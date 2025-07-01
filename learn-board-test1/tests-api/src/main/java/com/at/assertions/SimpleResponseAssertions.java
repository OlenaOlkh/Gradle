package com.at.assertions;

import com.at.dto.SimpleResponse;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.assertj.core.api.SoftAssertions;

public class SimpleResponseAssertions {

    private SimpleResponse simpleResponse;
    private SoftAssertions softAssertions;

    public SimpleResponseAssertions(SimpleResponse simpleResponse) {
        this.simpleResponse = simpleResponse;
        this.softAssertions = new AutoCloseableSoftAssertions();
    }

    public SimpleResponseAssertions verifyMessage(String expectedMessage) {
        softAssertions.assertThat(simpleResponse.getMessage())
                .as("Message is not equal to expected.")
                .isEqualTo(expectedMessage);
        return this;
    }

    public SimpleResponseAssertions verifyStatus(String expectedStatus) {
        softAssertions.assertThat(simpleResponse.getStatus())
                .as("Status is not equal to expected.")
                .isEqualTo(expectedStatus);
        return this;
    }

    public SimpleResponseAssertions verifyHttpStatus(String expectedHttpStatus) {
        softAssertions.assertThat(simpleResponse.getHttpStatus())
                .as("Http status is not equal to expected.")
                .isEqualTo(expectedHttpStatus);
        return this;
    }

    public SimpleResponseAssertions verifyException(String expectedException) {
        softAssertions.assertThat(simpleResponse.getException())
                .as("Exception is not equal to expected.")
                .isEqualTo(expectedException);
        return this;
    }

    public void assertAll() {
        softAssertions.assertAll();
    }
}
