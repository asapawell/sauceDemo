import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.work.pages.ProductsPage;
import ru.work.pages.YourCartPage;
import ru.work.utils.Builder;

import static io.qameta.allure.Allure.step;
import static ru.work.utils.Builder.*;

@Epic("Покупка товара")
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

    @Feature("Добавление товара в корзину")
    @Test(priority = 1, description = "Проверка добавления товара \"Рюкзак\" в корзину")
    public void shouldAddItemToCart() {
        step("Добавить товар " + backpack.getName() + " в корзину", () -> {
            productsPage
                    .addItemToCart(backpack)
                    .clickOnShoppingCart();
        });
        step("Проверяем, что у товара кнопка Add to Cart поменялась на Remove", () -> {
            Assert.assertEquals(productsPage.getChangedItemText(), "REMOVE");
        });
        step("Проверяем, что у корзины появился счетчик и он равен количеству добавленных товаров (1)", () -> {
            Assert.assertEquals(productsPage.getCountOfShoppingCartItems(), "1");
        });
        step("Проверяем переход в корзину", () -> {
            Assert.assertEquals(yourCartPage.getTitleText(), "Your Cart");
        });
        step("Проверяем, что был добавлен товар с названием " + backpack.getName(), () -> {
            Assert.assertEquals(yourCartPage.getAddedItems(), "Sauce Labs Backpack");
        });
    }

    //Проверка для практики с DataProvider. Добавляем все оставшиеся товары по очереди.
    @Test(priority = 2, dataProvider = "inputData")
    public void shouldTestDataProvider(int index, String addedItem, String countOfAddedItems) {
        productsPage
                .addItemFromDataProvider(index)
                .clickOnShoppingCart();

        Assert.assertEquals(productsPage.getChangedItemTextFromDataProvider(index), "REMOVE");
        Assert.assertEquals(productsPage.getCountOfShoppingCartItems(), countOfAddedItems);
        Assert.assertEquals(yourCartPage.getTitleText(), "Your Cart");
        Assert.assertEquals(yourCartPage.getAddedItemsFromDataProvider(index), addedItem);
    }
}
