package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.consts.APIConstants;
import com.qa.gorest.consts.APIHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.ExcelUtil;
import com.qa.gorest.utils.StringUtils;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseTest {

    @BeforeMethod
    public void getUserSetup() {
        restClient = new RestClient(prop, baseURI);
    }

    @DataProvider
    public Object[][] getUserTestData() {
        return new Object[][]{
                {"alok", "male", "active"},
                {"rekha", "female", "inactive"},
                {"srini", "male", "inactive"}
        };
    }

    @DataProvider
    public Object[][] getUserTestSheetData() {
        // System.out.println("IN getUserTestSheetData");
        return ExcelUtil.getTestData(APIConstants.GOREST_USER_SHEET_NAME);
    }

    @Test(dataProvider = "getUserTestData", description = "")
    public void createUserTest(String name, String gender, String status) {
        // System.out.println("IN createUserTest");

        //1. POST:
        User user = new User(name, StringUtils.getRandomEmailId(), gender, status);
        Integer userId = restClient.post(GOREST_END_POINT, "json", user, true, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.CREATED_201.getCode())
                .extract().path("id");
        System.out.println("User id: " + userId);

        // Use JsonPathValidator to validate response
        //2. GET
        RestClient clientGet = new RestClient(prop, baseURI);
        clientGet.get(GOREST_END_POINT + "/" + userId, true, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode())
                .assertThat().body("id", equalTo(userId));
    }
}
