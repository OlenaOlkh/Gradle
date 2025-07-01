package com.at;

import com.at.mockserver.MockServer;
import com.at.utils.DataBaseConnector;
import com.at.utils.EpamAuth;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.URISyntaxException;

public abstract class AbstractLearnBoardApiTest {

    public static LearnBoardAPI learnBoardAPI;
    public static DataBaseConnector dataBaseConnector;

    @Parameters({"apiBaseUrl", "jdbcURL"})
    @BeforeSuite
    public void setup(@Optional("") String apiBaseUrl, @Optional("") String jdbcURL) throws URISyntaxException {

        dataBaseConnector = DataBaseConnector.getInstance(jdbcURL);

        System.out.println("====================== BeforeSuite=================");
        // If no url has been sent then use the mock server
        String apiToken = "";
        if (apiBaseUrl.equals("")) {
            apiBaseUrl = "http://localhost:8081/learnboard/api";
            MockServer.getInstance().startServer(apiBaseUrl);
            System.out.println("Mock server is being used!");
        } else {
            apiToken = EpamAuth.getTokenForTestUser();
        }
        System.out.println("apiBaseUrl: " + apiBaseUrl);
        // In the future there will be different tests with different tokens I suppose
        learnBoardAPI = LearnBoardAPI.init(apiBaseUrl, apiToken);
    }

    @AfterSuite
    public void teardown() {
        MockServer.getInstance().stopServer();
        dataBaseConnector.closeConnection();
    }
}
