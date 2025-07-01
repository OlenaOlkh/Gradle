package com.at;

import com.at.servicies.AuthService;
import com.at.servicies.EventsAllService;
import com.at.servicies.EventsService;
import com.at.servicies.OutlookService;

public class LearnBoardAPI {

    public OutlookService outlookService;
    public AuthService authService;
    public EventsService eventsService;
    public EventsAllService eventsAllService;

    public static String BASE_URI = "";
    public static String API_TOKEN = "";
    public static String API_BASE_PATH_AUTH = "auth/userinfo";
    public static String API_BASE_PATH_EVENTS = "events/";
    public static String API_BASE_PATH_EVENTS_ALL = "events/all";
    public static String API_BASE_PATH_OUTLOOK = "outlook/";

    private LearnBoardAPI() {
        eventsService = new EventsService(BASE_URI, API_BASE_PATH_EVENTS, API_TOKEN);
        eventsAllService = new EventsAllService(BASE_URI, API_BASE_PATH_EVENTS_ALL, API_TOKEN);
        authService = new AuthService(BASE_URI, API_BASE_PATH_AUTH, API_TOKEN);
        outlookService = new OutlookService(BASE_URI, API_BASE_PATH_OUTLOOK, API_TOKEN);
    }

    public static LearnBoardAPI init(String baseUri, String token) {
        BASE_URI = baseUri;
        API_TOKEN = token;
        return new LearnBoardAPI();
    }

}
