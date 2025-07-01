package com.at.pages;

import static com.at.utils.DriverUtils.getCurrentURL;
import static com.at.utils.WaitUtils.waitForPageToLoad;
import static java.lang.String.format;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.at.utils.FragmentFactory;
import com.at.utils.annotations.PageUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPage.class);

    private String relativeUrl;
    private String urlFormat;

    private final FragmentFactory fragmentFactory = new FragmentFactory();

    protected FragmentFactory getFragmentFactory() {
        return fragmentFactory;
    }

    public void visit() {
        LOGGER.info("Opening page by URL: {}", getFullUrl());
        Selenide.open(getFullUrl());
    }

    public void checkUrl() {
        waitForPageToLoad();
        if (!isCurrentPageUrlAsExpected()) {
            throw new AssertionError(
                    format("Current page is wrong by url format.\nExpected url format: %s\nCurrent url: %s",
                            getUrlFormat(), getCurrentURL()));
        }
    }

    public boolean isCurrentPageUrlAsExpected() {
        Pattern pattern = Pattern.compile(getUrlFormat());
        Matcher matcher = pattern.matcher(getCurrentURL());
        return matcher.find();
    }

    protected String getUrlFormat() {
        if (urlFormat == null) {
            this.urlFormat = getBaseURL() + getRelativeUrl();
        }
        return urlFormat;
    }

    private String getFullUrl() {
        if (getRelativeUrl().equals("/")) {
            return getBaseURL();
        } else {
            return getBaseURL() + getRelativeUrl();
        }
    }

    private String getRelativeUrl() {
        if (relativeUrl == null) {
            if (getClass().isAnnotationPresent(PageUrl.class)) {
                relativeUrl = getClass().getAnnotation(PageUrl.class).value();
            } else {
                throw new IllegalStateException(
                        "Please set page URL using annotation @PagePath. Page URL must not be null.");
            }
        }
        return relativeUrl;
    }

    private String getBaseURL() {
        return Configuration.baseUrl;
    }

}