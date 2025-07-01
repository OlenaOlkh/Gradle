package com.at.servicies;

import io.restassured.response.Response;

public class OutlookService extends AbstractService {

    public OutlookService(String baseUri, String basePath, String token) {
        super(baseUri, basePath, token);
    }

    public Response postLogin() {
        return postRequest("login");
    }

    public Response postExport() {
        return postRequest("export");
    }
}
