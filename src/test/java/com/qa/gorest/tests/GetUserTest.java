package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.consts.APIHttpStatus;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static  org.hamcrest.Matchers.*;

public class GetUserTest extends BaseTest {

    @BeforeMethod
    public void getUserSetup(){
        restClient = new RestClient(prop, baseURI);
    }
    // code smell: sonarQube

    @Test(description = "This test will get all the users")
    public void gerAllUsersTest(){
        // Use JsonPathValidator to validate response
        restClient.get(GOREST_END_POINT, true, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
    }
    @Test(enabled = false, description = "")
    public void gerUserTest(){
        // Use JsonPathValidator to validate response
        restClient.get(GOREST_END_POINT+"/"+4275465, true, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode())
                .and().body("id" , equalTo(4275465));
    }
    @Test(description = "")
    public void gerUserWithQueryParamsTest(){
        Map<String, Object> queryPrams = new HashMap<>();
        queryPrams.put("name", "akhil");
        queryPrams.put("status", "active");

        // Use JsonPathValidator to validate response
        restClient.get(GOREST_END_POINT, queryPrams, null, true, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
                // .and().body("id" , equalTo(4091957));
    }
}
