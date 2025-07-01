package com.at.servicies;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class EventsService extends AbstractService {

    public EventsService(String baseUri, String basePath, String token) {
        super(baseUri, basePath, token);
    }

    public Response getEventById(Integer id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("eventId", "Long");
        return getRequest(id.toString(), queryParams);
    }

    public Response getEventById(String id) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("eventId", "Long");
        return getRequest(id, queryParams);
    }

    public Response postEventToExchange(String jsonString) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("eventTemplate", "EventTemplate");
        return postRequest("", queryParams, jsonString);
    }

    public Response postEventToExchange(Object event) {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("eventTemplate", "EventTemplate");
        return postRequest("", queryParams, event);
    }

}
