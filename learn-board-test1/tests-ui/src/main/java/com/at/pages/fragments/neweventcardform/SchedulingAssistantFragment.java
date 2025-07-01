package com.at.pages.fragments.neweventcardform;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import com.at.pages.fragments.AbstractFragment;

public class SchedulingAssistantFragment extends AbstractFragment {

    private final String numberOfOccurrencesField = "input[name='number-of-occurrences']";

    private final SelenideElement rootElement = $(".repeat-details");

    public SchedulingAssistantFragment() {
        setRootElement(rootElement);
    }

    public SelenideElement getNumberOfOccurrencesField() {
        return getRootElement().$(numberOfOccurrencesField);
    }

}
