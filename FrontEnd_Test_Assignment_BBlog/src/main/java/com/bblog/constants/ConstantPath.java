package com.bblog.constants;

import java.io.File;

public class ConstantPath {
	public static final String LOGGER_FILE_PATH = System.getProperty("user.dir") + File.separator + "properties" + File.separator + "Log4j.properties";
	public static final String PROPERTIES_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "properties" + File.separator;
	public static final String CHROME_EXE_PATH = System.getProperty("user.dir") + File.separator + "lib" + File.separator + "chromedriver.exe";
	public static final String FIREFOX_EXE_PATH = System.getProperty("user.dir") + File.separator + "lib" + File.separator + "geckodriver.exe";
	public static final String DATA_FOLDER_PATH = System.getProperty("user.dir") + File.separator + "testdata" + File.separator;
	public static final String LOG_PATH = System.getProperty("user.dir") + File.separator + "log" + File.separator;
}
