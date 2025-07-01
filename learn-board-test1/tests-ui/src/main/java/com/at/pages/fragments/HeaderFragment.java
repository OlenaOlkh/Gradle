package com.at.pages.fragments;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class HeaderFragment extends AbstractFragment {

    private final String logo = "a.logo";
    private final String scheduleTab = ".//button[text()='Schedule']";
    private final String settingsTab = ".//button[text()='Settings']";
    private final String reportsTab = ".//button[text()='Reports']";

    private final String bellIcon = ".//i[@class='bell-icon']";
    private final String newEventButton = ".//button[text()='New Event']";
    private final String darkModeSwitcher = ".//p[text()='Dark mode']/../label[@class='toggle-button__switch']";
    private final String infoLink = "a.info-link";
    private final String userAvatar = ".avatar";
    private final String myAccountArrow = "div.open-dropdown-button>button";

    private final String navigationMenuRootElementLocator = "ul.dropdown-menu__list";

    private final SelenideElement rootElement = $(".header");

    public HeaderFragment() {
        setRootElement(rootElement);
    }

    public NavigationMenuFragment getNavigationMenuFragment() {
        return getFragmentFactory().createFragment(getRootElement().$(navigationMenuRootElementLocator),
                NavigationMenuFragment.class);
    }

    public boolean isLogoDisplayed() {
        return getLogo().isDisplayed();
    }

    public void openMyAccount() {
        if (!getNavigationMenuFragment().isDisplayed()) {
            getMyAccountArrow().click();
        }
    }

    public boolean isScheduleTabDisplayed() {
        return getScheduleTab().isDisplayed();
    }

    public boolean isSettingsTabDisplayed() {
        return getSettingsTab().isDisplayed();
    }

    public boolean isReportsTabDisplayed() {
        return getReportsTab().isDisplayed();
    }

    public boolean isBellIconDisplayed() {
        return getBellIcon().isDisplayed();
    }

    public boolean isNewEventButtonDisplayed() {
        return getNewEventButton().isDisplayed();
    }

    public boolean isDarkModeSwitcherDisplayed() {
        return getDarkModeSwitcher().isDisplayed();
    }

    public boolean isInfoLinkDisplayed() {
        return getInfoLink().isDisplayed();
    }

    public boolean isUserAvatarDisplayed() {
        return getUserAvatar().isDisplayed();
    }

    public SelenideElement getLogo() {
        return getRootElement().$(logo);
    }

    public SelenideElement getScheduleTab() {
        return getRootElement().$x(scheduleTab);
    }

    public SelenideElement getSettingsTab() {
        return getRootElement().$x(settingsTab);
    }

    public SelenideElement getReportsTab() {
        return getRootElement().$x(reportsTab);
    }

    public SelenideElement getBellIcon() {
        return getRootElement().$x(bellIcon);
    }

    public SelenideElement getNewEventButton() {
        return getRootElement().$x(newEventButton);
    }

    public SelenideElement getDarkModeSwitcher() {
        return getRootElement().$x(darkModeSwitcher);
    }

    public SelenideElement getInfoLink() {
        return getRootElement().$(infoLink);
    }

    public SelenideElement getUserAvatar() {
        return getRootElement().$(userAvatar);
    }

    public SelenideElement getMyAccountArrow() {
        return getRootElement().$(myAccountArrow);
    }

}
