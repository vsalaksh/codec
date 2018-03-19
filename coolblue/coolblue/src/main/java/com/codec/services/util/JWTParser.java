package com.codec.services.util;

import java.util.Base64;

import com.codec.services.exception.InvalidInputException;
import com.codec.services.model.RawJWTToken;

public class JWTParser {
	
	private static Base64.Decoder decoder = Base64.getDecoder();
	
	public static RawJWTToken parse(String input) throws InvalidInputException
	{
		String[] split_string = input.split("\\.");
		if (split_string.length != 3)
			throw new InvalidInputException("Invalid JWT Token");
		String base64EncodedHeader = split_string[0];
		String base64EncodedBody = split_string[1];
		String base64EncodedSignature = split_string[2];
		String jwtHeader = new String(decoder.decode(base64EncodedHeader));
		String jwtBody = new String(decoder.decode(base64EncodedBody));
		//String jwtSignature = new String(decoder.decode(base64EncodedSignature));
		
		RawJWTToken jwtToken = new RawJWTToken(jwtHeader, jwtBody, "");
		
		return jwtToken;
	}

}
