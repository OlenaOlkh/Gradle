package com.at.pagehelpers;

import static com.codeborne.selenide.Condition.visible;
import static com.at.utils.WaitUtils.waitForDisplayedIgnoringError;
import static java.util.Objects.requireNonNull;

import com.at.pages.LoginPage;

public class LoginPageHelper {

    private final LoginPage loginPage;

    public LoginPageHelper() {
        this.loginPage = new LoginPage();
    }

    public void enterEmail(String email) {
        loginPage.getEmailField().shouldBe(visible).setValue(email);
    }

    public void enterPassword(String password) {
        loginPage.getPasswordField().shouldBe(visible).setValue(password);
    }

    public void clickSubmitButton() {
        loginPage.getSubmitButton().click();
    }

    public boolean isEmailErrorMessageDisplayed() {
        waitForDisplayedIgnoringError(loginPage.getEmailErrorMessage());
        return loginPage.getEmailErrorMessage().isDisplayed();
    }

    public boolean isPasswordErrorMessageDisplayed() {
        waitForDisplayedIgnoringError(loginPage.getPasswordErrorMessage());
        return loginPage.getPasswordErrorMessage().isDisplayed();
    }

    public boolean isEmailFieldEmpty() {
        return requireNonNull(loginPage.getEmailField().getValue()).isEmpty();
    }

    public boolean isPasswordFieldEmpty() {
        return requireNonNull(loginPage.getPasswordField().getValue()).isEmpty();
    }

    public void logIn(String email, String password) {
        enterEmail(email);
        clickSubmitButton();
        enterPassword(password);
        clickSubmitButton();
    }
}