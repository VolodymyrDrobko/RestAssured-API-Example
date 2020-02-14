package steps;

import io.restassured.http.ContentType;
import org.hamcrest.core.Is;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class BDDStyleClass {
    public static String ALL_POSTS = "http://localhost:3000/posts/";
    public static String POST_BY_NUMBER = "http://localhost:3000/posts/{post}";

    public static void verifyTitleByIdPostNumber(String postNumber) {
        given().contentType(ContentType.JSON)
                .with().pathParam("post", 2)
                .when().get(String.format(POST_BY_NUMBER, postNumber))
                .then().body("title", is("Appium"));
    }

    public static void verifyGetAllPostsTitles() {
        given().contentType(ContentType.JSON)
                .queryParam("_page", "1").queryParam("_limit", "2")
                .when().get(ALL_POSTS)
                .then().body("title", containsInAnyOrder("Appium", "Selenium")).statusCode(200);
    }

    public static void verifyPostWithBodyParameterOperation() {
        HashMap<String, String> postContent = new HashMap<>();
        postContent.put("id", "4");
        postContent.put("title", "Java");
        postContent.put("author", "JavaMan");

        given().contentType(ContentType.JSON)
                .with().body(postContent)
                .when().post(ALL_POSTS)
                .then().body("author", Is.is("JavaMan"));
    }

}
