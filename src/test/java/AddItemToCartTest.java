import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.work.pages.ProductsPage;
import ru.work.pages.YourCartPage;

import static ru.work.utils.Builder.*;

public class AddItemToCartTest extends BaseTest {
    ProductsPage productsPage = new ProductsPage();
    YourCartPage yourCartPage = new YourCartPage();

    @BeforeMethod
    void login() {
        mainPage
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
    }

    @Test()
    void shouldAddItemToCart() {
        productsPage
                .addItemToCart(backpack)
                .clickOnShoppingCart();
        //проверка смены статуса у товара
        Assert.assertEquals(productsPage.getChangedItemText(), "REMOVE");
        //проверка счетчика у корзины
        Assert.assertEquals(productsPage.getCountOfShoppingCartItems(), "1");
        //проверка перехода в корзину
        Assert.assertEquals(yourCartPage.getTitleText(), "Your Cart");
        //проверка, что добавили нужный товар
        Assert.assertEquals(yourCartPage.getAddedItems(), "Sauce Labs Backpack");
    }
}
