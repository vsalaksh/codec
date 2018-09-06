package com.tools.security.util;

import java.util.ArrayList;
import java.util.List;

import com.tools.security.model.DigitalSignatureRequest;

public class DigitalSignatureUtil {

	public static String validateRequest(DigitalSignatureRequest req) {
		// TODO Auto-generated method stub
		String validationStatus = "SUCCESS";
		List<String> validationErrorMessages = new ArrayList<String>();
		validatePayload(req.getPayload(), validationErrorMessages);
		validateSignature(req.getSignature(), validationErrorMessages);
		validateAlgorithm(req.getAlg(), validationErrorMessages);
		validateSigningKey(req, validationErrorMessages);
		if (!validationErrorMessages.isEmpty())
		{
			validationStatus = "FAILURE";
			for (String errMsg : validationErrorMessages)
			{
				validationStatus += "\n" + errMsg;
			}
		}
		return validationStatus;
	}
	
	private static void validateSigningKey(DigitalSignatureRequest req,
			List<String> validationErrorMessages) {
		String signingKey = getSigningKey(req);
		if (isNullOrEmpty(signingKey))
		{
			validationErrorMessages.add("Signing Key is not valid");
		}
		
		return;
		
	}

	private static void validateAlgorithm(String alg, List<String> validationErrorMessages) {
		String algorithm = SignatureMappings.getSignatureAlgorithm(alg);
		if (isNullOrEmpty(algorithm))
		{
			validationErrorMessages.add("JWT Signature Algorithm not valid");
		}
		
		return;
	}

	private static void validateSignature(String signature, List<String> validationErrorMessages) {
		if (isNullOrEmpty(signature))
		{
			validationErrorMessages.add("JWT Signature can't be empty");
		}
		
		return;
		
	}

	private static void validatePayload(String payload, List<String> validationErrorMessages) {
		if (isNullOrEmpty(payload))
		{
			validationErrorMessages.add("JWT Header and Body can't be empty");
		}
		
		return;
	}

	private static boolean isNullOrEmpty(String str)
	{
		boolean isNullOrEmpty = false;
		if (str == null || str.trim().isEmpty())
		{
			isNullOrEmpty = true;
		}
		
		return isNullOrEmpty;
	}

	public static String getSigningKey(DigitalSignatureRequest req) {
		String signingKey = null;
		String algorithm = req.getAlg();
		if (algorithm != null && algorithm.startsWith("HS"))
		{
			signingKey = req.getSharedSecret();
		}
		else
		{
			signingKey = req.getBase64EncodedKey();
		}
		return signingKey;
	}

}
