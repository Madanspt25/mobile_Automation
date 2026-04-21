package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public SignupPage(AndroidDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Locators
    By createAccountBtn = By.id("com.sportstechbrands.sportstechlive:id/getStarted");
    By emailOption = By.id("com.sportstechbrands.sportstechlive:id/ll_email_signUp");

    By emailField = By.xpath("//android.widget.EditText[contains(@text,'Email') or contains(@hint,'Email')]");
    By passwordField = By.xpath("//android.widget.EditText[contains(@text,'Password') or contains(@hint,'Password') or contains(@text,'Set')]");

    // Screen 1
    By continueBtnStep1 = By.xpath("//android.widget.Button[@text='CONTINUE']");

    // Screen 2 (DOB)
    By continueBtnDOB = By.id("com.sportstechbrands.sportstechlive:id/buttonSendOtp");


    // Actions
    public void clickCreateAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(createAccountBtn)).click();
    }

    public void clickEmailOption() {
        wait.until(ExpectedConditions.elementToBeClickable(emailOption)).click();
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//android.widget.EditText[@hint='Enter your email ID']")
                )
        );
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//android.widget.EditText[@hint='Set password']")
                )
        );
        passwordField.sendKeys(password);
    }

    public void clickContinue() {
        WebElement continueBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//android.widget.Button[@text='CONTINUE']")
                )
        );
        continueBtn.click();
    }
}