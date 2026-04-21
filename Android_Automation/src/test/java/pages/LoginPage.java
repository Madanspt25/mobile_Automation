package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    // Locators
    private By loginBtn = By.id("com.sportstechbrands.sportstechlive:id/login");
    private By emailLogin = By.id("com.sportstechbrands.sportstechlive:id/ll_email_signin");
    private By emailField = By.id("com.sportstechbrands.sportstechlive:id/etEmail");
    private By passwordField = By.id("com.sportstechbrands.sportstechlive:id/etPassword");
    private By loginButton = By.xpath("//android.widget.Button[@text='LOGIN']");

    // 🔥 FULL LOGIN FLOW
    public void login(String email, String password) {

        click(loginBtn);
        click(emailLogin);

        type(emailField, email);
        type(passwordField, password);

        try {
            driver.hideKeyboard();
        } catch (Exception e) {}

        click(loginButton);
    }
}