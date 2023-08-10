package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.consts.APIHttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReqResAPITest extends BaseTest {

    @BeforeMethod
    public void getReqResUserSetup(){
        restClient = new RestClient(prop, baseURI);
    }

    @Test(description = "")
    public void getUserTest(){
        // Use JsonPathValidator to validate response
        restClient.get(REQRES_END_POINT+"/"+2 , false, true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode());
    }
}
