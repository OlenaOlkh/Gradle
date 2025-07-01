package com.at.servicies;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class EventsAllService extends AbstractService {

    public EventsAllService(String baseUri, String basePath, String token) {
        super(baseUri, basePath, token);
    }

    public Response getAllEvents() {
        return getRequest("");
    }

    public Response getEventsOnDate(String stringDate) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("date", stringDate);
        return getRequest("", queryParams);
    }

    public Response getEventsOnDateInterval(String stringDateStart, String stringDateEnd) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("from", stringDateStart);
        queryParams.put("to", stringDateEnd);
        return getRequest("", queryParams);
    }
}
