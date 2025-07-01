package com.at.utils;

import com.at.dto.EpamAuthTokenDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EpamAuth {

    public static String getTokenForTestUser() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String user_email = propertiesReader.getTestUserLogin();
        String user_password = getTestUserPasswordFromSystemProperties();
        String REGULAR_APP_CLIENT_ID = propertiesReader.getAppClientId();
        String REGULAR_APP_SECRET_ID = propertiesReader.getAppSecretId();
        String AUTH0_URL = propertiesReader.getEpamAuthURL();
        Response response = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("grant_type", "password")
                .formParam("username", user_email)
                .formParam("password", user_password)
                .formParam("client_id", REGULAR_APP_CLIENT_ID)
                .formParam("client_secret", REGULAR_APP_SECRET_ID)
                .when()
                .post(AUTH0_URL);
        EpamAuthTokenDto epamAuthTokenDto = response.as(EpamAuthTokenDto.class);
        return epamAuthTokenDto.getAccess_token();
    }

    public static String getTestUserPasswordFromSystemProperties() {
        String pwd = System.getProperty("testUserPassword");
        if (pwd == null) {
            throw new RuntimeException("You should set system variable -DtestUserPassword='somepassword'");
        }
        return pwd;
    }
}
