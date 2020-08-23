package com.tools.decompiler.config;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import com.tools.config.ConfigurationException;
import com.tools.config.util.*;

public class DecompilerConfig {
	
	private static volatile DecompilerConfig config = null;
	
	private static Object lock = new Object();
	
	private Document configHolder = null;
	
	private String tempClassPath = null;
	
	private int bufferSize = 4096;
	
	private String customClassPath = null;
	
	private int fileSizeLimit = 0;
	
	public static DecompilerConfig getInstance()
	{
		if (config == null)
		{
			synchronized(lock)
			{
				if (config == null)
				{
					config = new DecompilerConfig();
					try {
						config.configHolder = (Document) ConfigHelper.getConfig("decompiler-config.xml");
					} catch (ConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		return config;
	}
	
	private DecompilerConfig()
	{
		
	}
	
	public static boolean refresh()
	{
		return false;
	}
	
	public String getTempClassPath()
	{
		if (tempClassPath != null)
			return tempClassPath;
		XPathFactory xpathFactory = XPathFactory.newInstance();

        // Create XPath object
        XPath xpath = xpathFactory.newXPath();
		XPathExpression expr;
		String tempClassPath = "";
		try {
			expr = xpath.compile("/Config/TempClasspath/text()");
		
        tempClassPath = (String) expr.evaluate(configHolder, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tempClassPath;
	}
	
	public int getBufferSize()
	{
		if (bufferSize != 4096)
			return bufferSize;
		XPathFactory xpathFactory = XPathFactory.newInstance();

        // Create XPath object
        XPath xpath = xpathFactory.newXPath();
		XPathExpression expr;
		try {
			expr = xpath.compile("/Config/BufferSize/text()");
		
			bufferSize = Integer.parseInt((String) expr.evaluate(configHolder, XPathConstants.STRING));
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bufferSize;
	}
	
	public String getCustomClassPath()
	{
		if (customClassPath != null)
			return customClassPath;
		XPathFactory xpathFactory = XPathFactory.newInstance();

        // Create XPath object
        XPath xpath = xpathFactory.newXPath();
		XPathExpression expr;
		String tempClassPath = "";
		try {
			expr = xpath.compile("/Config/CustomClasspath/text()");
		
			customClassPath = (String) expr.evaluate(configHolder, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customClassPath;
	}
	
	public int getFileSizeLimit()
	{
		if (fileSizeLimit != 0)
			return fileSizeLimit;
		XPathFactory xpathFactory = XPathFactory.newInstance();

        // Create XPath object
        XPath xpath = xpathFactory.newXPath();
		XPathExpression expr;
		String tempClassPath = "";
		try {
			expr = xpath.compile("/Config/FileSizeLimit/text()");
		
			String fileSizeLimitStr = (String) expr.evaluate(configHolder, XPathConstants.STRING);
			fileSizeLimit = Integer.parseInt(fileSizeLimitStr);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileSizeLimit;
	}

}
