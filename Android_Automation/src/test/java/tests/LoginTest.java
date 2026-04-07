package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.navigateToLogin();
        loginPage.enterEmail(config.get("email"));
        loginPage.enterPassword(config.get("password"));

        loginPage.hideKeyboard();
        Thread.sleep(2000);

        loginPage.clickLogin();

        String welcomeText = loginPage.verifyHomePage();
        System.out.println("Login Successful: " + welcomeText);
    }
}