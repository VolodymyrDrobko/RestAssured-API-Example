package steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class GetPostSteps {
    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) throws Throwable {
    }

    @And("^I perform GET for the post number \"([^\"]*)\"$")
    public void iPerformGETForThePostNumber(String postNumber) throws Throwable {
        BDDStyleClass.simpleGetPost(postNumber);
    }

    @Then("^I should see the title as \"([^\"]*)\"$")
    public void iShouldSeeTheTitleAs(String arg0) throws Throwable {
    }

    @Then("^I should see the author names$")
    public void iShouldSeeTheAuthorNames() throws Throwable {
        BDDStyleClass.performContainsCollection();
    }

    @Then("^I should see verify GET parameter$")
    public void iShouldSeeVerifyGETParameter() throws Throwable {
    }

    @Then("^I should verify path GET parameter$")
    public void iShouldVerifyPathGETParameter() throws Throwable {
        BDDStyleClass.performPathParameter();
    }

    @Then("^I should verify query GET parameter$")
    public void iShouldVerifyQueryGETParameter() throws Throwable {
        BDDStyleClass.performQueryParameter();
    }
}
