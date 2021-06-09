package com.testscripts.bblog;


import java.util.Properties;

import org.testng.Assert;

import com.basesource.action.PreDefinedActions;
import com.basesource.utils.ObjectReader;
import com.basetest.environments.SetTestEnvironments;
import com.bblog.pages.HomePage;

public class CommonTest extends SetTestEnvironments {

	private CommonTest instance;
	private Properties selectDataProperties;
	private ObjectReader commonTestPropertiesReader = new ObjectReader();
	public static String environment;
	public CommonTest getInstance() {
		if (instance == null) {
			synchronized (CommonTest.class) {
				if (instance == null) {
					instance = new CommonTest();

				}
			}
		}
		return instance;
	}

	public final String getData(String dataValue, String key) throws Exception {
		selectDataProperties = commonTestPropertiesReader.getData(dataValue);
		return selectDataProperties.getProperty(key);
	}

	/**
	 * Login method
	 * 
	 */
	public final void login(String userId, String password) throws Exception {

		HomePage homePage = new HomePage(PreDefinedActions.getDriver());
		homePage = homePage.getInstance();
		homePage.clickOnElementsOfHomePage("signIn");
		Assert.assertTrue(homePage.verifyElementsOfHomePage("userName"), "SignIn is not open successfully");		
		homePage.enterTextForHomePage("userName", getData("data", userId));
		homePage.enterTextForHomePage("password", getData("data", password));
		homePage.clickOnElementsOfHomePage("userName");
		homePage.wait(6000);
		homePage.clickOnElementsOfHomePage("loginBtn");	
		homePage.wait(6000);

	}

	/**
	 * Logout method
	 * 
	 */
	public final void logout() throws Exception {
		
		HomePage homePage = new HomePage(PreDefinedActions.getDriver()).getInstance();
		homePage.clickOnElementsOfHomePage("settings");
		homePage.clickOnElementsOfHomePage("logout");		
	}
	
	/**
	 * New Article Publish method
	 * 
	 */
	public final void newArticlePublish() throws Exception {

		HomePage homePage = new HomePage(PreDefinedActions.getDriver());
		homePage = homePage.getInstance();
		Assert.assertTrue(homePage.verifyElementsOfHomePage("articleTitle"), "New Post page is not open successfully");
		String testRandom = generateAlphaRandomNumber();
		String title = getData("data", "articleTitle")+ testRandom;
		homePage.enterTextForHomePage("articleTitle", title);
		homePage.enterTextForHomePage("about", getData("data", "articleAbout")+ testRandom);
		homePage.enterTextForHomePage("writeArticle", getData("data", "articleWrite")+ testRandom);
		homePage.clickOnElementsOfHomePage("publishArticle");
		Assert.assertEquals(homePage.getTextOfHomePage("matchTitle"), title, "New Article Post failed");	
		}	
}
