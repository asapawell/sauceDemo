import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import ru.work.pages.ProductsPage;

import static io.qameta.allure.Allure.step;
import static org.testng.Assert.assertEquals;

@Epic("Покупка товара")
public class LoginTest extends BaseTest {
    ProductsPage productsPage = new ProductsPage();

    @Feature("Авторизация")
    @Story("Авторизация пользователя с главной страницы")
    @Test(dataProvider = "inputData", description = "Проверка успешного входа в систему")
    public void shouldLogin(String userName, String password) {
        step("Устанавливаем логин " + userName + " " +
                "и пароль " + password + " " + ", жмем на логин", () ->
                mainPage
                        .setLogin(userName)
                        .setPassword(password)
                        .clickLogin());

        //Тесты, что после логина, мы попали на страницу с товарами.
        step("Проверка успешного логина. Имеется заголовок Products", () ->
                assertEquals(productsPage.getSubTitle(), "Products"));
        step("Проверка, что при на странице Products, имеется 6 товаров", () ->
                assertEquals(productsPage.getCountOfItems(), 6));

    }
}
