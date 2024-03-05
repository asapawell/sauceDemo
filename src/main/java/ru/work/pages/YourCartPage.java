package ru.work.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class YourCartPage {
    private final SelenideElement title = $(".subheader");
    private final ElementsCollection item = $$(".inventory_item_name");
    private final ElementsCollection removeButton = $$x("//button[@class='btn_secondary cart_button']");
    private final SelenideElement checkOutButton = $("a[href^='./checkout']");

    public String getTitleText() {
        return title.getText();
    }

    public String getAddedItems() {
        return item.get(0).getText();
    }

    public void clickOnCheckOutButton() {
        checkOutButton.click();
    }

    //метод для dataProvider
    public String getAddedItemsFromDataProvider(int index) {
        return item.get(index).getText();
    }

    public void removeAllItemsFromCart() {
        removeButton.forEach(SelenideElement::click);
    }


}
