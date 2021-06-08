package Assignment.Rest_Assured_BBLog;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import utils.TestProperties;

public class NonAuthenticateUserTest {
	@Test(priority = 1, description = "Verify Non Authenticate user should get Error - 401")
	public void verifyAuthenticationRequiredErrorTest() {
		int status = RestAssured.given().auth().preemptive().basic("random", "random").when()
				.get(TestProperties.getProperties("baseURL")).getStatusCode();
		Assert.assertEquals(status, 401, "Provided user has access to system, Test Failed");
		Reporter.log("Status of Request", status);
	}		
}
