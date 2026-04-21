package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    // Optional: direct getters (clean usage)
    public static String getBaseUrl() {
        return get("baseUrl");
    }

    public static String getAuthPath() {
        return get("authBasePath");
    }

    public static String getOtpEndpoint() {
        return get("getOtpEndpoint");
    }

}