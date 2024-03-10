import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.*;
import ru.work.DataProviderClass;
import ru.work.Setup;
import ru.work.pages.ProductsPage;
import ru.work.pages.YourCartPage;

import static io.qameta.allure.Allure.step;


@Epic("Покупка товара")
public class AddItemToCartTest extends Setup {
    ProductsPage productsPage = new ProductsPage();
    YourCartPage yourCartPage = new YourCartPage();

    @BeforeMethod(description = "Логинимся")
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
        productsPage.
                shouldAddItemToCart();
        //Проверки
        productsPage.checkChangeOfButton();
        productsPage.checkCountOfAddedItemsNearbyCart();
        yourCartPage.checkTheTransitionToTheShoppingCart();
        yourCartPage.checkItemInCart();
    }

    //Проверка для практики с DataProvider
    @Feature("Добавление товара в корзину")
    @Story("Добавление через Data Provider")
    @Test(priority = 2, dataProvider = "itemsDataProvider", dataProviderClass = DataProviderClass.class,
            description = "Проверка добавления товара в корзину")
    public void shouldTestDataProvider(int index, String addedItem, String countOfAddedItems) {
        productsPage
                .shouldAddWithDataProvider(index);
        //Проверки
        productsPage.checkChangeOfButtonWithDP(index);
        productsPage.checkCountOfAddedItemsNearbyCartWithDP(countOfAddedItems);
        yourCartPage.checkTheTransitionToTheShoppingCart();
        yourCartPage.checkAddedItemsFromDataProvider(index, addedItem);
    }

    @AfterClass(description = "Очистка корзины")
    public void shouldRemoveItems() {
        step("Удаляем товары из корзины", () ->
                yourCartPage.removeAllItemsFromCart());
    }
}
