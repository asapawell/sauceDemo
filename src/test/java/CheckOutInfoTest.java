import com.codeborne.selenide.commands.As;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.work.pages.OverviewPage;
import ru.work.pages.ProductsPage;
import ru.work.pages.YourCartPage;
import ru.work.pages.YourInformationPage;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static ru.work.utils.UserBuilder.user;

public class CheckOutInfoTest extends BaseTest {
    ProductsPage productsPage = new ProductsPage();
    YourCartPage yourCartPage = new YourCartPage();
    YourInformationPage yourInformationPage = new YourInformationPage();
    OverviewPage overviewPage = new OverviewPage();
    static final double tax = 8.01;

    @BeforeMethod
    void addItemToCart() {
        mainPage
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
        productsPage
                .addItemToCart()
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
        double cost = 29.99 * tax / 100 + 29.99;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols() {{
            setDecimalSeparator('.');
        }});
        String result = decimalFormat.format(cost);
        String finalCost = "$" + result;
        Assert.assertEquals(overviewPage.getItemTotalCostWithTax(),finalCost);
    }
}
