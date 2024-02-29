package ru.work.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.time.Duration.ofSeconds;
import static ru.work.AppConfig.baseURL;

public class MainPage {
    private final SelenideElement loginWrapper = $(".login_wrapper");
    private final SelenideElement userLogin = $("#user-name");
    private final SelenideElement userPassword = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    public MainPage setLogin(String login){
        userLogin.setValue(login);
        return this;
    }
    public MainPage setPassword(String password){
        userPassword.setValue(password);
        return this;
    }
    public void clickLogin(){
        loginButton.click();
    }

}
