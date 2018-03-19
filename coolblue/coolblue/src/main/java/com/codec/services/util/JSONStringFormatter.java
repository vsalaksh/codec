package com.codec.services.util;

import org.codehaus.jackson.map.ObjectMapper;

public class JSONStringFormatter {
	
	public static String formatJSONString(String input)
	{
		String formattedString = "";
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			Object headerJson = mapper.readValue( input , Object.class);
			formattedString = mapper.defaultPrettyPrintingWriter().writeValueAsString(headerJson);
		}
		catch (Exception exc)
		{
			formattedString = "Error while formatting JSON String";
			exc.printStackTrace();
		}
		
		return formattedString;
	}

}
