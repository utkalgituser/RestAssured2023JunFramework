package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.consts.APIHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class APISchemaValidationTest extends BaseTest {
    @BeforeMethod
    public void getUserSetup() {
        restClient = new RestClient(prop, baseURI);
    }

    @Test(description = "")
    public void createUserAPISchemaTest() {
        System.out.println("IN createUserTest");

        //1. POST:
        User user = new User("Tom", StringUtils.getRandomEmailId(), "male", "active");
        restClient.post(GOREST_END_POINT, "json", user, true, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
                .body(matchesJsonSchemaInClasspath("createUserSchema.json"));
    }

    @Test(enabled = true, description = "")
    public void gerUserTest(){
        // Use JsonPathValidator to validate response
        restClient.get(GOREST_END_POINT+"/"+4475709, true, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode())
                .and().body(matchesJsonSchemaInClasspath("getuserschema.json"));
    }
}
