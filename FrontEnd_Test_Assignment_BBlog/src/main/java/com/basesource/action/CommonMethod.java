
package com.basesource.action;

import java.io.IOException;
import org.apache.commons.lang.RandomStringUtils;
import org.json.simple.parser.JSONParser;

public class CommonMethod extends PreDefinedActions {

	protected JSONParser parser = new JSONParser();  
	private CommonMethod instance;

	public CommonMethod getInstance() throws IOException {
		if (instance == null) {
			synchronized (CommonMethod.class) {
				if (instance == null) {
					instance = new CommonMethod();
				}
			}
		}
		return instance;
		
	}
	
	public final boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException er) {
			return false;
		}
	}

	/*
	 *
	 * This method generate randomString upto 4 characters
	 */
	public final String generateRandomString() {
		String randomStr = RandomStringUtils.randomAlphabetic(4);
		String upperRandom = randomStr.toUpperCase();
		return upperRandom;
	}
	
	/*
	 *
	 * This method generate random alphanumeric string upto 4 characters
	 */
	public final String generateAlphaRandomNumber() {
		String randomStr = RandomStringUtils.randomAlphanumeric(4);
		String lowerRandom = randomStr.toLowerCase();
		return lowerRandom;
	}
}