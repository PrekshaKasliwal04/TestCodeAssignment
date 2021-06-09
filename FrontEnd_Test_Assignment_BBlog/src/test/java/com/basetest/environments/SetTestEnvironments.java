package com.basetest.environments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.InputMismatchException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.basesource.action.CommonMethod;
import com.basesource.action.PreDefinedActions;
import com.basesource.driver.DriverFactory;
import com.basesource.utils.ObjectReader;
import com.bblog.constants.ConstantPath;
import com.bblog.pages.HomePage;
import com.testscripts.bblog.CommonTest;

public class SetTestEnvironments extends CommonMethod {
	private final static Logger LOGGER = Logger.getLogger(PreDefinedActions.class);
	public static String TestCaseName;
	private static Properties environmentPageProperties;
	private static ObjectReader environmentPropertiesReader = new ObjectReader();
	public String filePath;
	public static String environment = System.getProperty("environment");
	public static String browser = System.getProperty("browserName");
	public static String buildNumber = System.getProperty("buildNumber");

	@BeforeSuite(alwaysRun = true)
	public final void beforeSuite(ITestContext testContext) throws Exception {
		String suiteName = testContext.getCurrentXmlTest().getSuite().getName();
		LOGGER.info("Executing suite : " + suiteName);
		this.deleteDir(ConstantPath.LOG_PATH);
		init();
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method MethodName, ITestResult result) throws Exception {
		HomePage homePage = new HomePage(PreDefinedActions.getDriver());
		homePage = homePage.getInstance();
		DRIVER = DriverFactory.setDriver(System.getProperty("browserName"), SetTestEnvironments.getEnvironment(System.getProperty("environment")));
		setWebDriver(DRIVER);
		jsDriver();
		TestCaseName = MethodName.getName();
		LOGGER.info("Test Started : " + TestCaseName);
	}

	@AfterMethod(alwaysRun = true)
	public final void aftermethod(ITestResult result, Method MethodName) throws Exception {
		try {
			CommonTest commonTest = new CommonTest();
			commonTest = commonTest.getInstance();
			LOGGER.info("Test Finished  : " + TestCaseName);
		} finally {
			dr.set(null);
			DRIVER.quit();
			LOGGER.info("Browser is closed");
		}
	}

		@AfterSuite(alwaysRun = true)
		public final void afterSuite(ITestContext context) {
	LOGGER.info("Test case execution is completed");
		}

	/**
	 * This method is to read logger file.
	 */
	public final void init() {
		File log4jPropertiesFile = new File(ConstantPath.LOGGER_FILE_PATH);
		if (log4jPropertiesFile.exists()) {
			PropertyConfigurator.configure(ConstantPath.LOGGER_FILE_PATH);
		} else {
			Properties props = new Properties();

			try {
				props.load(
						SetTestEnvironments.class.getClassLoader().getResourceAsStream("properties/log4j.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			PropertyConfigurator.configure(props);
		}
	}

	/**
	 * This method with return URL based on the given environment
	 * 
	 * @param environment
	 * @return
	 */
	public static String getEnvironment(String environment) {
		try {
			environmentPageProperties = environmentPropertiesReader.getObjectRepository("Environment");

			switch (environment.toUpperCase()) {
			case "ASSIGNMENT":
				LOGGER.info("Running Test On : " + environment);

				return environmentPageProperties.getProperty("TESTURL");

			default:
				LOGGER.info("Given : " + environment + " environment is incorrect");
				throw new InputMismatchException(" ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method will delete directory at given path
	 * 
	 * @param path : path to delete directory
	 * @throws IOException
	 */
	public void deleteDir(String path) throws IOException {
		if (new File(path).exists()) {
			File file = new File(path);
			FileUtils.cleanDirectory(file);
			FileUtils.forceDelete(file);
			FileUtils.forceMkdir(file);
		} else {
			File file = new File(path);
			FileUtils.forceMkdir(file);
		}
	}

}
