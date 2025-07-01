package com.at.event_tests;

import com.at.AbstractLearnBoardApiTest;
import com.at.assertions.SimpleResponseAssertions;
import com.at.dto.NewEventDto;
import com.at.dto.SimpleResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.time.OffsetDateTime;

public class EventServiceTests extends AbstractLearnBoardApiTest {

    @Test(enabled = true)
    public void postEventWrongAdminEmailTest() {
        String adminEmail = "Dmity_Kuznetsov5@epam.com";
        System.out.println("Test event creation when adminEmail = " + adminEmail);
        OffsetDateTime dateFrom = OffsetDateTime.parse("2022-01-18T21:39:46.365Z");
        OffsetDateTime dateTo = OffsetDateTime.parse("2022-01-18T21:39:46.365Z");
        NewEventDto newEventDto = NewEventDto.builder()
                .adminEmail(adminEmail)
                .description("the desc 1")
                .from(dateFrom)
                .to(dateTo)
                .groups("group1")
                .subject("subject1")
                .tags("tag1")
                .topic("topic 1")
                .trainersEmailList(new String[]{"Dmitry_Kuznetsov5@epam.com"})
                .build();
        Response response = learnBoardAPI.eventsService.postEventToExchange(newEventDto);
        System.out.println("Response: " + response.prettyPrint());
        SimpleResponse simpleResponse = response
                .then()
                .statusCode(400)
                .extract().as(SimpleResponse.class);
        new SimpleResponseAssertions(simpleResponse)
                .verifyMessage("There is no user with email = " + adminEmail)
                .verifyException("UserNotFoundException")
                .verifyHttpStatus("BAD_REQUEST")
                .assertAll();

        Response responseLogin = learnBoardAPI.outlookService.postLogin();
        System.out.println("Response login: " + responseLogin.prettyPrint());
        responseLogin.then().statusCode(200);
        Response responseExport = learnBoardAPI.outlookService.postExport();
        System.out.println("responseExport: " + responseExport.prettyPrint());
        responseExport.then().statusCode(200);
    }

    @Test(enabled = true)
    public void postEventCorrectAdminAsTrainerEmailTest() {
        // adminEmail is a trainer
        String adminEmail = "Dmitry_Kuznetsov5@epam.com";
        System.out.println("Test event creation when adminEmail = " + adminEmail);
        OffsetDateTime dateFrom = OffsetDateTime.parse("2022-01-18T21:39:46.365Z");
        OffsetDateTime dateTo = OffsetDateTime.parse("2022-01-18T21:39:46.365Z");
        NewEventDto newEventDto = NewEventDto.builder()
                .adminEmail(adminEmail)
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
        Response responseLogin = learnBoardAPI.outlookService.postLogin();
        System.out.println("Response login: " + responseLogin.prettyPrint());
        responseLogin.then().statusCode(200);
        Response responseExport = learnBoardAPI.outlookService.postExport();
        System.out.println("responseExport: " + responseExport.prettyPrint());
        responseExport.then().statusCode(200);
    }

    @Test(enabled = true)
    public void postEventCorrectAdminAsAdminEmailTest() {
        // adminEmail is an admin
        String adminEmail = "auto_epm-lstr_learn_board_dev@epam.com";
        System.out.println("Test event creation when adminEmail = " + adminEmail);
        OffsetDateTime dateFrom = OffsetDateTime.parse("2022-01-18T21:39:46.365Z");
        OffsetDateTime dateTo = OffsetDateTime.parse("2022-01-18T21:39:46.365Z");
        NewEventDto newEventDto = NewEventDto.builder()
                .adminEmail(adminEmail)
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
        Response responseLogin = learnBoardAPI.outlookService.postLogin();
        System.out.println("Response login: " + responseLogin.prettyPrint());
        responseLogin.then().statusCode(200);
        Response responseExport = learnBoardAPI.outlookService.postExport();
        System.out.println("responseExport: " + responseExport.prettyPrint());
        responseExport.then().statusCode(200);
    }

}
