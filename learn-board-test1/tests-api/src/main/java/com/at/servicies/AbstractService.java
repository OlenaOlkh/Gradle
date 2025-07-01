package com.at.servicies;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.util.Map;

public abstract class AbstractService {

    protected RequestSpecification requestSpecification;

    public AbstractService(String baseUri, String basePath, String token) {
        requestSpecification =
                new RequestSpecBuilder()
                        .setBaseUri(baseUri)
                        .setBasePath(basePath)
                        .setAuth(oauth2(token))
                        .setContentType(ContentType.JSON)
                        .build();
        // .build().filters(new RequestLoggingFilter());
        // .build().filters(new ResponseLoggingFilter());
        enableLoggingOfRequestAndResponseIfValidationFails();
    }

    protected Response makeRequest(Method method, String uri) {
        Response response = given(requestSpecification).request(method, uri);
        return response;
    }

    protected Response makeRequest(Method method, String uri, Map<String, Object> queryParams) {
        RequestSpecification specification = given(requestSpecification);
        addParameters(specification, queryParams);
        Response response = specification.request(method, uri);
        return response;
    }

    protected Response makeRequest(Method method, String uri, Map<String, Object> queryParams, String jsonString) {
        RequestSpecification specification = given(requestSpecification);
        specification.body(jsonString);
        addParameters(specification, queryParams);
        Response response = specification.request(method, uri);
        return response;
    }

    protected Response makeRequest(Method method, String uri, Map<String, Object> queryParams, Object event) {
        RequestSpecification specification = given(requestSpecification);
        specification.body(event);
        addParameters(specification, queryParams);
        Response response = specification.request(method, uri);
        return response;
    }

    protected Response getRequest(String uri) {
        return makeRequest(Method.GET, uri);
    }

    protected Response getRequest(String uri, Map<String, Object> queryParams) {
        return makeRequest(Method.GET, uri, queryParams);
    }

    protected Response putRequest(String uri) {
        return makeRequest(Method.PUT, uri);
    }

    protected Response putRequest(String uri, Map<String, Object> queryParams) {
        return makeRequest(Method.PUT, uri, queryParams);
    }

    protected Response postRequest(String uri) {
        return makeRequest(Method.POST, uri);
    }

    protected Response postRequest(String uri, Map<String, Object> queryParams) {
        return makeRequest(Method.POST, uri, queryParams);
    }

    protected Response postRequest(String uri, Map<String, Object> queryParams, String jsonString) {
        return makeRequest(Method.POST, uri, queryParams, jsonString);
    }

    protected Response postRequest(String uri, Map<String, Object> queryParams, Object event) {
        return makeRequest(Method.POST, uri, queryParams, event);
    }

    protected Response deleteRequest(String uri) {
        return makeRequest(Method.DELETE, uri);
    }

    protected void addParameters(RequestSpecification requestSpecification, Map<String, Object> queryParams) {
        for (Map.Entry<String, Object> param : queryParams.entrySet()) {
            requestSpecification.queryParam(param.getKey(), param.getValue());
        }
    }

    public <T> T prepareResult(Response response, Class<T> expectedClass) {
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            return response.as(expectedClass);
        } else if (response.getStatusCode() == HttpStatus.SC_NOT_FOUND) {
            return null;
        } else {
            response.then().log().ifError();
            return null;
        }
    }
}
