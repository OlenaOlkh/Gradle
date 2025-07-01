package com.at.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private final Properties properties = new Properties();
    private static final String PROPERTIES_FILE_PATH = "src/main/resources/project.properties";

    public PropertiesReader() {
        try (FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTestUserLogin() {
        return properties.getProperty("test_user_login");
    }

    public String getTestUserPassword() {
        return properties.getProperty("test_user_password");
    }

    public String getAppClientId() {
        return properties.getProperty("app_client_id");
    }

    public String getAppSecretId() {
        return properties.getProperty("app_secret_id");
    }

    public String getEpamAuthURL() {
        return properties.getProperty("epam_auth_url");
    }
}
