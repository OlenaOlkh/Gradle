package stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import com.at.pagehelpers.HomePageHelper;
import com.at.pages.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageStepDefs {

    public HomePageHelper homePageHelper = new HomePageHelper();
    public HomePage homePage = new HomePage();

    @When("open Learn Board Home page")
    public void openLearnBoardHomePage() {
        homePage.visit();
    }

    @Then("Home page is opened")
    public void isHomePageOpened() {
        homePage.checkUrl();
    }

    @Then("user is logged in")
    public void verifyUserIsLoggedIn() {
        assertThat(homePageHelper.isUserLoggedIn())
                .as("User is not logged in.")
                .isTrue();
    }

}