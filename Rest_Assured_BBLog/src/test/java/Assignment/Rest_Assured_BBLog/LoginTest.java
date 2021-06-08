package Assignment.Rest_Assured_BBLog;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIUtils;
import utils.TestProperties;

public class LoginTest {
	JSONObject requestResponse = new JSONObject();	

	@BeforeMethod
	public RequestSpecification setEnvironment() {
		RestAssured.baseURI = TestProperties.getProperties("baseURL");
		RequestSpecification request =RestAssured.given().auth().preemptive().basic(TestProperties.getProperties("username"), TestProperties.getProperties("password"));
		requestResponse = APIUtils.loginDetails();		
		request.header("Content-Type", "application/json");		 
		return request.body(requestResponse.toString());
	}
	
	@Test(description = "Verify Authentication - POST /api/users/login")
	public void verifyLoginUserTest() {
		RequestSpecification request =	setEnvironment();
		Response response = request.post(TestProperties.getProperties("login"));
		Assert.assertEquals(response.getStatusCode(), 200);	
        String verification = response.body().asString();
    	Assert.assertEquals(verification.contains(TestProperties.getProperties("userEmail")), true, "Response body does not contains Email");    	
	}			
}
