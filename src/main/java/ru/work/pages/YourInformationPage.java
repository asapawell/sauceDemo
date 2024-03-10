package ru.work.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static ru.work.utils.Builder.user;

public class YourInformationPage {
    private final SelenideElement firstNameInput = $("#first-name");
    private final SelenideElement lastNameInput = $("#last-name");
    private final SelenideElement postalCodeInput = $("#postal-code");
    private final SelenideElement continueButton = $x("//input[@type='submit']");

    public YourInformationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public YourInformationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public YourInformationPage setPostalCode(String postalCode) {
        postalCodeInput.setValue(postalCode);
        return this;
    }
    public void clickOnContinueButton(){
        continueButton.click();
    }

    //Степы
    @Step("Заполняем имя, фамилию, почтовый индекс и нажимаем на кнопку Continue")
    public void shouldSetInfoAboutUser(){
        this
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPostalCode(user.getPostalCode())
                .clickOnContinueButton();
    }
}
