package base;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.ConfigReader;

import java.net.URL;
import java.time.Duration;

public class BaseTest {

    public AndroidDriver driver;
    public WebDriverWait wait;
    public ConfigReader config;

    @BeforeClass
    public void setup() throws Exception {

        config = new ConfigReader();

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", config.get("platformName"));
        caps.setCapability("appium:automationName", config.get("automationName"));
        caps.setCapability("appium:deviceName", config.get("deviceName"));
        caps.setCapability("appium:appPackage", config.get("appPackage"));
        caps.setCapability("appium:appActivity", config.get("appActivity"));
        caps.setCapability("appium:appWaitActivity", config.get("appWaitActivity"));
        caps.setCapability("appium:noReset", Boolean.parseBoolean(config.get("noReset")));
        caps.setCapability("appium:ignoreHiddenApiPolicyError",
                Boolean.parseBoolean(config.get("ignoreHiddenApiPolicyError")));

        driver = new AndroidDriver(new URL(config.get("appiumURL")), caps);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}