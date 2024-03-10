import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import ru.work.DataProviderClass;
import ru.work.Setup;
import ru.work.pages.ProductsPage;

@Epic("Покупка товара")
public class LoginTest extends Setup {
    ProductsPage productsPage = new ProductsPage();

    @Feature("Авторизация")
    @Story("Авторизация пользователя с главной страницы")
    @Test(dataProvider = "loginsDataProvider", dataProviderClass = DataProviderClass.class, description = "Проверка успешного входа в систему")
    public void shouldLogin(String userName, String password) {
        mainPage
                .login(userName, password);
        productsPage
                .checkSubtitle();
        productsPage
                .checkCountOfItems();
    }
}
