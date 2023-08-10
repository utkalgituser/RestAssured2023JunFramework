package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import org.testng.annotations.BeforeMethod;

public class FakeStoreAPITest extends BaseTest {

    @BeforeMethod
    public void getUserSetup(){
        restClient = new RestClient(prop, baseURI);
    }

    // Get all product, single product, Add new product
    // create product, get product
    // Use JsonPathValidator to validate response
}
