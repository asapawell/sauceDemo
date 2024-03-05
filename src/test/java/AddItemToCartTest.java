import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.work.pages.ProductsPage;
import ru.work.pages.YourCartPage;

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
    @Story("Добавление со страницы товаров")
    @Test(priority = 1, description = "Проверка добавления товара \"Рюкзак\" в корзину")
    public void shouldAddItemToCart() {
        step("Добавить товар " + backpack.getName() + " в корзину", () ->
                productsPage
                        .addItemToCart(backpack)
                        .clickOnShoppingCart());
        step("Проверяем, что у товара кнопка Add to Cart поменялась на Remove", () ->
                Assert.assertEquals(productsPage.getChangedItemText(), "REMOVE"));
        step("Проверяем, что у корзины появился счетчик и он равен количеству добавленных товаров (1)", () ->
                Assert.assertEquals(productsPage.getCountOfShoppingCartItems(), "1"));
        step("Проверяем переход в корзину", () ->
                Assert.assertEquals(yourCartPage.getTitleText(), "Your Cart"));
        step("Проверяем, что был добавлен товар с названием " + backpack.getName(), () ->
                Assert.assertEquals(yourCartPage.getAddedItems(), "Sauce Labs Backpack"));
    }

    //Проверка для практики с DataProvider
    @Feature("Добавление товара в корзину")
    @Story("Добавление через Data Provider")
    @Test(priority = 2, dataProvider = "inputData", description = "Проверка добавления товара в корзину")
    public void shouldTestDataProvider(int index, String addedItem, String countOfAddedItems) {
        step("Добавить товар " + addedItem + " в корзину", () ->
                productsPage
                        .addItemFromDataProvider(index)
                        .clickOnShoppingCart());
        step("Проверяем, что у товара кнопка Add to Cart поменялась на Remove", () ->
                Assert.assertEquals(productsPage.getChangedItemTextFromDataProvider(index), "REMOVE"));
        step("Проверяем, что у корзины появился счетчик и он равен количеству добавленных товаров" + " "
                + countOfAddedItems, () -> Assert.assertEquals(productsPage.getCountOfShoppingCartItems(), countOfAddedItems));
        step("Проверяем переход в корзину", () ->
                Assert.assertEquals(yourCartPage.getTitleText(), "Your Cart"));
        step("Проверяем, что был добавлен товар с названием " + addedItem, () ->
                Assert.assertEquals(yourCartPage.getAddedItemsFromDataProvider(index), addedItem));
    }

    @AfterClass()
    public void shouldRemoveItems() {
        step("Удаляем товары из корзины", () ->
                yourCartPage.removeAllItemsFromCart());
    }
}
