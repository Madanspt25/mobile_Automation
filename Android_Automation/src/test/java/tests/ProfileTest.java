package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.ProfilePage;

public class ProfileTest extends BaseTest {

    @Test
    public void verifyProfileSections() throws InterruptedException {

        ProfilePage profilePage = new ProfilePage(driver);

        // Profile page is already open
        System.out.println("Profile Page is open");

        // My Dashboard
        System.out.println("Visiting: My Dashboard");
        profilePage.openMyDashboard();
        Thread.sleep(2000);
        driver.navigate().back();
        System.out.println("Returned from: My Dashboard");

        // Memberships
        System.out.println("Visiting: Memberships");
        profilePage.clickMemberships();
        Thread.sleep(2000);
        driver.navigate().back();
        System.out.println("Returned from: Memberships");

        // Edit Login Methods
        System.out.println("Visiting: Edit Login Methods");
        profilePage.clickEditLoginMethods();
        Thread.sleep(2000);
        driver.navigate().back();
        System.out.println("Returned from: Edit Login Methods");

        // Basic Information
        System.out.println("Visiting: Basic Information");
        profilePage.clickBasicInformation();
        Thread.sleep(2000);
        driver.navigate().back();
        System.out.println("Returned from: Basic Information");

        // Primary Goal
        System.out.println("Visiting: Primary Goal");
        profilePage.clickPrimaryGoal();
        Thread.sleep(2000);
        driver.navigate().back();
        System.out.println("Returned from: Primary Goal");

        // Fitness Information
        System.out.println("Visiting: Fitness Information");
        profilePage.clickFitnessInformation();
        Thread.sleep(2000);
        driver.navigate().back();
        System.out.println("Returned from: Fitness Information");

        // ===== RIGHT PANEL =====
        System.out.println("Visiting: Health Connect");
        profilePage.clickHealthConnect();
        Thread.sleep(2000);
        profilePage.goBackToProfile();
        System.out.println("Returned from:Health Connect ");

        System.out.println("Visiting: Connect Wearables");
        profilePage.clickConnectWearables();
        Thread.sleep(2000);
        profilePage.goBackToProfile();
        System.out.println("Returned from: Connect Wearables");

        System.out.println("Visiting: Goal Settings");
        profilePage.clickGoalSettings();
        Thread.sleep(2000);
        profilePage.goBackToProfile();
        System.out.println("Returned from: Goal Settings");

        System.out.println("Visiting: Unit Of Measurement");
        profilePage.clickUnitOfMeasurement();
        Thread.sleep(2000);
        profilePage.goBackToProfile();
        System.out.println("Returned from: Unit Of Measurement");

        System.out.println("Visiting: FAQ's");
        profilePage.clickFaqs();
        Thread.sleep(2000);
        profilePage.goBackToProfile();
        System.out.println("Returned from:  FAQ's");

        System.out.println("Visiting: Privacy Policy");
        profilePage.clickPrivacyPolicy();
        Thread.sleep(2000);
        profilePage.goBackToProfile();
        System.out.println("Returned from: Privacy Policy");

        System.out.println("Visiting: Rate The App");
        profilePage.clickRateTheApp();
        Thread.sleep(2000);
        profilePage.goBackToProfile();
        System.out.println("Returned from: Rate The App");

        System.out.println("Visiting: Contact Us");
        profilePage.clickContactUs();

        Thread.sleep(2000); // Wait to observe the page

        profilePage.goBackToProfile();
        System.out.println("Returned from: Contact Us");
    }
}