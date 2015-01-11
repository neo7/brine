package com.brine.sol;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FetchConfigValues {

	public String getPropertyValue(String key){
		Properties prop = new Properties();
		InputStream input = null;
		String property = null;
		try {
			
			//Hard wired config file location
			input = new FileInputStream("config.properties");
	 
			// load a properties file
			prop.load(input);
	 
			// get the property value 
			property = prop.getProperty(key);
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return property;
		
	}
}
