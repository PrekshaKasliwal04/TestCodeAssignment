package Assignment.Rest_Assured_BBLog;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIUtils;
import utils.TestProperties;

public class RegistrationTest {
	JSONObject requestResponse = new JSONObject();	

	@BeforeMethod(description = "Below piece of code will execue before ech test.")
	public void setEnvironment() {
		RestAssured.baseURI = TestProperties.getProperties("baseURL");
	}
	
	@Test(priority = 1, description = "Verify User SignUp success code: 200 for POST /api/users")
	public void verifySignUpUserTest() {
		RequestSpecification request =RestAssured.given().auth().preemptive().basic(TestProperties.getProperties("username"), TestProperties.getProperties("password"));
		requestResponse = APIUtils.registerUserDetails();
		request.header("Content-Type", "application/json");		 
		request.body(requestResponse.toString());	 
		Response response = request.post(TestProperties.getProperties("register_User"));
		Assert.assertEquals(response.getStatusCode(), 200);
		Reporter.log("Status of Sign Up User Request", response.getStatusCode());
	}
	@Test(priority = 2, description = "Verify Already Exist User error code: 422")
	public void verifyAlreadyExistUserErrorTest() {
		RequestSpecification request =RestAssured.given().auth().preemptive().basic(TestProperties.getProperties("username"), TestProperties.getProperties("password"));
		requestResponse = APIUtils.registerUserDetails();
		request.header("Content-Type", "application/json");		 
		request.body(requestResponse.toString());	 
		Response response = request.post(TestProperties.getProperties("register_User"));
		Assert.assertEquals(response.getStatusCode(), 200);
		Reporter.log("One user created with provided credentials.");	
		Response newResponse = request.post(TestProperties.getProperties("register_User"));
		int newStatusCode = newResponse.getStatusCode();
		Assert.assertEquals(newStatusCode, 422);	
		Reporter.log("Status of Already Exist User Sign Up Request", newStatusCode);
	}	
}
