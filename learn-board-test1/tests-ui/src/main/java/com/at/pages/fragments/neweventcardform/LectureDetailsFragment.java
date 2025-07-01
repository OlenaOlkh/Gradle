package com.at.pages.fragments.neweventcardform;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import com.at.pages.fragments.AbstractFragment;

public class LectureDetailsFragment extends AbstractFragment {

    private final SelenideElement rootElement = $("div.event-details-section");

    public LectureDetailsFragment() {
        setRootElement(rootElement);
    }

}
