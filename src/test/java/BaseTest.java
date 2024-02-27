import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import ru.work.pages.BasePage;

public class BaseTest {
    BasePage basePage = new BasePage();
    @BeforeClass
    public void shouldOpen(){
        Configuration.browserSize = "1920x1080";
        basePage.openBasePage();
    }
}
