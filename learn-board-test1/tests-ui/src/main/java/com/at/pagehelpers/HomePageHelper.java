package com.at.pagehelpers;

import com.at.pages.HomePage;

public class HomePageHelper {

    private final HomePage homePage;

    public HomePageHelper() {
        this.homePage = new HomePage();
    }

    public boolean isUserLoggedIn() {
        return homePage.getHeaderFragment().isUserAvatarDisplayed();
    }
}