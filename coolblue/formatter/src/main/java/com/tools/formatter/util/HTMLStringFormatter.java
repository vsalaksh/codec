package com.tools.formatter.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.w3c.tidy.Tidy;

public class HTMLStringFormatter {
	
	public static String formatHTMLString(String input)
	{
		String formattedString = "";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(4096);
		try
		{
			Tidy tidy = new Tidy();
			tidy.setTidyMark(false);
			tidy.setTabsize(2);
			//tidy.setIndentAttributes(true);
			tidy.setIndentContent(true);
			//tidy.setSpaces(2);
			tidy.setWraplen(100);
			tidy.setForceOutput(true);
			//tidy.setQuiet(true);
			tidy.parse(new ByteArrayInputStream(input.getBytes()), outputStream);
		}
		catch (Exception exc)
		{
			formattedString = "Error while formatting JSON String";
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
