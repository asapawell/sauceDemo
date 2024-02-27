package ru.work.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.ofSeconds;
import static ru.work.AppConfig.baseURL;

public class BasePage {
    private final SelenideElement loginWrapper = $(".login_wrapper");
    public void openBasePage(){
        open(baseURL);
        loginWrapper.shouldBe(visible, ofSeconds(5));
    }

}
