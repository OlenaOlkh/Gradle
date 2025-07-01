package com.at.pages.fragments;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class NavigationMenuFragment extends AbstractFragment {

    private final String userEmail = "p.dropdown-menu__list-item-wrapper";
    private final String logOutButton = ".//button[text()='Log out']";

    private final SelenideElement rootElement = $("ul.dropdown-menu__list");

    public NavigationMenuFragment() {
        setRootElement(rootElement);
    }

    public String getUserEmailText() {
        return getUserEmail().shouldBe(visible).getText();
    }

    public SelenideElement getUserEmail() {
        return getRootElement().$(userEmail);
    }

    public SelenideElement getLogOutButton() {
        return getRootElement().$x(logOutButton);
    }
}
