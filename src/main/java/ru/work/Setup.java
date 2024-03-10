package ru.work;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;
import ru.work.pages.MainPage;


import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static ru.work.AppConfig.baseURL;

public class Setup {
    public MainPage mainPage = new MainPage();

    @BeforeMethod(description = "Открываем стартовую страницу " + baseURL)
    public void init() {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
        open(baseURL);
    }
}
