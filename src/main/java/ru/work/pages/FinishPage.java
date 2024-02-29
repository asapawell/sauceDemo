package ru.work.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FinishPage {
    private final SelenideElement completeHeader = $(".complete-header");
    private final SelenideElement ponyExpressPicture = $(".pony_express");
    public String getCompleteHeader(){
        return completeHeader.getText();
    }

    public SelenideElement getPonyExpressPicture() {
        return ponyExpressPicture;
    }
}
