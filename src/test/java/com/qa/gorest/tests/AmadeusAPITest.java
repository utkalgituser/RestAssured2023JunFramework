package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.consts.APIHttpStatus;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AmadeusAPITest extends BaseTest {

    private String access_token;
    @Parameters({"baseURI", "grant_type", "client_id", "client_secret"})
    @BeforeMethod
    public void flightAPISetup(String baseURI, String  grant_type, String client_id, String client_secret){
        restClient = new RestClient(prop, baseURI);
        access_token = restClient.getAccessToken(AMADEUS_TOKEN_END_POINT, grant_type, client_id, client_secret);
    }
    @Test(description = "")
    public void getFlightInfoTest() {

        RestClient restClientFlight = new RestClient(prop, baseURI);
        // Query params
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("origin", "PAR");
        queryParams.put("maxPrice", 200);

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("Authorization", "Bearer " + access_token);

        // Use JsonPathValidator to validate response
        Response flightDataResponse = restClientFlight.get(AMADEUS_FLIGHTBOOKING_END_POINT, queryParams, headersMap,  false, true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode())
                .and()
                .extract().response();
        JsonPath js = flightDataResponse.jsonPath();
        String type = js.get("data[0].type");
        System.out.println("type is " + type); //flight-destination
    }
}
