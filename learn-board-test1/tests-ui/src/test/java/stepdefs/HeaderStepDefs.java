package stepdefs;

import static com.at.utils.WaitUtils.doWait;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

import com.at.pages.fragments.HeaderFragment;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HeaderStepDefs {

    public HeaderFragment headerFragment = new HeaderFragment();

    @When("open My account menu")
    public void openMyAccountMenu() {
        headerFragment.openMyAccount();
    }

    @Then("user email is equal to {string}")
    public void userEmailIsEqualToExpected(String expectedEmail) {
        String actualEmail = headerFragment.getNavigationMenuFragment().getUserEmailText();
        String formattedExpectedEmail = expectedEmail.toLowerCase().replace("@epam.com", EMPTY);

        doWait(5).untilAsserted(() -> assertThat(actualEmail)
                .as("Actual user email is not equal to expected.")
                .isEqualToIgnoringCase(formattedExpectedEmail));
    }

    @Then("logo is displayed")
    public void verifyLogoIsDisplayed() {
        assertThat(headerFragment.isLogoDisplayed())
                .as("Logo should be displayed.")
                .isTrue();
    }

    @Then("Schedule tab is displayed")
    public void verifyScheduleTabIsDisplayed() {
        assertThat(headerFragment.isScheduleTabDisplayed())
                .as("Schedule tab should be displayed.")
                .isTrue();
    }

    @Then("Settings tab is displayed")
    public void verifySettingsTabIsDisplayed() {
        assertThat(headerFragment.isSettingsTabDisplayed())
                .as("Settings tab should be displayed.")
                .isTrue();
    }

    @Then("Reports tab is displayed")
    public void verifyReportsTabIsDisplayed() {
        assertThat(headerFragment.isReportsTabDisplayed())
                .as("Reports tab should be displayed.")
                .isTrue();
    }

    @Then("Bell icon is displayed")
    public void verifyBellIconIsDisplayed() {
        assertThat(headerFragment.isBellIconDisplayed())
                .as("Bell icon should be displayed.")
                .isTrue();
    }

    @Then("New Event button is displayed")
    public void verifyNewEventButtonIsDisplayed() {
        assertThat(headerFragment.isNewEventButtonDisplayed())
                .as("New Event button should be displayed.")
                .isTrue();
    }

    @Then("Dark Mode switcher is displayed")
    public void verifyDarkModeOptionIsDisplayed() {
        assertThat(headerFragment.isDarkModeSwitcherDisplayed())
                .as("Dark Mode switcher should be displayed.")
                .isTrue();
    }

    @Then("Info link is displayed")
    public void verifyInfoLinkIsDisplayed() {
        assertThat(headerFragment.isInfoLinkDisplayed())
                .as("Info link should be displayed.")
                .isTrue();
    }

    @Then("user avatar is displayed")
    public void verifyUserAvatarIsDisplayed() {
        assertThat(headerFragment.isUserAvatarDisplayed())
                .as("User avatar should be displayed.")
                .isTrue();
    }

}
