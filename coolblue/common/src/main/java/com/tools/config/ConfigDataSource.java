package com.tools.config;

import org.w3c.dom.Document;

public interface ConfigDataSource {
	public Object loadConfig() throws ConfigurationException;
	
	public Document getConfig() throws ConfigurationException;

}
