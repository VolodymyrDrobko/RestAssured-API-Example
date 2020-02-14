package steps;

import cucumber.api.java.Before;
import utils.RestAssuredExtension;

public class TestSetUp {
    @Before
    public void beforeMethod() {
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
    }
}
