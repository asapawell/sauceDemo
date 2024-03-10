package ru.work.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class FinishPage {
    private final SelenideElement completeHeader = $(".complete-header");
    @Getter
    private final SelenideElement ponyExpressPicture = $(".pony_express");

    public String getCompleteHeader() {
        return completeHeader.getText();
    }

    //Степы
    @Step("Проверяем, что имеется заголовок \"THANK YOU FOR YOUR ORDER\"")
    public void checkOpeningFinisPage() {
        Assert.assertEquals(this.getCompleteHeader(), "THANK YOU FOR YOUR ORDER");
    }

    @Step("Проверяем наличие картинки с логотипом PonyExpress")
    public void checkPonyExpressLogo() {
        this.getPonyExpressPicture().shouldBe(visible);
    }

}
