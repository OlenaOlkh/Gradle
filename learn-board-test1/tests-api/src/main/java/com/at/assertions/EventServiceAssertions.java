package com.at.assertions;

import com.at.dto.EventDto;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.assertj.core.api.SoftAssertions;

public class EventServiceAssertions {

    private EventDto eventDto;
    private SoftAssertions softAssertions;

    public EventServiceAssertions(EventDto eventDto) {
        this.eventDto = eventDto;
        this.softAssertions = new AutoCloseableSoftAssertions();
    }

    public EventServiceAssertions verifyEventId(String expectedId) {
        softAssertions.assertThat(eventDto.getId())
                .as("Event id is not equal to expected.")
                .isEqualTo(expectedId);
        return this;
    }

    public EventServiceAssertions verifyEventAdminEmail(String adminEmail) {
        softAssertions.assertThat(eventDto.getAdmin().getEmail())
                .as("Admin email is not equal to expected")
                .isEqualToIgnoringCase(adminEmail);
        return this;
    }

    public void assertAll() {
        softAssertions.assertAll();
    }
}
