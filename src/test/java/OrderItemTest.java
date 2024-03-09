import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.work.Setup;
import ru.work.pages.*;

import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;
import static ru.work.utils.Builder.backpack;
import static ru.work.utils.Builder.user;

@Epic("Покупка товара")
public class OrderItemTest extends Setup {
    ProductsPage productsPage = new ProductsPage();
    YourCartPage yourCartPage = new YourCartPage();
    YourInformationPage yourInformationPage = new YourInformationPage();
    OverviewPage overviewPage = new OverviewPage();
    FinishPage finishPage = new FinishPage();

    @BeforeMethod(description = "Логинимся, добавляем Рюкзак в корзину, заполняем данные о пользователе, жмем на Continue")
    public void addItemToCart() {
        mainPage
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
        productsPage
                .addItemToCart(backpack)
                .clickOnShoppingCart();
        yourCartPage
                .clickOnCheckOutButton();
        yourInformationPage
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPostalCode(user.getPostalCode())
                .clickOnContinueButton();
    }

    //Проверяем что заказ прошел и что имеется эмблема пони экспресс.
    @Feature("Отображение информации о заказе")
    @Story("Пользователь успешно оформил заказ")
    @Test(description = "Проверка, что заказ оформлен")
    public void finishOrdering() {
        step("Нажимаем на кнопку Finish", () ->
                overviewPage
                        .clickOnFinish());
        step("Проверяем, что имеется заголовок \"THANK YOU FOR YOUR ORDER\"", () ->
                Assert.assertEquals(finishPage.getCompleteHeader(), "THANK YOU FR YOUR ORDER"));
        step("Проверяем наличие картинки с логотипом PonyExpress", () ->
                finishPage.getPonyExpressPicture().shouldBe(visible));
    }
}
