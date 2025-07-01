package com.at.event_tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.at.AbstractLearnBoardApiTest;
import com.at.assertions.EventServiceAssertions;
import com.at.assertions.OutlookServiceAssertions;
import com.at.assertions.SimpleResponseAssertions;
import com.at.dto.EventDto;
import com.at.dto.NewEventDto;
import com.at.dto.SimpleResponse;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import java.time.OffsetDateTime;

public class EventServiceSimpleTests extends AbstractLearnBoardApiTest {

    @Test(enabled = false)
    public void getAllEventsTest() {
        Response response = learnBoardAPI.eventsAllService.getAllEvents();
        System.out.println("Response: " + response.prettyPrint());
        response.then().statusCode(200);
    }

    @Test(enabled = false)
    public void getEventsOnDateTest() {
        Response response = learnBoardAPI.eventsAllService.getEventsOnDate("2022-01-17");
        System.out.println("Response: " + response.prettyPrint());
        response.then().statusCode(200);
    }

    @Test(enabled = false)
    public void getEventsOnDateIntervalTest() {
        Response response = learnBoardAPI.eventsAllService
                .getEventsOnDateInterval("2021-01-17", "2021-01-17");
        //System.out.println("Response: " + response.prettyPrint());
        response.then().statusCode(200);
    }

    @SneakyThrows
    @Test(enabled = false)
    public void postEventToExchangeTest() {
        OffsetDateTime dateFrom = OffsetDateTime.parse("2022-01-18T21:39:46.365Z");
        OffsetDateTime dateTo = OffsetDateTime.parse("2022-01-18T21:39:46.365Z");
        System.out.println("OffsetDateTime8: " + dateFrom);
        NewEventDto newEventDto = NewEventDto.builder()
                .adminEmail("Dmity_Kuznetsov5@epam.com")
                .description("the desc 1")
                .from(dateFrom)
                .to(dateTo)
                .groups("group1")
                .subject("subject1")
                .tags("tag1")
                .topic("topic 1")
                .trainersEmailList(new String[]{"Dmity_Kuznetsov5@epam.com"})
                .build();
        Response response = learnBoardAPI.eventsService.postEventToExchange(newEventDto);
        System.out.println("Response: " + response.prettyPrint());
        response.then().statusCode(201);
    }

    @Test(enabled = true)
    public void getEventByIdTest() {
        // dataBaseConnector.examplePrintTopFiveEvents();
        // String EVENT_ID = "AAMkADU5M2M4ZGJhLWZmZWQtNDY0Yy05ZjE3LTA2ZTNlNmVlNWEyZgBGAAAAAAC2tF5gUQNBQKUMnW7GdZlGBwBerHt4n5eSRI-GGG3bcUPpAAAAAAENAABerHt4n5eSRI-GGG3bcUPpAADR0vXUAAA=";
        // String EVENT_ID = "AAMkADU5M2M4ZGJhLWZmZWQtNDY0Yy05ZjE3LTA2ZTNlNmVlNWEyZgBGAAAAAAC2tF5gUQNBQKUMnW7GdZlGBwBerHt4n5eSRI-GGG3bcUPpAAAAAAENAABerHt4n5eSRI-GGG3bcUPpAADbZUC5AAA=";
        String adminEmail = "auto_epm-lstr_learn_board_dev@epam.com";
        String EVENT_ID = dataBaseConnector.exampleGetOneEventIdByAdminEmail(adminEmail);
        assertThat(EVENT_ID).as("We have at least one event for admin email = " + adminEmail).isNotEmpty();
        Response response = learnBoardAPI.eventsService.getEventById(EVENT_ID);
        System.out.println("Response: " + response.prettyPrint());
        String contentType = response.header("Content-Type");
        assertThat(contentType).isEqualToIgnoringCase("application/json");
        EventDto eventDto = response.then().statusCode(200).extract().as(EventDto.class);
        new EventServiceAssertions(eventDto)
                .verifyEventId(EVENT_ID)
                .verifyEventAdminEmail(adminEmail)
                .assertAll();
    }

    @Test(enabled = true)
    public void getEventByWrongIdTest() {
        Response response = learnBoardAPI.eventsService.getEventById(3);
        // System.out.println("Response: " + response.prettyPrint());
        String contentType = response.header("Content-Type");
        assertThat(contentType).isEqualToIgnoringCase("application/json");
        SimpleResponse simpleResponse = response
                .then()
                .statusCode(400)
                .extract().as(SimpleResponse.class);
        new SimpleResponseAssertions(simpleResponse)
                .verifyMessage("This event is not exist")
                .verifyException("EventNotFoundException")
                .verifyHttpStatus("BAD_REQUEST")
                .assertAll();
    }

    @Test(enabled = true)
    public void getAuthUserInfoTest() {
        Response response = learnBoardAPI.authService.getUserInfo();
        System.out.println("Response: " + response.prettyPrint());
        response.then().statusCode(200);
    }

    @Test(enabled = true)
    public void getOutlookLoginTest() {
        Response response = learnBoardAPI.outlookService.postLogin();
        String contentType = response.header("Content-Type");
        assertThat(contentType).isEqualToIgnoringCase("application/json");
        SimpleResponse simpleResponse = response
                .then()
                .statusCode(200)
                .extract().as(SimpleResponse.class);
        new SimpleResponseAssertions(simpleResponse)
                .verifyStatus("200")
                .verifyMessage("Authenticated to outlook")
                .assertAll();
    }

    @Test(enabled = true)
    public void getOutlookExportTest() {
        // It seems abnormal, but we need to make postLogin request before postExport request
        Response responseLogin = learnBoardAPI.outlookService.postLogin();
        SimpleResponse simpleResponseAfterLogin = responseLogin
                .then()
                .statusCode(200)
                .extract().as(SimpleResponse.class);
        new OutlookServiceAssertions(simpleResponseAfterLogin)
                .verifyStatus("200")
                .verifyMessage("Authenticated to outlook")
                .assertAll();

        Response responseExport = learnBoardAPI.outlookService.postExport();
        SimpleResponse simpleResponseExport = responseExport
                .then()
                .statusCode(200)
                .extract().as(SimpleResponse.class);
        new OutlookServiceAssertions(simpleResponseExport)
                .verifyStatus("200")
                .verifyMessage("Events exported to application database")
                .assertAll();
    }
}
