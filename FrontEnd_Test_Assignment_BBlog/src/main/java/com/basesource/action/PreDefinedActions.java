
package com.basesource.action;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.bblog.constants.CommonVariables;
import com.google.common.base.Strings;


public class PreDefinedActions {
	public WebDriver DRIVER;
	public static WebDriverWait wait;
	public String parentWindowHandler = null;
	protected final static Logger LOGGER = Logger.getLogger(PreDefinedActions.class);
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return dr.get();
	}

	public static void setWebDriver(WebDriver DRIVER) {
		dr.set(DRIVER);
	}

	public JavascriptExecutor jsDriver(){
		 return (JavascriptExecutor) getDriver();
	}
	

	/**
	 * Get object by locator from properties file
	 * 
	 * @param locator Locator from properties file
	 * @return By
	 */
	public By getObject(String locator) {
		try {
			String locatorType = getLocatorType(locator);
			String locatorVal = getLocatorValue(locator);

			switch (locatorType.toUpperCase()) {
			// Find by id
			case "ID":
				return By.id(locatorVal);
			// Find by xpath
			case "XPATH":
				return By.xpath(locatorVal);
			// Find by Classname
			case "CLASSNAME":
				return By.className(locatorVal);

			// Find by Name
			case "NAME":
				return By.name(locatorVal);

			// Find by CSS
			case "CSS":
				return By.cssSelector(locatorVal);

			// Find by Link
			case "LINK":
				return By.linkText(locatorVal);

			// Find by PartialLink
			case "PARTIALLINK":
				return By.partialLinkText(locatorVal);

			default:
				LOGGER.error("Provided : " + locatorType + " type is wrong, You can use : ID, XPATH, CLASSNAME, NAME, CSS, LINK, PARTIALLINK");
				throw new InputMismatchException("You can use : ID, XPATH, CLASSNAME, NAME, CSS, LINK, PARTIALLINK only ");
			}
		} catch (Exception e) {
			LOGGER.error("Exception in getting locator as type is wrong." + e.getMessage());
			return null;
		}
	}	
	/**
	 * Wait until page get load. It will wait till all the elements of page get loaded
	 * 
	 */
	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) getDriver()).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		try {
			Thread.sleep(1000);
			WebDriverWait wait = new WebDriverWait(getDriver(), CommonVariables.EXPLICITWAIT);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	/**
	 * Find if the element is present or not
	 * 
	 */
	public boolean verifyElementIsPresent(String locator) throws Exception {
		WebElement element = null;
		try {
			element = getDriver().findElement(this.getObject(locator));
			jsDriver().executeScript("arguments[0].style.border='3px solid red'", element);
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (StaleElementReferenceException e) {
			LOGGER.error("inside stale element exception verifyElementIsPresent");
			return wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Get text
	 */
	public String getTextBy(String locator) throws Exception {
		wait = new WebDriverWait(getDriver(), CommonVariables.EXPLICITWAIT);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(locator)));
		jsDriver().executeScript("arguments[0].style.border='3px solid red'", element);
		return element.getText().trim();
	}

	/**
	 * Click element
	 */
	public final void click(String locator) throws Exception {
		wait = new WebDriverWait(getDriver(), CommonVariables.EXPLICITWAIT);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(locator)));
		jsDriver().executeScript("arguments[0].style.border='3px solid red'", element);
		element.click();
	}

	/**
	 * Enter text
	 */
	public final void enterText(String locator, String text) throws Exception {
		wait = new WebDriverWait(getDriver(), CommonVariables.EXPLICITWAIT);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(locator)));
		jsDriver().executeScript("arguments[0].style.border='3px solid red'", element);
		element.clear();
		element.sendKeys(text);
	}
	
	/**
	 * get WebElement when it is visible.
	 * 
	 * @return
	 */
	public final WebElement getElement(String locator) throws Exception {
		wait = new WebDriverWait(getDriver(), CommonVariables.EXPLICITWAIT);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(locator)));
		jsDriver().executeScript("arguments[0].style.border='3px solid red'", element);
		return element;
	}

	/**
	 * Find if the element is visible or not
	 * 
	 */
	public boolean verifyElementIsVisible(String locator) throws Exception {
		WebElement element = null;
		try {
			wait = new WebDriverWait(getDriver(), CommonVariables.EXPLICITWAIT);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(locator)));
			jsDriver().executeScript("arguments[0].style.border='3px solid red'", element);
			return true;
		} catch (StaleElementReferenceException e) {
			LOGGER.error("inside stale element exception verifyElementIsVisible");
			return wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * Clear text of given locator
	 */
	public final void clearText(String locator) throws Exception {
		wait = new WebDriverWait(getDriver(), CommonVariables.EXPLICITWAIT);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(locator)));
		jsDriver().executeScript("arguments[0].style.border='3px solid red'", element);
		element.clear();
	}
	/**
	 * Scroll the page
	 */
	public final void scrollTillView(String locator) {
		WebElement element = getDriver().findElement(this.getObject(locator));
		jsDriver().executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * Get locator value from properties file.
	 * 
	 * @param locator Locator value from the file
	 * @return Locator value only (not locator type)
	 */
	private String getLocatorValue(String locator) {
		String value = null;
		try {
			int pos = locator.indexOf(":");
			if (pos == -1) {
				LOGGER.error("Error in locator format. Please check properties file for object type");
			} else {
				value = locator.substring(pos + 1);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in getting locator value is wrong." + ex.getMessage());
			return null;
		}
		return value;
	}

	/**
	 * Function to get locator type from locator from properties file
	 * 
	 * @param locator Locator value
	 * @return locator type
	 */
	private String getLocatorType(String locator) {
		String locatorType = null;
		try {
			if (!Strings.isNullOrEmpty(locator)) {
				String locatorValue = locator.split(":")[0];
				if (!Strings.isNullOrEmpty(locatorValue)) {
					locatorType = locatorValue.substring(locatorValue.indexOf("[") + 1, locatorValue.indexOf("]"));
				} else {
					LOGGER.error("Locator is not in correct format in properties file. Please check the file.");
				}
			} else {
				LOGGER.error("Locator is missing from properties file. Please check the property file for the locator");
			}
		} catch (Exception ex) {
			LOGGER.error("Exception in getting locator type from properties file." + ex.getMessage());
		}
		return locatorType;
	}
	public String getUrlOfCurrentPage() {
		return getDriver().getCurrentUrl();
	}

	public final void getUrl(String url) {
		getDriver().get(url);
	}
}
