package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredExtensionV2 {
    RequestSpecBuilder builder = new RequestSpecBuilder();
    private String url;
    private Method method;

    public RestAssuredExtensionV2(String uri, Method method, String token) {
        RestAssured.baseURI = "http://localhost:3000";
        this.url = RestAssured.baseURI + uri;
        this.method = method;

        if (token != null) {
                builder.addHeader("Authorization", "Bearer " + token);
        }
    }

    public Response sendRequest() {
        RequestSpecification requestSpecification = builder.build();
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.contentType(ContentType.JSON);
        httpRequest.spec(requestSpecification);
        return httpRequest.request(method, url);
    }

    public Response sendRequestWithPathParameter(Map<String, String> pathParameter) {
        builder.addPathParams(pathParameter);
        return sendRequest();
    }

    public Response sendRequestWithQueryParameter(Map<String, String> queryParam) {
        builder.addQueryParams(queryParam);
        return sendRequest();
    }
}
