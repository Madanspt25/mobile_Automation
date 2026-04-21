package utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverFactory {

    public static AndroidDriver initializeDriver() throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:deviceName", "Android");
        caps.setCapability("appium:appPackage", "com.sportstechbrands.sportstechlive");
        caps.setCapability("appium:appActivity", "com.sportstechbrands.sportstechlive.activities.newUiDesignRewamp.Welcome");
        caps.setCapability("appium:noReset", true);
        caps.setCapability("appium:ignoreHiddenApiPolicyError", true);
        return new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
    }
}