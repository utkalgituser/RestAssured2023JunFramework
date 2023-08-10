package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.consts.APIHttpStatus;
import com.qa.gorest.utils.JsonPathValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CircuitTest extends BaseTest {

    @BeforeMethod
    public void getUserSetup() {
        restClient = new RestClient(prop, baseURI);
    }

    @Test(description = "")
    public void getAllCircuits() {
        Response circuitResponse =  restClient.get(CIRCUIT_END_POINT + "/2017/circuits.json", false, true);
        circuitResponse
            .then()
                .assertThat()
                    .statusCode(APIHttpStatus.OK_200.getCode());

        JsonPathValidator validator = new JsonPathValidator();
        List<String> countryList = validator.readList(circuitResponse, "$.MRData.CircuitTable.Circuits[?(@.circuitId == 'shanghai')].Location.country");
        System.out.println(countryList);
        Assert.assertTrue(countryList.contains("China"));
                /*.then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode())*/
    }
}
