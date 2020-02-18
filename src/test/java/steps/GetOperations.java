package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utils.RestAssuredExtension;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItem;

public class GetOperations {
    private static  ResponseOptions<Response> response;

    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) {
        response = RestAssuredExtension.getResource(url);
    }

    @Then("^I should see the title as \"([^\"]*)\"$")
    public void iShouldSeeTheTitleAs(String titleName) {
        assertThat(response.getBody().jsonPath().get("title"), hasItem(titleName));
    }

    @Then("^I should see all posts titles$")
    public void iShouldSeeTheTitleAs() {
        BDDStyleClass.verifyGetAllPostsTitles();
    }

    @Given("^I perform GET operation for \"([^\"]*)\" with query parameter$")
    public void iPerformGETOperationForWithQueryParameter(String url, DataTable dataTable) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(dataTable.raw().get(0).get(0), dataTable.raw().get(1).get(0));

        response = RestAssuredExtension.getResourceWithQueryParameter(url, queryParams);
}

//JSON SCHEMA VALIDATOR
    @Then("^I should see author name as \"([^\"]*)\" with JSON validation$")
    public void iShouldSeeAuthorNameAsWithJSONValidation(String authorName) {
        String responseBody = response.getBody().asString();

        assertThat(responseBody, matchesJsonSchemaInClasspath("db.json"));
        System.out.println("Response body: \n" + responseBody);
        assertThat("Verify that response contain author name " + authorName, responseBody.contains(authorName));
    }
}
