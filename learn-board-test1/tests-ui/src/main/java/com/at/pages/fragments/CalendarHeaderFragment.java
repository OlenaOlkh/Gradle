package com.at.pages.fragments;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class CalendarHeaderFragment extends AbstractFragment {

    private final String todayButton = ".//button[text()='Today']";

    private final SelenideElement rootElement = $(".schedule>.filter");

    public CalendarHeaderFragment() {
        setRootElement(rootElement);
    }

    public SelenideElement getTodayButton() {
        return getRootElement().$x(todayButton);
    }

}
