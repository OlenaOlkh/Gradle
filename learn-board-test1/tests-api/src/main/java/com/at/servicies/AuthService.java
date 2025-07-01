package com.at.servicies;

import io.restassured.response.Response;

public class AuthService extends AbstractService {

    public AuthService(String baseUri, String basePath, String token) {
        super(baseUri, basePath, token);
    }

    public Response getUserInfo() {
        return getRequest("");
    }
}
