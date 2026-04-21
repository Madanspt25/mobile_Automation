package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OtpApi {

    public static void sendOtp(String baseUrl, String endpoint, String phone) {

        Response response = RestAssured
                .given()
                .queryParam("phone", phone)
                .post(baseUrl + endpoint);

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to send OTP. Status: " + response.statusCode());
        }

        System.out.println("OTP sent successfully");
    }

    public static String getOtp(String baseUrl, String endpoint, String phone) {

        Response response = RestAssured
                .given()
                .queryParam("phone", phone)
                .get(baseUrl + endpoint);

        if (response.statusCode() == 200) {
            return response.jsonPath().getString("otp");
        }

        throw new RuntimeException("Failed to fetch OTP. Status: " + response.statusCode());
    }

    public static String getOtpWithRetry(String baseUrl, String endpoint, String phone) {

        RestAssured.baseURI = baseUrl;

        int maxAttempts = 8;
        int waitTime = 3000;

        try {
            // Initial wait for OTP generation
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= maxAttempts; i++) {
            try {
                System.out.println("Attempt " + i + " to fetch OTP for: " + phone);

                Response response = RestAssured
                        .given()
                        .queryParam("phone", phone)
                        .get(endpoint);

                if (response.statusCode() == 200) {

                    String otp = response.jsonPath().getString("otp");

                    System.out.println("Response: " + response.asString());

                    if (otp != null && !otp.isEmpty() && otp.matches("\\d{6}")) {
                        System.out.println("OTP received: " + otp);
                        return otp;
                    }
                }

                Thread.sleep(waitTime);

            } catch (Exception e) {
                System.out.println("Attempt " + i + " failed: " + e.getMessage());
            }
        }

        throw new RuntimeException("OTP not received after " + maxAttempts + " attempts for phone: " + phone);
    }
}