package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.ProfilePage;

public class ProfileTest extends BaseTest {

    @Test
    public void verifyProfileSections() throws InterruptedException {

        ProfilePage profilePage = new ProfilePage(driver);

        // Step 1: Navigate to Profile
        System.out.println("Navigating to Profile Page");
        profilePage.openProfile();
        Thread.sleep(3000);

        // My Dashboard
        System.out.println("Visiting: My Dashboard");
        profilePage.clickMyDashboard();
        Thread.sleep(2000);
        profilePage.goBack();
        System.out.println("Returned from: My Dashboard");

        // Memberships
        System.out.println("Visiting: Memberships");
        profilePage.clickMemberships();
        Thread.sleep(2000);
        profilePage.goBack();
        System.out.println("Returned from: Memberships");

        // Edit Login Methods
        System.out.println("Visiting: Edit Login Methods");
        profilePage.clickEditLoginMethods();
        Thread.sleep(2000);
        profilePage.goBack();
        System.out.println("Returned from: Edit Login Methods");

        // Basic Information
        System.out.println("Visiting: Basic Information");
        profilePage.clickBasicInformation();
        Thread.sleep(2000);
        profilePage.goBack();
        System.out.println("Returned from: Basic Information");

        // Primary Goal
        System.out.println("Visiting: Primary Goal");
        profilePage.clickPrimaryGoal();
        Thread.sleep(2000);
        profilePage.goBack();
        System.out.println("Returned from: Primary Goal");

        // Fitness Information
        System.out.println("Visiting: Fitness Information");
        profilePage.clickFitnessInformation();
        Thread.sleep(2000);
        profilePage.goBack();
        System.out.println("Returned from: Fitness Information");
    }
}