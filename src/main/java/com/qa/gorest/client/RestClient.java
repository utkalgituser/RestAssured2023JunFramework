package com.qa.gorest.client;

import com.qa.gorest.consts.APIHttpStatus;
import com.qa.gorest.framworkException.APIFrameworkException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class RestClient {

    private RequestSpecBuilder specBuilder;

    private Properties prop;
    private String baseURI;

    private boolean isAuthorizationHeaderAdded = false;

    public RestClient(Properties prop, String baseURI) {
        // System.out.println("IN RestClient");
        specBuilder = new RequestSpecBuilder();
        this.prop = prop;
        this.baseURI = baseURI;
    }

    public void addAuthorizationHeader() {
        if (!isAuthorizationHeaderAdded) {
            System.out.println("prop is "+ prop +" --------------");
            System.out.println("Token id is "+ prop.getProperty("tokenid")+" --------------");
            specBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenid"));
            isAuthorizationHeaderAdded = true;
        }
    }

    private void setReuqestContentType(String contentType) {//json-JSON-Json
        switch (contentType.toLowerCase()) {
            case "json":
                specBuilder.setContentType(ContentType.JSON);
                break;
            case "xml":
                specBuilder.setContentType(ContentType.XML);
                break;
            case "text":
                specBuilder.setContentType(ContentType.TEXT);
                break;
            case "multipart":
                specBuilder.setContentType(ContentType.MULTIPART);
                break;

            default:
                System.out.println("plz pass the right content type....");
                throw new APIFrameworkException("INVALIDCONTENTTYPE");
        }
    }

    private RequestSpecification createRequestSpec(boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);

        if (includeAuth) {
            addAuthorizationHeader();
        }

        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Map<String, String> headersMap, boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        if (headersMap != null) {
            specBuilder.addHeaders(headersMap);
        }
        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Map<String, String> headersMap, Map<String, Object> queryParams, boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        if (headersMap != null) {
            specBuilder.addHeaders(headersMap);
        }
        if (queryParams != null) {
            specBuilder.addQueryParams(queryParams);
        }
        return specBuilder.build();
    }


    private RequestSpecification createRequestSpec(Object requestBody, String contentType, boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        setReuqestContentType(contentType);
        if (requestBody != null) {
            specBuilder.setBody(requestBody);
        }
        return specBuilder.build();
    }

    private RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String, String> headersMap, boolean includeAuth) {
        specBuilder.setBaseUri(baseURI);
        if (includeAuth) {
            addAuthorizationHeader();
        }
        setReuqestContentType(contentType);
        if (headersMap != null) {
            specBuilder.addHeaders(headersMap);
        }
        if (requestBody != null) {
            specBuilder.setBody(requestBody);
        }
        return specBuilder.build();
    }


    //Http Methods Utils:
    public Response get(String serviceUrl, boolean includeAuth, boolean log) {
        if (log) {
            return given(createRequestSpec(includeAuth)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return given(createRequestSpec(includeAuth)).when().get(serviceUrl);

    }

    public Response get(String serviceUrl, Map<String, String> headersMap, boolean includeAuth, boolean log) {

        if (log) {
            return given(createRequestSpec(headersMap, includeAuth)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return given(createRequestSpec(headersMap, includeAuth)).when().get(serviceUrl);
    }

    public Response get(String serviceUrl, Map<String, Object> queryParams, Map<String, String> headersMap, boolean includeAuth, boolean log) {

        if (log) {
            return given(createRequestSpec(headersMap, queryParams, includeAuth)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return given(createRequestSpec(headersMap, queryParams, includeAuth)).when().get(serviceUrl);
    }


    public Response post(String serviceUrl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
        if (log) {
            return given(createRequestSpec(requestBody, contentType, includeAuth)).log().all()
                    .when()
                    .post(serviceUrl);
        }
        return given(createRequestSpec(requestBody, contentType, includeAuth))
                .when()
                .post(serviceUrl);
    }


    public Response post(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap, boolean includeAuth, boolean log) {
        if (log) {
            return given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).log().all()
                    .when()
                    .post(serviceUrl);
        }
        return given(createRequestSpec(requestBody, contentType, headersMap, includeAuth))
                .when()
                .post(serviceUrl);
    }


    public Response put(String serviceUrl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
        if (log) {
            return given(createRequestSpec(requestBody, contentType, includeAuth)).log().all()
                    .when()
                    .put(serviceUrl);
        }
        return given(createRequestSpec(requestBody, contentType, includeAuth))
                .when()
                .put(serviceUrl);
    }


    public Response put(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap, boolean includeAuth, boolean log) {
        if (log) {
            return given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).log().all()
                    .when()
                    .put(serviceUrl);
        }
        return given(createRequestSpec(requestBody, contentType, headersMap, includeAuth))
                .when()
                .put(serviceUrl);
    }


    public Response patch(String serviceUrl, String contentType, Object requestBody, boolean includeAuth, boolean log) {
        if (log) {
            return given(createRequestSpec(requestBody, contentType, includeAuth)).log().all()
                    .when()
                    .patch(serviceUrl);
        }
        return given(createRequestSpec(requestBody, contentType, includeAuth))
                .when()
                .patch(serviceUrl);
    }


    public Response patch(String serviceUrl, String contentType, Object requestBody, Map<String, String> headersMap, boolean includeAuth, boolean log) {
        if (log) {
            return given(createRequestSpec(requestBody, contentType, headersMap, includeAuth)).log().all()
                    .when()
                    .patch(serviceUrl);
        }
        return given(createRequestSpec(requestBody, contentType, headersMap, includeAuth))
                .when()
                .patch(serviceUrl);
    }


    public Response delete(String serviceUrl, boolean includeAuth, boolean log) {
        if (log) {
            return given(createRequestSpec(includeAuth)).log().all()
                    .when()
                    .delete(serviceUrl);
        }
        return given(createRequestSpec(includeAuth))
                .when()
                .delete(serviceUrl);
    }

    public String getAccessToken(String serviceURL, String grantType, String clientId, String clientSecret) {
        //1. POST - get the access token
        RestAssured.baseURI = "https://test.api.amadeus.com";

        String accessToken = given().log().all()
                .contentType(ContentType.URLENC)
                .formParam("grant_type", grantType)
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret)
                .when()
                .post(serviceURL)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode())
                .extract().path("access_token");

        System.out.println("access token: " + accessToken);
        return accessToken;

    }
}
