package com.bblog.pages;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.basesource.action.CommonMethod;
import com.basesource.utils.ObjectReader;

public class HomePage extends CommonMethod {
	private ObjectReader HomePagePropertiesReader = new ObjectReader();
	private Properties HomePagePropertiesPageProperties;

	private HomePage instance;
	public static String environment, URL;

	public HomePage getInstance() throws IOException {
		if (instance == null) {
			synchronized (HomePage.class) {
				if (instance == null) {
					instance = new HomePage(DRIVER);
				}
			}
		}
		return instance;
	}

	public HomePage(WebDriver driver) throws IOException {
		HomePagePropertiesPageProperties = HomePagePropertiesReader.getObjectRepository("locatorValues");
	}

	
	public final void clickOnElementsOfHomePage(String key) throws Exception {
		click(HomePagePropertiesPageProperties.getProperty(key));
	}

	public final void enterTextForHomePage(String key, String Text) throws Exception {
		enterText(HomePagePropertiesPageProperties.getProperty(key), Text);
	}
	public final void clearTextOfHomePage(String key) throws Exception {
		clearText(HomePagePropertiesPageProperties.getProperty(key));
	}
	public final boolean verifyElementsOfHomePage(String key) throws Exception {
		return verifyElementIsPresent(HomePagePropertiesPageProperties.getProperty(key));
	}

	public final boolean waitForElementsOfHomePage(String key) throws Exception {
		return verifyElementIsVisible(HomePagePropertiesPageProperties.getProperty(key));
	}
	public final String getTextOfHomePage(String key) throws Exception {
		return getTextBy(HomePagePropertiesPageProperties.getProperty(key));
	}

}
