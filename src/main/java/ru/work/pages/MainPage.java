package ru.work.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;


public class MainPage {
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
    //Степы
    @Step("Устанавливаем логин {userName} и пароль {password}, жмем на логин")
    public void login (String userName, String password){
        this
                .setLogin(userName)
                .setPassword(password)
                .clickLogin();
    }
}
