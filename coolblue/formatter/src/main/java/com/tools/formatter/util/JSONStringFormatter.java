package com.tools.formatter.util;

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
	
	public static void main(String[] args)
	{
		String jsonStr = "{\"array\": [1,2,3],\"boolean\": true,\"null\": null,\"number\": 123,\"object\": {\"a\": \"b\",\"c\": \"d\",\"e\": \"f\"},\"string\": \"Hello World\"}";
		System.out.println(formatJSONString(jsonStr));
	}

}
