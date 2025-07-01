package com.at.utils;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.remote.CapabilityType.ACCEPT_INSECURE_CERTS;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverUtils {

    private DriverUtils() {
    }

    public static void setChromeCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-extensions");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;
        Configuration.browserCapabilities.setCapability(ACCEPT_INSECURE_CERTS, true);
    }

    public static String getCurrentURL() {
        return getWebDriver().getCurrentUrl();
    }
}