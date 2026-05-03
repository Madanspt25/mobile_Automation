package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class ProfilePage {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public ProfilePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ===== Locators =====
    private final By profileTab = By.xpath("//android.widget.TextView[@text='Profile']");

    // NEW: Added this to detect the Contact Us / Report an Issue screen
    private final By reportAnIssueTitle = By.xpath("//android.widget.TextView[@text='Report an Issue']");

    // Generic toolbar back options
    private final By toolbarBack1 = By.xpath("//android.widget.ImageButton");
    private final By toolbarBack2 = By.xpath("//android.widget.ImageView[@content-desc='Navigate up']");
    private final By toolbarBack3 = By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']");

    // Panel Elements
    private final By myDashboard = By.xpath("//android.widget.TextView[@text='My Dashboard']");
    private final By memberships = By.xpath("//android.widget.TextView[@text='Memberships']");
    private final By editLoginMethods = By.xpath("//android.widget.TextView[@text='Edit login methods']");
    private final By basicInformation = By.xpath("//android.widget.TextView[@text='Basic information']");
    private final By primaryGoal = By.xpath("//android.widget.TextView[@text='Primary goal']");
    private final By fitnessInformation = By.xpath("//android.widget.TextView[@text='Fitness information']");
    private final By healthConnect = By.xpath("//android.widget.TextView[@text='Health Connect']");
    private final By connectWearables = By.xpath("//android.widget.TextView[@text='Connect Wearables']");
    private final By goalSettings = By.xpath("//android.widget.TextView[@text='Goal settings']");
    private final By unitOfMeasurement = By.xpath("//android.widget.TextView[@text='Unit of Measurement']");
    private final By faqs = By.xpath("//android.widget.TextView[@text=\"FAQ's\"]");
    private final By privacyPolicy = By.xpath("//android.widget.TextView[@text='Privacy Policy']");
    private final By rateTheApp = By.xpath("//android.widget.TextView[@text='Rate the App']");
    private final By contactUs = By.xpath("//android.widget.TextView[@text='Contact Us']");

    // ===== Navigation Actions =====

    public void openProfile() {
        wait.until(ExpectedConditions.elementToBeClickable(profileTab)).click();
    }

    /**
     * Navigates back and handles keyboard/modal overlays.
     */
    public void goBackToProfile() {
        // 1. Check if we are on the Report an Issue modal
        boolean isReportPage = false;
        try {
            isReportPage = driver.findElements(reportAnIssueTitle).size() > 0;
        } catch (Exception ignored) {}

        if (isReportPage) {
            System.out.println("Detected Report an Issue page. Sending back command...");
            driver.navigate().back(); // This might only close the keyboard if open
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }

        // 2. Try standard toolbar back buttons
        boolean clicked = false;
        By[] backButtons = {toolbarBack1, toolbarBack2, toolbarBack3};

        for (By locator : backButtons) {
            try {
                wait.withTimeout(Duration.ofSeconds(2))
                        .until(ExpectedConditions.elementToBeClickable(locator)).click();
                clicked = true;
                break;
            } catch (Exception ignored) {}
        }

        // 3. Fallback: If still not back, perform system back
        if (!clicked) {
            driver.navigate().back();
        }

        // 4. Verification & Recovery
        try {
            wait.withTimeout(Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(profileTab));
        } catch (Exception e) {
            System.out.println("Still stuck? Attempting second back navigation...");
            driver.navigate().back(); // Second back handles Keyboard + Page exit
            try {
                wait.withTimeout(Duration.ofSeconds(3))
                        .until(ExpectedConditions.visibilityOfElementLocated(profileTab));
            } catch (Exception fatal) {
                // Last resort: force click the profile tab if it exists
                openProfile();
            }
        }
    }

    // ===== Page Actions =====
    public void openMyDashboard() { wait.until(ExpectedConditions.elementToBeClickable(myDashboard)).click(); }
    public void clickMemberships() { wait.until(ExpectedConditions.elementToBeClickable(memberships)).click(); }
    public void clickEditLoginMethods() { wait.until(ExpectedConditions.elementToBeClickable(editLoginMethods)).click(); }
    public void clickBasicInformation() { wait.until(ExpectedConditions.elementToBeClickable(basicInformation)).click(); }
    public void clickPrimaryGoal() { wait.until(ExpectedConditions.elementToBeClickable(primaryGoal)).click(); }
    public void clickFitnessInformation() { wait.until(ExpectedConditions.elementToBeClickable(fitnessInformation)).click(); }
    public void clickHealthConnect() { wait.until(ExpectedConditions.elementToBeClickable(healthConnect)).click(); }
    public void clickConnectWearables() { wait.until(ExpectedConditions.elementToBeClickable(connectWearables)).click(); }
    public void clickGoalSettings() { wait.until(ExpectedConditions.elementToBeClickable(goalSettings)).click(); }
    public void clickUnitOfMeasurement() { wait.until(ExpectedConditions.elementToBeClickable(unitOfMeasurement)).click(); }
    public void clickFaqs() { wait.until(ExpectedConditions.elementToBeClickable(faqs)).click(); }
    public void clickPrivacyPolicy() { wait.until(ExpectedConditions.elementToBeClickable(privacyPolicy)).click(); }
    public void clickRateTheApp() { wait.until(ExpectedConditions.elementToBeClickable(rateTheApp)).click(); }

    public void clickContactUs() {
        scrollDown();
        wait.until(ExpectedConditions.elementToBeClickable(contactUs)).click();
    }

    // ===== Scroll Helpers =====
    public void scrollDown() {
        Dimension size = driver.manage().window().getSize();
        driver.executeScript("mobile: swipeGesture", Map.of(
                "left", size.width / 2,
                "top", (int) (size.height * 0.2),
                "width", 200,
                "height", (int) (size.height * 0.6),
                "direction", "up",
                "percent", 1.0
        ));
    }

    public void resetProfileScroll() {
        Dimension size = driver.manage().window().getSize();
        driver.executeScript("mobile: swipeGesture", Map.of(
                "left", size.width / 2,
                "top", (int) (size.height * 0.30),
                "width", 100,
                "height", 700,
                "direction", "down",
                "percent", 0.9
        ));
    }
}