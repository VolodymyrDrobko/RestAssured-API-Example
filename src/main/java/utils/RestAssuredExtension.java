package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredExtension {
    public static RequestSpecification request;

    public RestAssuredExtension() {

        //ARRANGE
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:3000");
        builder.setContentType(ContentType.JSON);
        request = RestAssured.given().spec(builder.build());
    }

    public static ResponseOptions<Response> getResourceWithPathParameter(String url, Map<String, String> pathParameter) {
        request.pathParams(pathParameter);
        return request.get(url);
    }

    public static ResponseOptions<Response> getResourceResponse(String url) {
        return request.get(url);
    }

    public static ResponseOptions<Response> postResourceParameter(String url, Map<String, String> body) {

        //ACT
        request.body(body);
        return request.post(url);
    }

    public static ResponseOptions<Response> postResourceWithPathParameter(String url, Map<String, String> pathParameter, Map<String, String> body) {

        //ACT
        request.pathParams(pathParameter);
        request.body(body);
        return request.post(url);
    }

    public static ResponseOptions<Response> deleteResourceWithPathParameter(String url, Map<String, String> pathParams) {
        request.pathParams(pathParams);
        return request.delete(url);
    }
}
