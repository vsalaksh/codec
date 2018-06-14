package com.tools.formatter.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

import org.w3c.tidy.Tidy;

public class HTMLStringFormatter {
	private static Tidy tidy = new Tidy();
	
	static
	{		
		Properties oProps = new Properties();
		oProps.setProperty("new-blocklevel-tags", "header hgroup article footer nav span i");
		tidy.setConfigurationFromProps(oProps);
		tidy.setTidyMark(false);
		tidy.setTabsize(2);
		//tidy.setIndentAttributes(true);
		tidy.setIndentContent(true);
		//tidy.setSpaces(2);
		tidy.setWraplen(100);
		tidy.setForceOutput(true);
		//tidy.setQuiet(true);
	}
	
	public static String formatHTMLString(String input)
	{
		String formattedString = "";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(4096);
		try
		{
			
			tidy.parse(new ByteArrayInputStream(input.getBytes()), outputStream);
		}
		catch (Exception exc)
		{
			formattedString = "Error while formatting HTML String";
			exc.printStackTrace();
		}
		
		formattedString = outputStream.toString();
		return formattedString;
	}
	
	public static void main(String[] args)
	{
		String jsonStr = "<html><title>this is not formatted</title><body>How are you</body></html>";
		System.out.println(formatHTMLString(jsonStr));
	}
	

}
