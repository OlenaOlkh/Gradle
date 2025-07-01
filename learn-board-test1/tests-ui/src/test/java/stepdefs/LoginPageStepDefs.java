package stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import com.at.pagehelpers.LoginPageHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageStepDefs {

    private final LoginPageHelper loginPageHelper = new LoginPageHelper();

    @When("enter email {string} into Email field")
    public void enterEmail(String email) {
        loginPageHelper.enterEmail(email);
    }

    @When("enter password {string} into Password field")
    public void enterPassword(String password) {
        loginPageHelper.enterPassword(password);
    }

    @When("click Submit button")
    public void clickSubmitButton() {
        loginPageHelper.clickSubmitButton();
    }

    @When("login with email {string} and password {string}")
    public void logInWithEmailAndPassword(String email, String password) {
        enterEmail(email);
        clickSubmitButton();
        enterPassword(password);
        clickSubmitButton();
    }

    @Then("email error message is displayed")
    public void verifyEmailErrorMessageIsDisplayed() {
        assertThat(loginPageHelper.isEmailErrorMessageDisplayed())
                .as("Email error message should be displayed.")
                .isTrue();
    }

    @Then("password error message is displayed")
    public void verifyPasswordErrorMessageIsDisplayed() {
        assertThat(loginPageHelper.isPasswordErrorMessageDisplayed())
                .as("Password error message should be displayed.")
                .isTrue();
    }

    @Then("Email field is empty")
    public void verifyEmailFieldIssEmpty() {
        assertThat(loginPageHelper.isEmailFieldEmpty())
                .as("Email field should be empty.")
                .isTrue();
    }

    @When("Password field is empty")
    public void verifyPasswordFieldIsEmpty() {
        assertThat(loginPageHelper.isPasswordFieldEmpty())
                .as("Password field should be empty.")
                .isTrue();
    }
}