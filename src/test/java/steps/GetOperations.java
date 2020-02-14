package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utils.RestAssuredExtension;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsIterableContaining.hasItem;

public class GetOperations {
    private static  ResponseOptions<Response> response;

    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) {
        response = RestAssuredExtension.getResourceResponse(url);
    }

    @Then("^I should see the title as \"([^\"]*)\"$")
    public void iShouldSeeTheTitleAs(String titleName) {
        assertThat(response.getBody().jsonPath().get("title"), hasItem(titleName));
    }

    @Then("^I should see all posts titles$")
    public void iShouldSeeTheTitleAs() {
        BDDStyleClass.verifyGetAllPostsTitles();
    }

    @Given("^I perform POST operation for \"([^\"]*)\"$")
    public void iPerformPOSTOperationFor(String url){
        response = new RestAssuredExtension().getResourceResponse(url);
        BDDStyleClass.verifyPostWithBodyParameterOperation();
    }

    @Then("^I should see new author \"([^\"]*)\"$")
    public void iShouldSeeNewAuthor(String authorName) {

    }


    @Given("^I perform POST operation for \"([^\"]*)\" with body$")
    public void iPerformPOSTOperationForWithBody(String url, DataTable table) {
        String name = table.raw().get(1).get(0);
        HashMap<String, String> body = new HashMap<>();
        body.put("name", name);

        String pathParam = table.raw().get(1).get(1);
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("profileNo", pathParam);

        response = RestAssuredExtension.postResourceWithPathParameter(url, pathParams, body);
        response.getBody().print();
    }

    @Then("^I should see the body has name as \"([^\"]*)\"$")
    public void iShouldSeeTheBodyHasNameAs(String name) {
        assertThat(response.getBody().jsonPath().get("name"), equalTo(name));
    }
}
