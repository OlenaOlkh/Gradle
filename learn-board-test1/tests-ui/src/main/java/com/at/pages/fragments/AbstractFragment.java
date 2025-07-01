package com.at.pages.fragments;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;
import com.at.utils.FragmentFactory;

public abstract class AbstractFragment {

    private FragmentFactory fragmentFactory = new FragmentFactory();

    private SelenideElement rootElement;

    protected FragmentFactory getFragmentFactory() {
        return fragmentFactory;
    }

    public void setRootElement(SelenideElement rootElement) {
        this.rootElement = rootElement;
    }

    protected SelenideElement getRootElement() {
        return rootElement;
    }

    public boolean isDisplayed() {
        return getRootElement().isDisplayed();
    }

    public void waitForVisible() {
        getRootElement().shouldBe(visible);
    }

}
