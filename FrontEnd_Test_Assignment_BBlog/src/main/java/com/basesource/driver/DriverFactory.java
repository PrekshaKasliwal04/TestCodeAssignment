package com.basesource.driver;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.bblog.constants.CommonVariables;
import com.bblog.constants.ConstantPath;

public class DriverFactory {
	private final static Logger LOGGER = Logger.getLogger(DriverFactory.class);

	public static WebDriver setDriver(String browserType, String appURL) {
		WebDriver driver;
		switch (browserType.toUpperCase()) {
		case "CHROME":
			driver = initChromeDriver(appURL);
			break;
		case "FIREFOX":
			driver = initFirefoxDriver(appURL);
			break;
		default:
			LOGGER.info("Browser : " + browserType + " Currently we support windows based browsers - Chrome and Firefox, Launching Firefox as Default browser..");
			driver = initFirefoxDriver(appURL);
		}
		return driver;
	}

	// Method to open chrome browser
	private static WebDriver initChromeDriver(String appURL) {
		LOGGER.info("Launching chrome browser..");

		if (new File(ConstantPath.CHROME_EXE_PATH).exists()) {
			// This is for Eclipse IDE
			System.setProperty("webdriver.chrome.driver", ConstantPath.CHROME_EXE_PATH);
		} else {
			File chromeDriver = new File("lib" + File.separator + "chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
		}
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		driver.navigate().to("https://candidatex:qa-is-cool@qa-task.backbasecloud.com/");		
		driver.manage().timeouts().implicitlyWait(CommonVariables.IMPLICITWAIT, TimeUnit.SECONDS);
		return driver;
	}

	// Method to open Firefox browser
	private static WebDriver initFirefoxDriver(String appURL) {
		LOGGER.info("Launching Firefox browser..");

		System.setProperty("webdriver.gecko.driver", "lib" + File.separator + "geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability("marionette", true);
	    WebDriver	driver = new FirefoxDriver();
		driver.get(appURL);
		driver.navigate().to("https://candidatex:qa-is-cool@qa-task.backbasecloud.com/");		
		return driver;
	
	}

}
