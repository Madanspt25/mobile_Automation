package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DOBPage;
import pages.SignupPage;
import pages.OTPPage;
import api.OtpService;

public class SignupTest extends BaseTest {

    @Test
    public void signupWithEmailTest() throws Exception {

        SignupPage signup = new SignupPage(driver);

        signup.clickCreateAccount();
        signup.clickEmailOption();

        // ✅ Dynamic email
        String email = "test" + System.currentTimeMillis() + "@gmail.com";
        String password = "Test#123";

        System.out.println("Using Email: " + email);

        signup.enterEmail(email);
        signup.enterPassword(password);
        signup.clickContinue(); // goes to DOB page


        // ✅ DOB AUTOMATION

        DOBPage dobPage = new DOBPage(driver);
        dobPage.setDobAndContinue("Mar", 16, 2010);


        // ✅ Use RETRY method (IMPORTANT)
        OtpService.sendOtp(email); // 🔥 trigger OTP

        String otp = OtpService.getOtpWithRetry(email); // 🔥 fetch OTP

        System.out.println("OTP Received: " + otp);

        // ✅ Strong validation
        Assert.assertNotNull(otp, "OTP is null");
        Assert.assertFalse(otp.isEmpty(), "OTP is empty");

        // =====================================================
        // ✅ ENTER OTP
        // =====================================================
        OTPPage otpPage = new OTPPage(driver);
        otpPage.enterOtp(otp);
        otpPage.clickContinue();
    }
}