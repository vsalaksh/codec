package com.tools.config.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.tools.config.ConfigDataSource;
import com.tools.config.ConfigurationException;

public class FileDataSource implements ConfigDataSource{
	
	private final String filePath;
	private final String fileName;
	
	private Document config;
	
	public FileDataSource(String filePath, String fileName)
	{
		this.filePath = filePath;
		this.fileName = fileName;
	}

	public Document loadConfig() throws ConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;
        try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse(new File(filePath, fileName));
		} catch (ParserConfigurationException exc) {
			throw new ConfigurationException(exc);
		}
        catch (SAXException exc) {
        	throw new ConfigurationException(exc);
		} catch (IOException exc) {
			throw new ConfigurationException(exc);
		}
        if (doc != null)
        {
        	config = doc;
        }
        return doc;
        
	}

	public Document getConfig() throws ConfigurationException {
		if (config == null)
			loadConfig();
		return config;
	}

}
