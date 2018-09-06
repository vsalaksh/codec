package com.tools.security.util;

import java.util.HashMap;
import java.util.Map;

public class SignatureMappings {
	
	static Map<String, String> algoMap = new HashMap<String, String>();
	
	static
	{
		algoMap.put("HS256", "HMACSHA256");
		algoMap.put("HS384", "HMACSHA384");
		algoMap.put("HS512", "HMACSHA512");
		algoMap.put("RS256", "SHA256withRSA");
		algoMap.put("RS384", "SHA384withRSA");
		algoMap.put("RS512", "SHA512withRSA");
		
	}
	
	public static String getSignatureAlgorithm(String input)
	{
		return algoMap.get(input);
	}

}
