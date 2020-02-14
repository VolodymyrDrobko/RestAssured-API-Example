package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.core.Is;
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
}
