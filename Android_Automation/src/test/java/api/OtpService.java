package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OtpService {

    // ✅ Use SAME endpoints as working project
    private static final String SEND_OTP_URL =
            "https://api.sportstech.app/user-mgmt-svc/api/user/signup/otp";

    private static final String GET_OTP_URL =
            "https://api.sportstech.app/verification-svc/api/test/login-otp";

    // 🔥 IMPORTANT → API KEY
    private static final String API_KEY =
            "ee9bb6b1-da1f-4735-9584-19655dff38ac";


    // ============================================
    // ✅ SEND OTP
    // ============================================
    public static void sendOtp(String email) {

        String body = "{ \"email\": \"" + email + "\" }";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("x-api-key", API_KEY)   // 🔥 REQUIRED
                .body(body)
                .when()
                .post(SEND_OTP_URL);

        System.out.println("Send OTP Status: " + response.getStatusCode());
        System.out.println("Send OTP Response: " + response.asString());

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("❌ Send OTP Failed");
        }
    }


    // ============================================
    // ✅ GET OTP (IMPORTANT FIX)
    // ============================================
    public static String getOtp(String email) {

        String body = "{ \"email\": \"" + email + "\" }";

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("x-api-key", API_KEY)   // 🔥 REQUIRED
                .body(body)
                .when()
                .post(GET_OTP_URL);             // 🔥 POST (NOT GET)

        System.out.println("Get OTP Status: " + response.getStatusCode());
        System.out.println("Get OTP Response: " + response.asString());

        if (response.getStatusCode() != 200) {
            return null; // retry will handle
        }

        return response.jsonPath().getString("otp");
    }


    // ============================================
    // 🔁 RETRY LOGIC (BEST PRACTICE)
    // ============================================
    public static String getOtpWithRetry(String email) {

        String otp = null;

        for (int i = 0; i < 10; i++) {

            System.out.println("🔁 Attempt " + (i + 1) + " to fetch OTP...");

            otp = getOtp(email);

            if (otp != null && !otp.isEmpty()) {
                return otp;
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        throw new RuntimeException("❌ OTP not received after retries");
    }
}