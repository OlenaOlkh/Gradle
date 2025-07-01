package com.at.assertions;

import com.at.dto.SimpleResponse;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.assertj.core.api.SoftAssertions;

public class OutlookServiceAssertions {

    private SimpleResponse simpleResponse;
    private SoftAssertions softAssertions;

    public OutlookServiceAssertions(SimpleResponse simpleResponse) {
        this.simpleResponse = simpleResponse;
        this.softAssertions = new AutoCloseableSoftAssertions();
    }

    public OutlookServiceAssertions verifyMessage(String expectedMessage) {
        softAssertions.assertThat(simpleResponse.getMessage())
                .as("Message is not equal to expected.")
                .isEqualTo(expectedMessage);
        return this;
    }

    public OutlookServiceAssertions verifyStatus(String expectedStatus) {
        softAssertions.assertThat(simpleResponse.getStatus())
                .as("Status is not equal to expected.")
                .isEqualTo(expectedStatus);
        return this;
    }

    public void assertAll() {
        softAssertions.assertAll();
    }
}
