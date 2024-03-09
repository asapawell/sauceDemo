package ru.work.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;

public class OverviewPage {
    private final SelenideElement subTitle = $(".subheader");
    private final ElementsCollection items = $$(".inventory_item_name");
    private final SelenideElement itemTotal = $(".summary_subtotal_label");
    private final SelenideElement itemTotalCostWithTax = $(".summary_total_label");
    private final SelenideElement finishButton = $("a[href^='./checkout']");
    private final SelenideElement toCart = $x("//div[@id='shopping_cart_container']/a");
    public String getSubTitle(){
        return subTitle.getText();
    }
    public String getAddedItem(){
        return items.get(0).getText();
    }
    //метод для вытаскивания стоимости в строке
    public String getItemTotalCost(){
        String totalCost = null;
        String text = itemTotal.getText();
        Pattern pattern = Pattern.compile("\\$\\d{1,3}\\.\\d{2}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            totalCost =  matcher.group();
        }
        return totalCost;
    }
    //метод для вытаскивания окончательной стоимости из строки с налогом
    public String getItemTotalCostWithTax(){
        String totalCostWithTax = null;
        String text = itemTotalCostWithTax.getText();
        Pattern pattern = Pattern.compile("\\$\\d{1,3}\\.\\d{2}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            totalCostWithTax =  matcher.group();
        }
        return totalCostWithTax;
    }
    public void clickOnFinish(){
        finishButton.click();
    }
    public void clickOnCart(){
        toCart.click();
    }
}
