import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.work.pages.*;

import static com.codeborne.selenide.Condition.visible;
import static ru.work.utils.UserBuilder.user;

public class OrderItemTest extends BaseTest {
    ProductsPage productsPage = new ProductsPage();
    YourCartPage yourCartPage = new YourCartPage();
    YourInformationPage yourInformationPage = new YourInformationPage();
    OverviewPage overviewPage = new OverviewPage();
    FinishPage finishPage = new FinishPage();

    @BeforeMethod
    void addItemToCart() {
        mainPage
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
        productsPage
                .addItemToCart()
                .clickOnShoppingCart();
        yourCartPage
                .clickOnCheckOutButton();
        yourInformationPage
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPostalCode(user.getPostalCode())
                .clickOnContinueButton();
    }
        @Test
        void finishOrdering() {
            overviewPage
                    .clickOnFinish();

            Assert.assertEquals(finishPage.getCompleteHeader(),"THANK YOU FOR YOUR ORDER");
            finishPage.getPonyExpressPicture().shouldBe(visible);
        }
    }
