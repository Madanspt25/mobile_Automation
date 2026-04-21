package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {

    AndroidDriver driver;
    WebDriverWait wait;

    public ProfilePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By profileTab = By.xpath("//android.widget.TextView[@text='Profile']");
    private By myDashboard = By.xpath("//android.widget.TextView[@text='My Dashboard']");
    private By memberships = By.xpath("//android.widget.TextView[@text='Memberships']");
    private By editLoginMethods = By.xpath("//android.widget.TextView[@text='Edit login methods']");
    private By basicInformation = By.id("com.sportstechbrands.sportstechlive:id/linear_basic_information");
    private By primaryGoal = By.id("com.sportstechbrands.sportstechlive:id/linear_primary_goal");
    private By fitnessInformation = By.id("com.sportstechbrands.sportstechlive:id/linear_fitness_information");

    // Common reusable click
    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Actions
    public void openProfile() {
        click(profileTab);
    }

    public void clickMyDashboard() {
        click(myDashboard);
    }

    public void clickMemberships() {
        click(memberships);
    }

    public void clickEditLoginMethods() {
        click(editLoginMethods);
    }

    public void clickBasicInformation() {
        click(basicInformation);
    }

    public void clickPrimaryGoal() {
        click(primaryGoal);
    }

    public void clickFitnessInformation() {
        click(fitnessInformation);
    }

    public void goBack() {
        driver.navigate().back();
    }
}
