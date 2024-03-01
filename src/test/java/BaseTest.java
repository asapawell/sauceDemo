import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;
import ru.work.pages.MainPage;

import java.lang.reflect.Method;

import static com.codeborne.selenide.Selenide.open;
import static ru.work.AppConfig.baseURL;

public class BaseTest {
    MainPage mainPage = new MainPage();

    @BeforeMethod
    public void setUp() {
        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
        open(baseURL);
    }

    //Провайдер используется в тесте логина и в тесте добавления товара в корзину
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
