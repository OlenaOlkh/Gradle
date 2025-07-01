package com.at.utils;

import com.codeborne.selenide.SelenideElement;
import com.at.pages.fragments.AbstractFragment;

import java.util.List;

public class FragmentFactory {

    public <T extends AbstractFragment> T createFragment(SelenideElement rootElement, Class<T> type) {
        T instance = createFragment(type);
        instance.setRootElement(rootElement);
        return instance;
    }

    public <T extends AbstractFragment> T createFragment(Class<T> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("Unable to create an instance of class " + type.getSimpleName(),
                    e.getCause());
        }
    }

    public <T extends AbstractFragment> List<T> createFragmentsFromSelenideElements(List<SelenideElement> elements,
            Class<T> type) {
        return elements.stream().map(element -> createFragment(element, type)).toList();
    }

}
