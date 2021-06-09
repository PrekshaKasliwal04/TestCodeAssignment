package com.basesource.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.bblog.constants.ConstantPath;

public class ObjectReader {
	private Properties property = new Properties();

	public final Properties getObjectRepository(String filename) throws IOException {
		// Read object repository file
		File propertiesFile = new File(ConstantPath.PROPERTIES_FOLDER_PATH + filename + ".properties");		
		InputStream stream;
		if (propertiesFile.exists()) {
			stream = new FileInputStream(propertiesFile);
			property.load(stream);
		}
		return property;
	}

	public final Properties getData(String data) throws IOException {
		// Read object repository file
		
		File propertiesFile = new File(ConstantPath.DATA_FOLDER_PATH + File.separator + data.toUpperCase() + ".properties");
		InputStream stream;
		if (propertiesFile.exists()) {
			stream = new FileInputStream(propertiesFile);
			property.load(stream);
		} 
		return property;
	}
}