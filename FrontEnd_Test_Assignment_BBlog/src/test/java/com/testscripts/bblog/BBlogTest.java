package com.testscripts.bblog;


import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.basesource.action.PreDefinedActions;
import com.bblog.pages.HomePage;

public class BBlogTest extends CommonTest {
	private static Logger LOGGER = Logger.getLogger(BBlogTest.class);
	
	@Test(priority = 1, groups = { "REGRESSION"},description = "Verify user should be able to publish new Article")
	public final void verifyNewArticlePublish() throws Exception {		
		waitForPageLoaded();
		HomePage homePage = new HomePage(PreDefinedActions.getDriver()).getInstance();
		Assert.assertEquals(homePage.waitForElementsOfHomePage("signIn"),true,"Home page did not open successfully");		
		login("correctId", "correctPassword");	
		Assert.assertEquals(getUrlOfCurrentPage(), getData("data", "loginURL"),"Login page URL verification failed");
		LOGGER.info("User login successfull");
		homePage.waitForElementsOfHomePage("newPost");
		homePage.clickOnElementsOfHomePage("newPost");
		Assert.assertEquals(homePage.waitForElementsOfHomePage("articleTitle"),true,"New Post did not open successfully");				
		Assert.assertEquals(getUrlOfCurrentPage(), getData("data", "editorURL"),"New Post page URL verification failed");		
		newArticlePublish();
		
		logout();		
		LOGGER.info("New Article Publish Test: Success");
	}

	@Test(priority = 2, groups = { "REGRESSION"},description = "Verify user should be able to Edit Article published")
	public final void verifyEditPublishedArticle() throws Exception {		
		waitForPageLoaded();
		HomePage homePage = new HomePage(PreDefinedActions.getDriver()).getInstance();
		Assert.assertEquals(homePage.waitForElementsOfHomePage("signIn"),true,"Home page did not open successfully");		
		login("correctId", "correctPassword");		
		Assert.assertEquals(getUrlOfCurrentPage(), getData("data", "loginURL"),"Login page URL verification failed");
		LOGGER.info("User login successfull");
		homePage.clickOnElementsOfHomePage("newPost");
		newArticlePublish();	
		String currentTitle = homePage.getTextOfHomePage("matchTitle");
		String editTitle = currentTitle + " edit";
		homePage.clickOnElementsOfHomePage("editArticle");
		Assert.assertEquals(homePage.waitForElementsOfHomePage("articleTitle"),true,"Edit page did not open successfully");				
		homePage.clickOnElementsOfHomePage("articleTitle");
		homePage.enterTextForHomePage("articleTitle", editTitle);
		homePage.clickOnElementsOfHomePage("about");		
		homePage.clickOnElementsOfHomePage("publishArticle");
		Assert.assertEquals(homePage.waitForElementsOfHomePage("newPost"),true,"Home page did not open successfully");			
		Assert.assertEquals(homePage.getTextOfHomePage("matchTitle"), editTitle, "Edit Article failed, titles not matching after edit");		
		logout();		
		LOGGER.info("Edit Article Test: Success");
	}	
	
	@Test(priority = 3, groups = { "REGRESSION"},description = "Verify user should be able to Delete Article published")
	public final void verifyDeletePublishedArticle() throws Exception {		
		waitForPageLoaded();
		HomePage homePage = new HomePage(PreDefinedActions.getDriver()).getInstance();
		Assert.assertEquals(homePage.waitForElementsOfHomePage("signIn"),true,"Home page did not open successfully");		
		login("correctId", "correctPassword");		
		Assert.assertEquals(getUrlOfCurrentPage(), getData("data", "loginURL"),"Login page URL verification failed");
		LOGGER.info("User login successfull");
		homePage.clickOnElementsOfHomePage("newPost");
		newArticlePublish();	
		homePage.clickOnElementsOfHomePage("deleteArticle");
		Assert.assertEquals(homePage.waitForElementsOfHomePage("newPost"),true,"Home page did not open successfully after Delete");				
		logout();		
		LOGGER.info("Delete Article Test: Success");
	}
	@Test(priority = 4, groups = { "REGRESSION"},description = "Verify with Invalid Username, password user should get error")
	public final void verifyAuthErrorForSignIn() throws Exception {		
		waitForPageLoaded();
		HomePage homePage = new HomePage(PreDefinedActions.getDriver()).getInstance();
		Assert.assertEquals(homePage.waitForElementsOfHomePage("signIn"),true,"Home page did not open successfully");		
		login("inCorrectId", "inCorrectPassword");
		Assert.assertEquals(homePage.getTextOfHomePage("authError"), getData("data", "authError"), "Error for invalid user is not matching the Error");				
		LOGGER.info("Invalid user error test: Success");
	}
		
	@Test(priority = 5, groups = { "REGRESSION"},description = "Verify user should not be able to publish blank Article")
	public final void verifyErrorForBlankArticlePublish() throws Exception {		
		waitForPageLoaded();
		HomePage homePage = new HomePage(PreDefinedActions.getDriver()).getInstance();
		Assert.assertEquals(homePage.waitForElementsOfHomePage("signIn"),true,"Home page did not open successfully");		
		login("correctId", "correctPassword");	
		Assert.assertEquals(getUrlOfCurrentPage(), getData("data", "loginURL"),"Login page URL verification failed");
		LOGGER.info("User login successfull");
		homePage.clickOnElementsOfHomePage("newPost");
		Assert.assertTrue(homePage.verifyElementsOfHomePage("articleTitle"), "New Post page is not open successfully");
		homePage.clickOnElementsOfHomePage("publishArticle");
		Assert.assertFalse(homePage.verifyElementsOfHomePage("editArticle"), "Article published, Test Case failed");	
		logout();		
		LOGGER.info("Blank Article Publish Error Test: Failed");
	}

}
