package ru.work.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class YourCartPage {
    private final SelenideElement title = $(".subheader");
    private final ElementsCollection item = $$(".inventory_item_name");
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

}
