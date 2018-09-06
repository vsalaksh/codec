package com.tools.security.ds;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.tools.security.util.SignatureMappings;

public class HSSignatureVerifier implements SignatureVerifier {
	
	Base64.Encoder encoder = Base64.getEncoder();
	
	public boolean verifySignature(DigitalSignature signature)
	{
		boolean isSignatureValid = false;
		try
		{
			Mac sig = Mac.getInstance(SignatureMappings.getSignatureAlgorithm(signature.getAlgorithm()));
			Charset asciiCs = Charset.forName("UTF-8");
			SecretKeySpec secret_key = new javax.crypto.spec.SecretKeySpec(signature.getKey().getBytes(asciiCs), "AES");
			sig.init(secret_key);
			final byte[] mac_data = sig.doFinal(signature.getPayload().getBytes(asciiCs));
			String signatureStringBuilt = getBase64URLEncodedValue(mac_data);
			isSignatureValid = signature.getSignature().equals(signatureStringBuilt) ? true : false;
		}
		catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JWT : " + e.getMessage());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JWT : " + e.getMessage());
		}
		
		return isSignatureValid;
	}

	private String getBase64URLEncodedValue(byte[] mac_data) {
		String base64EncodedString = encoder.encodeToString(mac_data);
		base64EncodedString = base64EncodedString.split("=")[0]; // Remove any trailing '='s
        base64EncodedString = base64EncodedString.replace('+', '-'); // 62nd char of encoding
        base64EncodedString = base64EncodedString.replace('/', '_'); // 63rd char of encoding
        //System.out.println(base64EncodedString);
		return base64EncodedString;
	}

}
