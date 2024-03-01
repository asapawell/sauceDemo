import org.testng.annotations.Test;
import ru.work.pages.ProductsPage;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    ProductsPage productsPage = new ProductsPage();

    @Test(dataProvider = "inputData")
    public void shouldLogin(String userName,String password) {
        mainPage
                .setLogin(userName)
                .setPassword(password)
                .clickLogin();
        //Тесты, что после логина, мы попали на страницу с товарами.
        assertEquals(productsPage.getSubTitle(), "Products");
        assertEquals(productsPage.getCountOfItems(), 6);
    }
}
