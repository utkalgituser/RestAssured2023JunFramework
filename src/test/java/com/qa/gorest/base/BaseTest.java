package com.qa.gorest.base;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {

    // service url
    public static final String  AMADEUS_TOKEN_END_POINT = "/v1/security/oauth2/token";
    public static final String  CIRCUIT_END_POINT = "/api/f1";
    public static final String  GOREST_END_POINT = "/public/v2/users";
    public static final String  REQRES_END_POINT = "/api/users";
    public static final String  AMADEUS_FLIGHTBOOKING_END_POINT = "/v1/shopping/flight-destinations";

    protected ConfigurationManager config;
    protected Properties prop;
    protected RestClient restClient;
    protected String baseURI;
    @Parameters({"baseURI"})
    @BeforeTest
    public void setup(String baseURI){
        // For Allure report
        RestAssured.filters(new AllureRestAssured());

        config = new ConfigurationManager();
        prop = config.initProp();
        this.baseURI = baseURI;
        // restClient = new RestClient(prop, baseURI);
    }
}
