package steps;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class BDDStyleClass {
    public static String ALL_POSTS = "http://localhost:3000/posts/";
    public static String POST_BY_ID = "http://localhost:3000/posts/%s";

    public static void simpleGetPost(String postId) {
        given().contentType(ContentType.JSON)
                .when().get(String.format(POST_BY_ID, postId))
                .then().body("title", is("Appium")).statusCode(200);
    }

    public static void performContainsCollection() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(ALL_POSTS)
                .then()
                .body("title", containsInAnyOrder("Selenium", "Appium")).statusCode(200);
    }

    public static void performPathParameter() {
        given()
                .contentType(ContentType.JSON)
                .with().pathParam("posts", 1)
                .when().get(String.format(POST_BY_ID, "{posts}"))
                .then().body("title", containsString("Selenium")).statusCode(200);
    }

    public static void performQueryParameter() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("id", 1)
                .when().get(ALL_POSTS)
                .then().body("title", hasItem("Selenium")).statusCode(200);
    }
}
