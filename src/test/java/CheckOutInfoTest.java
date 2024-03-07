import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.work.Setup;
import ru.work.pages.OverviewPage;
import ru.work.pages.ProductsPage;
import ru.work.pages.YourCartPage;
import ru.work.pages.YourInformationPage;

import static io.qameta.allure.Allure.step;
import static ru.work.utils.Builder.backpack;
import static ru.work.utils.Builder.user;
import static ru.work.utils.Item.getFinalCostWithTax;

@Epic("Покупка товара")
public class CheckOutInfoTest extends Setup {
    ProductsPage productsPage = new ProductsPage();
    YourCartPage yourCartPage = new YourCartPage();
    YourInformationPage yourInformationPage = new YourInformationPage();
    OverviewPage overviewPage = new OverviewPage();

    @BeforeMethod(description = "Логинимся и добавляем Рюкзак в корзину")
    public void login() {
        mainPage
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
        productsPage
                .addItemToCart(backpack)
                .clickOnShoppingCart();
    }

    //вводим данные пользователя и проверяем, что попали на страницу Оформления заказа
    @Feature("Заполнение данных для отправки")
    @Story("Указание имени, фамилии, почтового индекса")
    @Test(description = "Проверка заполнения обязательных полей")
    public void setUserInfo() {
        step("Нажимаем на кнопку Checkout", () ->
                yourCartPage
                        .clickOnCheckOutButton());
        step("Заполняем имя, фамилию, почтовый индекс и нажимаем на кнопку Continue", () ->
                yourInformationPage
                        .setFirstName(user.getFirstName())
                        .setLastName(user.getLastName())
                        .setPostalCode(user.getPostalCode())
                        .clickOnContinueButton());

        step("Проверяем что попали на страницу с заголовком \"Checkout: Overview\"", () ->
                Assert.assertEquals(overviewPage.getSubTitle(), "Checkout: Overview"));
        step("Проверяем, что на странице имеется заказ " + overviewPage.getAddedItem(), () ->
                Assert.assertEquals(overviewPage.getAddedItem(), "Sauce Labs Backpack"));
        step("Проверяем, что стоимость товара без налога составляет " + backpack.getCost() + " " + "долларов",
                () -> Assert.assertEquals(overviewPage.getItemTotalCost(), "$29.99"));
        step("Проверяем что окончательная стоимость товара составляет " + getFinalCostWithTax(backpack),
                () -> Assert.assertEquals(overviewPage.getItemTotalCostWithTax(),
                        getFinalCostWithTax(backpack)));
    }

    @AfterClass()
    public void shouldRemoveItems() {
        step("Удаляем товары из корзины", () ->
                yourCartPage.removeAllItemsFromCart());
    }
}
