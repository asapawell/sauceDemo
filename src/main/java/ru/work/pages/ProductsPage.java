package ru.work.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.work.utils.Builder;
import ru.work.utils.Item;

import java.nio.Buffer;

import static com.codeborne.selenide.Selenide.*;

public class ProductsPage {
    private final SelenideElement subTitle = $(".product_label");
    private final ElementsCollection products = $$(".inventory_item");
    private final ElementsCollection addToCardButtons = $$x("//button[text()='ADD TO CART']");
    private final ElementsCollection removeFromCartButtons = $$x("//button[text()='REMOVE']");
    private final SelenideElement shoppingCartLink = $(".shopping_cart_link");
    private final SelenideElement countOfShoppingCartItems = $(".shopping_cart_badge");

    public String getSubTitle() {
        return subTitle.getText();
    }

    public int getCountOfItems() {
        return products.size();
    }

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

}
