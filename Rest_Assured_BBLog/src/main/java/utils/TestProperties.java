package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	public static String propertyValue;

	public static String getProperties(String key) {
		try {
			FileInputStream inputStream = new FileInputStream("src/test/resources/PropertyFile.properties");
			Properties propObj = new Properties();
			propObj.load(inputStream);

			propertyValue = propObj.getProperty(key);

			return propertyValue;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
