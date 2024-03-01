import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.work.pages.*;

import static com.codeborne.selenide.Condition.visible;
import static ru.work.utils.Builder.backpack;
import static ru.work.utils.Builder.user;

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
                .addItemToCart(backpack)
                .clickOnShoppingCart();
        yourCartPage
                .clickOnCheckOutButton();
        yourInformationPage
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPostalCode(user.getPostalCode())
                .clickOnContinueButton();
    }

    //Проверяем что заказ прошел и что имеется эмблема пони экспресс.
    @Test
    void finishOrdering() {
        overviewPage
                .clickOnFinish();

        Assert.assertEquals(finishPage.getCompleteHeader(), "THANK YOU FOR YOUR ORDER");
        finishPage.getPonyExpressPicture().shouldBe(visible);
    }
}
