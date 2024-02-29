package ru.work.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ProductsPage {
    private final SelenideElement subTitle = $(".product_label");
    private final ElementsCollection products = $$(".inventory_item");
    private final ElementsCollection addToCardButtons = $$x("//button[text()='ADD TO CART']");
    private final ElementsCollection removeFromCartButtons = $$x("//button[text()='REMOVE']");
    private final SelenideElement shoppingCartLink = $(".shopping_cart_link");
    private final SelenideElement countOfShoppingCartItems = $(".shopping_cart_badge");
    private final SelenideElement burgerMenu = $("#react-burger-menu-btn");
    private final SelenideElement logOutButton = $x("//a[text()='Logout']");

    public String getSubTitle() {
        return subTitle.getText();
    }

    public int getCountOfItems() {
        return products.size();
    }

    public ProductsPage addItemToCart() {

        addToCardButtons.get(0).click();
        return this;
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
    public void logOut(){
        burgerMenu.click();
        logOutButton.shouldBe(visible);
    }

}
