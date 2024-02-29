import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.work.pages.OverviewPage;
import ru.work.pages.ProductsPage;
import ru.work.pages.YourCartPage;
import ru.work.pages.YourInformationPage;

import static ru.work.utils.Builder.backpack;
import static ru.work.utils.Builder.user;
import static ru.work.utils.Item.getFinalCostWithTax;

public class CheckOutInfoTest extends BaseTest {
    ProductsPage productsPage = new ProductsPage();
    YourCartPage yourCartPage = new YourCartPage();
    YourInformationPage yourInformationPage = new YourInformationPage();
    OverviewPage overviewPage = new OverviewPage();

    @BeforeMethod
    void addItemToCart() {
        mainPage
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
        productsPage
                .addItemToCart(backpack)
                .clickOnShoppingCart();
    }

    @Test
    void setUserInfo() {
        yourCartPage
                .clickOnCheckOutButton();
        yourInformationPage
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPostalCode(user.getPostalCode())
                .clickOnContinueButton();

        Assert.assertEquals(overviewPage.getSubTitle(), "Checkout: Overview");
        Assert.assertEquals(overviewPage.getAddedItem(), "Sauce Labs Backpack");
        //проверка стоимости товара
        Assert.assertEquals(overviewPage.getItemTotalCost(), "$29.99");
        //проверка окончательной стоимости товара с налогом
        Assert.assertEquals(overviewPage.getItemTotalCostWithTax(), getFinalCostWithTax(backpack));
    }
}
