package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        System.out.println("--- TEST STARTED ---");

        loginPage.login("protest@gmail.com", "Test@123");

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Login Failed");

        System.out.println("Welcome Text: " + homePage.getWelcomeText());
    }
}
