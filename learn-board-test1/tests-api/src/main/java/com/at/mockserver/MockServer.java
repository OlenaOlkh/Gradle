package com.at.mockserver;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;

import java.net.URI;
import java.net.URISyntaxException;

public class MockServer {

    private static MockServer instance;
    private static String MOCK_SERVER_HOST = "localhost";
    private static int MOCK_SERVER_PORT = 8081;
    private static String MOCK_SERVER_PATH = "";
    private static WireMockServer wireMockServer;

    public MockServer() {
    }

    public static MockServer getInstance() {
        if (null == instance) {
            instance = new MockServer();
        }
        return instance;
    }

    public void startServer(String apiBaseUrl) throws URISyntaxException {
        if (!apiBaseUrl.equals("")) {
            URI uri = new URI(apiBaseUrl);
            MOCK_SERVER_HOST = uri.getHost();
            MOCK_SERVER_PORT = uri.getPort();
            MOCK_SERVER_PATH = uri.getPath();
        }
        WireMockConfiguration wireMockConfiguration = WireMockConfiguration
                .wireMockConfig()
                .port(MOCK_SERVER_PORT)
                .extensions(new ResponseTemplateTransformer(false));
        wireMockServer = new WireMockServer(wireMockConfiguration);
        wireMockServer.start();
        WireMock.configureFor(MOCK_SERVER_HOST, MOCK_SERVER_PORT);
        addStubEventsAll();
        addStubEventById();
        addStubEventsOnDate();
        addStubEventsOnDateInterval();
        addStubAuthUserInfo();
        addStubOutlookLogin();
        addStubOutlookExport();
        addStubEventsPostNewEvent();
    }

    private void addStubOutlookExport() {
        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
        String jsonResponse = "{\n"
                + "    \"message\": \"Events exported to application database\",\n"
                + "    \"status\": 200\n"
                + "}";
        mockResponse.withStatus(200).withHeader("Content-Type", "application/json")
                .withBody(jsonResponse);
        WireMock.stubFor(
                WireMock.post("" + MOCK_SERVER_PATH + "/outlook/export")
                        .willReturn(mockResponse)
        );
    }

    private void addStubOutlookLogin() {
        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
        String jsonResponse = "{\n"
                + "    \"message\": \"Authenticated to outlook\",\n"
                + "    \"status\": 200\n"
                + "}";
        mockResponse.withStatus(200).withHeader("Content-Type", "application/json")
                .withBody(jsonResponse);
        WireMock.stubFor(
                WireMock.post("" + MOCK_SERVER_PATH + "/outlook/login")
                        .willReturn(mockResponse)
        );
    }

    private void addStubAuthUserInfo() {
        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
        mockResponse.withStatus(200)
                .withBody("i'm the stub for auth user info");
        WireMock.stubFor(
                WireMock.get("" + MOCK_SERVER_PATH + "/auth/userinfo")
                        .willReturn(mockResponse)
        );
    }

    private void addStubEventsOnDateInterval() {
        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
        mockResponse.withStatus(200)
                .withBody("i'm the stub for date interval 2023-09-10 - 2023-10-10");
        WireMock.stubFor(
                WireMock.get("" + MOCK_SERVER_PATH + "/events/all?from=2021-09-10&to=2021-10-10")
                        .willReturn(mockResponse)
        );
    }

    private void addStubEventsAll() {
        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
        mockResponse.withStatus(200)
                .withBody("i'm a mock");
        WireMock.stubFor(
                WireMock.get("" + MOCK_SERVER_PATH + "/events/all")
                        .willReturn(mockResponse)
        );
    }

    private void addStubEventById() {
        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
        String jsonResponse = "{\n"
                + "    \"id\": \"AAMkADU5M2M4ZGJhLWZmZWQtNDY0Yy05ZjE3LTA2ZTNlNmVlNWEyZgBGAAAAAAC2tF5gUQNBQKUMnW7GdZlGBwBerHt4n5eSRI-GGG3bcUPpAAAAAAENAABerHt4n5eSRI-GGG3bcUPpAADR0vXUAAA=\",\n"
                + "    \"title\": \"Pre-interview JAVA\",\n"
                + "    \"admin\": {\n"
                + "        \"id\": 1,\n"
                + "        \"objectId\": null,\n"
                + "        \"email\": \"auto_epm-lstr_learn_board_dev@epam.com\",\n"
                + "        \"displayName\": null,\n"
                + "        \"upsaId\": null,\n"
                + "        \"role\": {\n"
                + "            \"id\": 1,\n"
                + "            \"roleName\": \"admin\"\n"
                + "        }\n"
                + "    },\n"
                + "    \"description\": \"\",\n"
                + "    \"topic\": null,\n"
                + "    \"trainers\": [\n"
                + "        \n"
                + "    ],\n"
                + "    \"startDateTime\": \"2022-01-24T13:00:00\",\n"
                + "    \"finishDateTime\": \"2022-01-24T14:30:00\",\n"
                + "    \"tags\": \"\",\n"
                + "    \"teamsLink\": null,\n"
                + "    \"studentGroups\": \"\",\n"
                + "    \"students\": \"\"\n"
                + "}";
        mockResponse.withStatus(200).withHeader("Content-Type", "application/json")
                .withBody(jsonResponse);
        String urlEncodedId = "AAMkADU5M2M4ZGJhLWZmZWQtNDY0Yy05ZjE3LTA2ZTNlNmVlNWEyZgBGAAAAAAC2tF5gUQNBQKUMnW7GdZlGBwBerHt4n5eSRI-GGG3bcUPpAAAAAAENAABerHt4n5eSRI-GGG3bcUPpAADR0vXUAAA%3D";
        WireMock.stubFor(
                WireMock.get("" + MOCK_SERVER_PATH + "/events/" + urlEncodedId + "?eventId=Long")
                        .willReturn(mockResponse)
        );
    }

    private void addStubEventsOnDate() {
        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
        mockResponse.withStatus(200)
                .withBody("i'm the stub for date 2021-10-10");
        WireMock.stubFor(
                WireMock.get("" + MOCK_SERVER_PATH + "/events/all?date=2021-10-10")
                        .willReturn(mockResponse)
        );
    }

    private void addStubEventsPostNewEvent() {
        ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
        mockResponse.withStatus(201)
                .withBody("i'm the stub for post new event");
        WireMock.stubFor(
                WireMock.post(MOCK_SERVER_PATH + "/events/?eventTemplate=EventTemplate")
                        .willReturn(mockResponse));
    }

    public void stopServer() {
        if (null != wireMockServer && wireMockServer.isRunning()) {
            wireMockServer.shutdownServer();
        }
    }
}
