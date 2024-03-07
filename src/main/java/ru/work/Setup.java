package ru.work;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import ru.work.pages.MainPage;

import java.lang.reflect.Method;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static ru.work.AppConfig.baseURL;

public class Setup {
    public MainPage mainPage = new MainPage();

    @BeforeSuite
    public void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        // or for fine-tuning:
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }

    @BeforeMethod
    public void init() {
        step("Открываем стартовую страницу " + baseURL, () -> {
            Configuration.browser = "firefox";
            Configuration.browserSize = "1920x1080";
            open(baseURL);
        });
    }

    @DataProvider(name = "inputData")
    public Object[][] getDataFromDataProvider(Method method) {
        if (method.getName().equalsIgnoreCase(("shouldLogin"))) {
            return new Object[][]
                    {
                            {"standard_user", "secret_sauce"},
                            {"locked_out_user", "secret_sauce"},
                            {"problem_user", "secret_sauce"},
                            {"performance_glitch_user", "secret_sauce"}
                    };
        } else {
            return new Object[][]{
                    {1, "Sauce Labs Bike Light", "2"},
                    {2, "Sauce Labs Bolt T-Shirt", "3"},
                    {3, "Sauce Labs Fleece Jacket", "4"},
                    {4, "Sauce Labs Onesie", "5"},
                    {5, "Test.allTheThings() T-Shirt (Red)", "6"}
            };
        }
    }
}
