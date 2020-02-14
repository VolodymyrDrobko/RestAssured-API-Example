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
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:3000");
        builder.setContentType(ContentType.JSON);
        request = RestAssured.given().spec(builder.build());
    }

    //GET
    public static ResponseOptions<Response> getResourceWithPathParameter(String url, Map<String, String> pathParameter) {
        request.pathParams(pathParameter);
        return request.get(url);
    }

    public static ResponseOptions<Response> getResource(String url) {
        return request.get(url);
    }

    //POST
    public static ResponseOptions<Response> postResourceWithBody(String url, Map<String, String> body) {
        request.body(body);
        return request.post(url);
    }

    public static ResponseOptions<Response> postResourceWithPathParameter(String url, Map<String, String> pathParameter, Map<String, String> body) {
        request.pathParams(pathParameter);
        request.body(body);
        return request.post(url);
    }

    //PUT
    public static ResponseOptions<Response> putResourceWithBody(String url, Map<String, String> pathParam, Map<String, String> body) {
        request.body(body);
        request.pathParams(pathParam);
        return request.put(url);
    }

    //DELETE
    public static ResponseOptions<Response> deleteResourceWithPathParameter(String url, Map<String, String> pathParams) {
        request.pathParams(pathParams);
        return request.delete(url);
    }
}
