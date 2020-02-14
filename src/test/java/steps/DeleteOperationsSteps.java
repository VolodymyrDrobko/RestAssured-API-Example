package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.core.IsNot;
import utils.RestAssuredExtension;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteOperationsSteps {
    private static ResponseOptions<Response> response;

    @Given("^I perform POST operation for \"([^\"]*)\" with body as$")
    public void iPerformPOSTOperationForWithBodyAs(String url, DataTable dataTable) {
        Map<String, String> body = new HashMap<>();
        body.put("id", dataTable.raw().get(1).get(0));
        body.put("title", dataTable.raw().get(1).get(1));
        body.put("author", dataTable.raw().get(1).get(2));

        RestAssuredExtension.postResourceWithBody(url, body);
    }

    @And("^I perform DELETE operation for \"([^\"]*)\"$")
    public void iPerformDELETEOperationFor(String url, DataTable dataTable) {
        Map<String, String> pathParam = new HashMap<>();
        pathParam.put("postId", dataTable.raw().get(1).get(0));

        RestAssuredExtension.deleteResourceWithPathParameter(url, pathParam);
    }

    @And("^I perform GET operation with path \"([^\"]*)\"$")
    public void iPerformGETOperationWithPath(String url, DataTable dataTable) {
        Map<String, String> pathParam = new HashMap<>();
        pathParam.put("postId", dataTable.raw().get(1).get(0));

        response = RestAssuredExtension.getResourceWithPathParameter(url, pathParam);
    }

    @Then("^I should not see the body with title as \"([^\"]*)\"$")
    public void iShouldNotSeeTheBodyWithTitleAs(String title) {
        assertThat(response.getBody().jsonPath().get("title"), IsNot.not(title));
    }
}
