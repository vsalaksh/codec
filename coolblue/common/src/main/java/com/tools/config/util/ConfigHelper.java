package com.tools.config.util;

import com.tools.config.ConfigurationException;
import com.tools.config.impl.FileDataSource;

/**
 * This class reads the system property named devtoolzone.config.type and initialise the 
 * System Configuration from the corresponding underlying resource ie File/DB
 * @author laarunac
 *
 */
public class ConfigHelper {
	
	
	
	public static Object getConfig(String fileName) throws ConfigurationException
	{
		String filePath = System.getProperty("CONFIG_PATH");
		FileDataSource dataSource = new FileDataSource(filePath, fileName);
		return dataSource.getConfig();
	}

}
