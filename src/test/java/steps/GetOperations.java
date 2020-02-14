package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utils.RestAssuredExtension;

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

    @Given("^I perform POST operation for \"([^\"]*)\"$")
    public void iPerformPOSTOperationFor(String url){
        response = new RestAssuredExtension().getResourceResponse(url);
        BDDStyleClass.verifyPostWithBodyParameterOperation();
    }

    @Then("^I should see all posts titles$")
    public void iShouldSeeTheTitleAs() {
        BDDStyleClass.verifyGetAllPostsTitles();
    }
}
