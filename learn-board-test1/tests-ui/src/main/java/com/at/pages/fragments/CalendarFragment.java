package com.at.pages.fragments;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class CalendarFragment extends AbstractFragment {

    private final SelenideElement rootElement = $("section.calendar");

    public CalendarFragment() {
        setRootElement(rootElement);
    }

}
