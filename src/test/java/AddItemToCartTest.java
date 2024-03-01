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
    public void login() {
        mainPage
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
    }
    //проверка добавления товара "Рюкзак" в корзину
    @Test(priority = 1)
    public void shouldAddItemToCart() {
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
    //Проверка для практики с DataProvider. Добавляем все оставшиеся товары по очереди.
    @Test(priority = 2,dataProvider = "inputData")
    public void shouldTestDataProvider(int index,String addedItem,String countOfAddedItems){
        productsPage
                .addItemFromDataProvider(index)
                .clickOnShoppingCart();

        Assert.assertEquals(productsPage.getChangedItemTextFromDataProvider(index),"REMOVE");
        Assert.assertEquals(productsPage.getCountOfShoppingCartItems(), countOfAddedItems);
        Assert.assertEquals(yourCartPage.getTitleText(), "Your Cart");
        Assert.assertEquals(yourCartPage.getAddedItemsFromDataProvider(index), addedItem);
    }
}
