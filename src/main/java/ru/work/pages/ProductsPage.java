package ru.work.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import ru.work.models.Item;

import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;
import static ru.work.utils.Builder.backpack;

public class ProductsPage {
    private final SelenideElement subTitle = $(".product_label");
    private final ElementsCollection products = $$(".inventory_item");
    private final ElementsCollection addToCardButtons = $$x("//div[@class='pricebar']//button[contains(@class,'btn_inventory')]");
    private final ElementsCollection removeFromCartButtons = $$x("//button[text()='REMOVE']");
    private final SelenideElement shoppingCartLink = $(".shopping_cart_link");
    private final SelenideElement countOfShoppingCartItems = $(".shopping_cart_badge");

    public String getSubTitle() {
        return subTitle.getText();
    }

    public int getCountOfItems() {
        return products.size();
    }

    //метод без использования dataProvider
    public ProductsPage addItemToCart(Item item) {
        return switch (item.getName()) {
            case "Sauce Labs Backpack" -> {
                addToCardButtons.get(0).click();
                yield this;
            }
            case "Sauce Labs Bike Light" -> {
                addToCardButtons.get(1).click();
                yield this;
            }
            case "Sauce Labs Bolt T-Shirt" -> {
                addToCardButtons.get(2).click();
                yield this;
            }
            case "Sauce Labs Fleece Jacket" -> {
                addToCardButtons.get(3).click();
                yield this;
            }
            case "Sauce Labs Onesie" -> {
                addToCardButtons.get(4).click();
                yield this;
            }
            case "Test.allTheThings() T-Shirt (Red)" -> {
                addToCardButtons.get(5).click();
                yield this;
            }
            default -> this;
        };
    }

    public String getChangedItemText() {
        return removeFromCartButtons.get(0).getText();
    }

    public String getCountOfShoppingCartItems() {
        return countOfShoppingCartItems.getText();
    }

    public void clickOnShoppingCart() {
        shoppingCartLink.click();
    }

    //методы для использования dataProvider
    public ProductsPage addItemFromDataProvider(int index) {
        addToCardButtons.get(index).click();
        return this;
    }

    public String getChangedItemTextFromDataProvider(int index) {
        return removeFromCartButtons.get(index).getText();
    }

    //Степы
    @Step("Проверка успешного логина. Имеется заголовок Products")
    public void checkSubtitle() {
        assertEquals(this.getSubTitle(), "Products");
    }

    @Step("Проверка, что на странице Products, имеется 6 товаров")
    public void checkCountOfItems() {
        assertEquals(this.getCountOfItems(), 6);
    }

    @Step("Добавляем Рюкзак в корзину")
    public void shouldAddItemToCart() {
        this
                .addItemToCart(backpack)
                .clickOnShoppingCart();
    }

    @Step("Проверяем, что у товара Рюкзак кнопка Add to Cart поменялась на Remove")
    public void checkChangeOfButton() {
        Assert.assertEquals(this.getChangedItemText(), "REMOVE");
    }

    @Step("Проверяем, что у корзины появился счетчик и он равен количеству добавленных товаров (1)")
    public void checkCountOfAddedItemsNearbyCart() {
        Assert.assertEquals(this.getCountOfShoppingCartItems(), "1");
    }

    @Step("Добавить товар в корзину")
    public void shouldAddWithDataProvider(int index) {
        this
                .addItemFromDataProvider(index)
                .clickOnShoppingCart();
    }

    @Step("Проверяем, что у добавленного товара кнопка Add to Cart поменялась на Remove")
    public void checkChangeOfButtonWithDP(int index) {
        Assert.assertEquals(this.getChangedItemTextFromDataProvider(index), "REMOVE");
    }

    @Step("Проверяем, что у корзины появился счетчик и он равен количеству добавленных товаров {countOfAddedItems}")
    public void checkCountOfAddedItemsNearbyCartWithDP(String countOfAddedItems) {
        Assert.assertEquals(this.getCountOfShoppingCartItems(),
                countOfAddedItems);
    }

}
