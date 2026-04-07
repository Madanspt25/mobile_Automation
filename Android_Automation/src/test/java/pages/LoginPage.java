package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public LoginPage(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Locators
    By loginBtn = By.id("com.sportstechbrands.sportstechlive:id/login");
    By emailSignIn = By.id("com.sportstechbrands.sportstechlive:id/ll_email_signin");
    By emailField = By.id("com.sportstechbrands.sportstechlive:id/etEmail");
    By passwordField = By.id("com.sportstechbrands.sportstechlive:id/etPassword");
    By loginSubmitBtn = By.xpath("//android.widget.Button[@text='LOGIN']");
    By homeLogo = By.id("com.sportstechbrands.sportstechlive:id/ivSportsTech");
    By userName = By.id("com.sportstechbrands.sportstechlive:id/tv_user_name");

    // Actions
    public void navigateToLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(emailSignIn)).click();
    }

    public void enterEmail(String email) {
        WebElement emailEl = wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailEl.click();
        emailEl.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passEl = driver.findElement(passwordField);
        passEl.click();
        passEl.sendKeys(password);
    }

    public void hideKeyboard() {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Keyboard already hidden");
        }
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginSubmitBtn)).click();
    }

    public String verifyHomePage() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(homeLogo),
                ExpectedConditions.visibilityOfElementLocated(userName)
        ));
        return driver.findElement(userName).getText();
    }
}