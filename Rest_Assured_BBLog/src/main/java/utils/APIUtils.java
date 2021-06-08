package utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

public class APIUtils {

	public static User getRandomUser() {
		String generatedString = RandomStringUtils.randomAlphabetic(6);
		String email = "Test" + generatedString + "@yopmail.com";
		String username = "Test" + generatedString;
		String password = generatedString;
		return new User(username, email, password);
	}

	public static User getRegisteredUser() {
		return new User(TestProperties.getProperties("userEmail"), TestProperties.getProperties("userPassword"));
	}

	public static JSONObject registerUserDetails() {
		User user = APIUtils.getRandomUser();
		JSONObject requestParams = new JSONObject();
		JSONObject requestComplete = new JSONObject();

		requestParams.put("username", user.getUsername());
		requestParams.put("password", user.getPassword());
		requestParams.put("email", user.getEmail());
		return requestComplete.put("user", requestParams);

	}

	public static JSONObject loginDetails() {
		JSONObject requestParams = new JSONObject();
		JSONObject requestComplete = new JSONObject();
		requestParams.put("email", TestProperties.getProperties("userEmail"));
		requestParams.put("password", TestProperties.getProperties("userPassword"));
		return requestComplete.put("user", requestParams);

	}

}
