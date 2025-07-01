package hooks;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.at.utils.DriverUtils.setChromeCapabilities;
import static com.at.utils.PropertyLoader.getInstance;
import static com.at.utils.WaitUtils.waitForPageToLoad;

import com.codeborne.selenide.Configuration;
import com.at.pagehelpers.LoginPageHelper;
import com.at.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebDriverHooks {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverHooks.class);

    @Before(order = 1)
    public void setBrowser() {
        String browser = getInstance().getProperty("browserName");
        Configuration.browser = browser;
        if (browser.equals("chrome")) {
            setChromeCapabilities();
        }
    }

    @Before(order = 2)
    public void openHomePage() {
        String baseUrl = getInstance().getProperty("baseUrl");
        Configuration.baseUrl = baseUrl;
        open(baseUrl);
    }

    @Before(order = 3)
    public void maximizeWindow() {
        getWebDriver().manage().window().maximize();
    }

    @Before(value = "@loginAsAdmin", order = 4)
    public void loginBeforeScenario() {
        LOGGER.info("Logging in as admin");

        String email = getInstance().getProperty("admin.email");
        String password = getInstance().getProperty("admin.password");

        new LoginPageHelper().logIn(email, password);
        waitForPageToLoad();
        if (!new HomePage().isCurrentPageUrlAsExpected()) {
            LOGGER.error("User was not logged in.");
        }
    }

    @After(order = 9999)
    public void attachScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }

    @After(order = 1)
    public void tearDown() {
        clearBrowserLocalStorage();
        clearBrowserCache();
        clearBrowserCookies();
        getWebDriver().quit();
    }

}