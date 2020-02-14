package steps;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import utils.RestAssuredExtension;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class PostOperationsSteps {
    private static ResponseOptions<Response> response;

    @Given("^I perform POST operation for \"([^\"]*)\" with body$")
    public void iPerformPOSTOperationForWithBody(String url, DataTable dataTable) {
        Map<String, String> pathParam = new HashMap<>();
        pathParam.put("profileNo", dataTable.raw().get(1).get(1));

        Map<String, String> body = new HashMap<>();
        body.put("name", dataTable.raw().get(1).get(0));
        body.put("profileNo", dataTable.raw().get(1).get(1));

        response = RestAssuredExtension.postResourceWithPathParameter(url, pathParam, body);
    }

    @Then("^I should see the body has name as \"([^\"]*)\"$")
    public void iShouldNotSeeTheBodyWithTitleAs(String name) {
        assertThat(response.getBody().jsonPath().get("name"), Is.is(name));
    }


    @Given("^I perform POST operation for \"([^\"]*)\"$")
    public void iPerformPOSTOperationFor(String url){
        response = new RestAssuredExtension().getResource(url);
        BDDStyleClass.verifyPostWithBodyParameterOperation();
    }

    @And("^I perform PUT operation for \"([^\"]*)\"$")
    public void iPerformPUTOperationFor(String url, DataTable dataTable) {
        Map<String, String> body = new HashMap<>();
        body.put("id", dataTable.raw().get(1).get(0));
        body.put("title", dataTable.raw().get(1).get(1));
        body.put("author", dataTable.raw().get(1).get(2));

        Map<String, String> pathParam = new HashMap<>();
        pathParam.put("postId", dataTable.raw().get(1).get(0));

        response = RestAssuredExtension.putResourceWithBody(url, pathParam, body);
    }

    @Then("^I \"([^\"]*)\" see the body with author as \"([^\"]*)\"$")
    public void iShouldNotSeeTheBodyWithAuthorAs(String condition, String author) {
        if (condition.equalsIgnoreCase("should not"))
            assertThat(response.getBody().jsonPath().get("author"), IsNot.not(author));
        else
            assertThat(response.getBody().jsonPath().get("author"), Is.is(author));
    }

    @And("^I perform GET operation with \"([^\"]*)\"$")
    public void iPerformGETOperationWith(String url, DataTable dataTable) {
        Map<String, String> pathParam = new HashMap<>();
        pathParam.put("postId", dataTable.raw().get(1).get(0));
        
        response = RestAssuredExtension.getResourceWithPathParameter(url, pathParam);
    }
}
