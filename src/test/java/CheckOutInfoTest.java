import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.work.Setup;
import ru.work.pages.OverviewPage;
import ru.work.pages.ProductsPage;
import ru.work.pages.YourCartPage;
import ru.work.pages.YourInformationPage;

import static io.qameta.allure.Allure.step;
import static ru.work.utils.Builder.backpack;


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
    @Test(description = "Проверка заполнения обязательных полей", groups = "a")
    public void setUserInfo() {
        yourCartPage.shouldOpenCheckOutOverviewPage();
        yourInformationPage.shouldSetInfoAboutUser();
        //проверки
        overviewPage.checkOpeningOverviewPage();
        overviewPage.checkAddedItem();
        overviewPage.checkCostWithoutTax();
        overviewPage.checkCostWithTax();
    }

    @AfterMethod(description = "После теста переходим в корзину, для удаления товаров")
    public void toCart() {
        step("Нажимаем на корзину", () ->
                overviewPage.clickOnCart());
    }

    @AfterClass(description = "Очистка корзины")
    public void shouldRemoveItems() {
        step("Удаляем товары из корзины", () ->
                yourCartPage.removeAllItemsFromCart());
    }
}
