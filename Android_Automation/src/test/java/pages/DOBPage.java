package pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class DOBPage {

    AndroidDriver driver;
    WebDriverWait wait;

    public DOBPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ✅ Continue button
    private By continueBtnDOB = By.id("com.sportstechbrands.sportstechlive:id/buttonSendOtp");

    // ✅ Stable locators
    private By monthPicker = By.xpath("//android.widget.DatePicker//android.widget.NumberPicker[1]");
    private By dayPicker   = By.xpath("//android.widget.DatePicker//android.widget.NumberPicker[2]");
    private By yearPicker  = By.xpath("//android.widget.DatePicker//android.widget.NumberPicker[3]");

    private By inputField = By.id("android:id/numberpicker_input");

    // =========================================================
    // ✅ MAIN METHOD
    // =========================================================
    public void setDobAndContinue(String month, int day, int year) {

        setMonth(month);
        setDay(day);
        setYear(year);

        clickContinueDOB();
    }

    // =========================================================
    // ✅ MONTH
    // =========================================================
    private void setMonth(String targetMonth) {

        WebElement picker = wait.until(ExpectedConditions.visibilityOfElementLocated(monthPicker));

        for (int i = 0; i < 30; i++) {

            String current = getPickerValue(picker);

            if (current.equalsIgnoreCase(targetMonth)) {
                System.out.println("Month selected: " + current);
                return;
            }

            swipeDown(picker);
            sleep(300);
        }

        throw new RuntimeException("Month not found: " + targetMonth);
    }

    // =========================================================
    // ✅ DAY
    // =========================================================
    private void setDay(int targetDay) {

        WebElement picker = wait.until(ExpectedConditions.visibilityOfElementLocated(dayPicker));

        for (int i = 0; i < 40; i++) {

            int current = safeParse(getPickerValue(picker));

            if (current == targetDay) {
                System.out.println("Day selected: " + current);
                return;
            }

            swipeDown(picker);
            sleep(300);
        }

        throw new RuntimeException("Day not found: " + targetDay);
    }

    // =========================================================
    // ✅ YEAR (SMART + CYCLIC FIX)
    // =========================================================
    private void setYear(int targetYear) {

        WebElement picker = wait.until(ExpectedConditions.visibilityOfElementLocated(yearPicker));

        int lastValue = -1;

        for (int i = 0; i < 200; i++) {

            String text = getPickerValue(picker);
            if (text.isEmpty()) continue;

            int current = safeParse(text);

            if (current == targetYear) {
                System.out.println("Year selected: " + current);
                return;
            }

            // 🔥 Detect stuck (cyclic loop protection)
            if (current == lastValue) {
                swipeUp(picker);
                sleep(400);
                continue;
            }

            lastValue = current;

            // 🔥 Smart direction
            if (current < targetYear) {
                swipeUp(picker);
            } else {
                swipeDown(picker);
            }

            sleep(400);
        }

        // 🔥 FINAL FALLBACK (force reset)
        System.out.println("Retrying Year Selection...");

        for (int i = 0; i < 200; i++) {

            int current = safeParse(getPickerValue(picker));

            if (current == targetYear) {
                System.out.println("Year selected after retry: " + current);
                return;
            }

            swipeUp(picker);
            sleep(300);
        }

        throw new RuntimeException("Year not found: " + targetYear);
    }

    // =========================================================
    // ✅ GET VALUE
    // =========================================================
    private String getPickerValue(WebElement picker) {
        try {
            return picker.findElement(inputField).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    // =========================================================
    // ✅ SAFE PARSE
    // =========================================================
    private int safeParse(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return -1;
        }
    }

    // =========================================================
    // ✅ CLICK CONTINUE
    // =========================================================
    public void clickContinueDOB() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtnDOB)).click();
    }

    // =========================================================
    // ✅ SWIPE METHODS
    // =========================================================
    private void swipeUp(WebElement element) {
        swipe(element, true);
    }

    private void swipeDown(WebElement element) {
        swipe(element, false);
    }

    private void swipe(WebElement element, boolean up) {

        Rectangle rect = element.getRect();

        int centerX = rect.getX() + rect.getWidth() / 2;
        int startY = rect.getY() + (int) (rect.getHeight() * 0.7);
        int endY = rect.getY() + (int) (rect.getHeight() * 0.3);

        if (!up) {
            int temp = startY;
            startY = endY;
            endY = temp;
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(400), PointerInput.Origin.viewport(), centerX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(List.of(swipe));

        sleep(500);
    }

    // =========================================================
    // ✅ SLEEP
    // =========================================================
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ignored) {}
    }
}