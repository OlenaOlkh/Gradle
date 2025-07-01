package com.at.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import com.at.utils.annotations.PageUrl;
import lombok.Getter;

@PageUrl("https://login.microsoftonline.com/epam.com/oauth2/v2.0/authorize.+")
@Getter
public class LoginPage extends AbstractPage {

    private final SelenideElement emailField = $("input[type='email']");
    private final SelenideElement passwordField = $("input[type='password']");
    private final SelenideElement submitButton = $("input[type='submit']");
    private final SelenideElement emailErrorMessage = $("div#usernameError.error");
    private final SelenideElement passwordErrorMessage = $("div#passwordError.error");

    @Override
    protected String getUrlFormat() {
        return getClass().getAnnotation(PageUrl.class).value();
    }
}