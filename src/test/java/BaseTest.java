import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import ru.work.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.work.AppConfig.baseURL;

public class BaseTest {
    MainPage mainPage = new MainPage();
    @BeforeClass
    public void setUp(){
        Configuration.browser = "firefox";
        Configuration.browserSize = "1920x1080";
        open(baseURL);
    }
}
