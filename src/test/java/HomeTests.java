import com.google.common.collect.Ordering;
import org.junit.Assert;
import org.junit.Test;
import pages.Homepage;
import pages.LoginPage;
import utilities.DriverManager;

import java.util.List;

public class HomeTests extends BaseTest {

    @Test
    public void verifyCartButtonNumberIsAdded(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();
        Homepage homepage= new Homepage(DriverManager.getDriver().driver);
        homepage.clickOnAddSauceLabsBackPackToCartButton();
        Assert.assertEquals( "1", homepage.getCartIconText());
        homepage.clickOnRemoveSauceLabsBackPackToCartButton();
    }
    @Test
    public void verifyCartButtonNumberIsBlankWhereThereIsNotProductsInTheCart(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();
        Homepage homepage= new Homepage(DriverManager.getDriver().driver);
        homepage.clickOnAddSauceLabsBackPackToCartButton();
        homepage.clickOnRemoveSauceLabsBackPackToCartButton();
        Assert.assertEquals( "", homepage.getCartIconText());
    }

    @Test
    public void verifyResetAppStateTest(){
        // Login with standard user
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();
        // Adding sauce labs back pack to the cart
        Homepage homepage= new Homepage(DriverManager.getDriver().driver);
        homepage.clickOnAddSauceLabsBackPackToCartButton();
        // Reset app state
        homepage.clickOnHamburguerButton();
        homepage.clickOnResetAppStateLink();
        DriverManager.getDriver().driver.navigate().refresh(); // F5
        // Verifications
        Assert.assertEquals( "", homepage.getCartIconText());
        Assert.assertTrue(homepage.addToCartSauceLabsBackPackButtonIsDisplayed());

    }

    @Test
    public void verifyLowToHighPriceItemsFilterTest() {
        // Login with standard user
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();
        // Filtering by price
        Homepage homepage= new Homepage(DriverManager.getDriver().driver);
        homepage.selectProductFilter("Price (low to high)");
        List<Double> prices = homepage.getAllItemPrices();
        boolean pricesAreSorted = Ordering.natural().isOrdered(prices);
        Assert.assertTrue(pricesAreSorted);
    }
}
