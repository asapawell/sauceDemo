package ru.work.listeners;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.work.Setup;


//Добавил Listener, чтобы скриншоты делались не только при падении селенидовских методов, но и при падении тест нг методов.
public class CustomListener extends Setup implements ITestListener {
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        attachScreen();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreen() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}

