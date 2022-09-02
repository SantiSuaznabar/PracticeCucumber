package stepDefinitions;

import com.google.common.collect.Ordering;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.Homepage;
import pages.LoginPage;
import utilities.DriverManager;

import java.util.List;

public class LoginSteps {
    LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
    Homepage homepage = new Homepage(DriverManager.getDriver().driver);

    @Given("I set the user name field with {string}")
    public void setUserName(String userName){
        loginPage.setUserNameTextBox(userName);
    }

    @And("I set the password field with {string}")
    public void setUserPassword(String password){
        loginPage.setPasswordTextBox(password);
    }

    @When("I click on login button")
    public void clickOnLoginButton(){
        loginPage.clickOnLoginButton();
    }

    @Then("The home page should be displayed")
    public void verifyHomePageIsDisplayed(){
        Assert.assertTrue(homepage.appLogoIsDisplayed());
    }

    @Then("An error message that says {string} is displayed")
    public void verifyLoginErrorMessage(String message){
        Assert.assertEquals(message, loginPage.getLoginErrorMessage());
    }

    @Given("I login with {string} and {string}")
    public void fullLogin(String user, String password)
    {
        loginPage.setUserNameTextBox(user);
        loginPage.setPasswordTextBox(password);
        loginPage.clickOnLoginButton();
    }

    @When("I click the hamburger button")
    public void clickHamburgerBtn(){homepage.clickOnHamburguerButton();}

    @And("Click the Logout Button")
    public void clickLogout() {homepage.clickOnLogoutLink();}

    @Then("I should be in the login page again")
    public void verifyLoginPage(){Assert.assertTrue(loginPage.loginButtonIsDisplayed());}

    @When("I add a Sauce Labs Backpack")
    public void addBackpack(){homepage.clickOnAddSauceLabsBackPackToCartButton();}

    @Then("The number in the cart should be: {string}")
    public void verifyNumInCart(String num){Assert.assertEquals(num, homepage.getCartIconText());}

    @When("I select the low to high filter")
    public void selectLowToHighFilter(){homepage.selectProductFilter("Price (low to high)");}

    @Then("All items should be displayed sorted")
    public void verifySortedItems(){
        List<Double> prices = homepage.getAllItemPrices();
        boolean pricesAreSorted = Ordering.natural().isOrdered(prices);
        Assert.assertTrue(pricesAreSorted);
    }
}
