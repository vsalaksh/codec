package com.tools.security.ds;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class DigitalSignatureProcessor {
	
	enum SupportedSignatureTypes
	{
		HS256,
		HS384,
		HS512,
		RS256,
		RS384,
		RS512
	}
	
	public boolean verify(DigitalSignature signature) throws SignatureNotSupportedException
	{
		boolean isValidSignature = false;
		String alg = signature.getAlgorithm();
		SignatureVerifier signatureVerifier = getSignatureVerifier(alg);
		if (signatureVerifier != null)
		{
			isValidSignature = signatureVerifier.verifySignature(signature);
		}
		else
		{
			System.out.println("JWT : No Signature Verifier found for Algorithm : " + alg);
		}
		return isValidSignature;
	}
	
	private SignatureVerifier getSignatureVerifier(String alg) throws SignatureNotSupportedException {
		
		SignatureVerifier signatureVerifier = null;
		SupportedSignatureTypes val = SupportedSignatureTypes.valueOf(alg);
		switch(val)
		{
		case HS256:
		case HS384:
		case HS512:
			signatureVerifier = new HSSignatureVerifier();
			break;
		case RS256:
		case RS384:
		case RS512:
			signatureVerifier = new RSASignatureVerifier();
			break;
		default:
			throw new SignatureNotSupportedException();
		  
		}
		return signatureVerifier;
	}
/*
	public boolean verify(String header, String payload, String signature, String signingPublicKey, String algorithm)
	{
		boolean isSignatureValid = false;
		try {
			Mac sig = Mac.getInstance(algorithm);
			//KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
			final Charset asciiCs = Charset.forName("UTF-8");
			final SecretKeySpec secret_key = new javax.crypto.spec.SecretKeySpec(signingPublicKey.getBytes(asciiCs), "AES");
	        System.out.println("Hi");
	        //sig.ini
			sig.init(secret_key);
	       // sig.update((header + "." + payload).getBytes(asciiCs));
			//X509EncodedKeySpec keySpec = new X509EncodedKeySpec(signingPublicKey.getBytes());
			//PublicKey pubKey = keyFactory.generatePublic(keySpec);
			//sig.initVerify(pubKey);
			//sig.update(payload.getBytes());
			//isSignatureValid = sig.verify(signature.getBytes());
	        System.out.println(new String(asciiCs.encode(header).array()));
			final byte[] mac_data = sig.doFinal((header + "." + payload).getBytes(asciiCs));
			Base64.Encoder encoder = Base64.getEncoder();
			String result = "";
	        for (final byte element : mac_data)
	        {
	           result += Integer.toString((element & 0xff) + 0x100, 16).substring(1);
	        }
	        System.out.println("Result:[" + result + "]");
	        String base64EncodedString = encoder.encodeToString(mac_data);
	        base64EncodedString = base64EncodedString.split("=")[0]; // Remove any trailing '='s
	        base64EncodedString = base64EncodedString.replace('+', '-'); // 62nd char of encoding
	        base64EncodedString = base64EncodedString.replace('/', '_'); // 63rd char of encoding
	        System.out.println(base64EncodedString);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return isSignatureValid;
		
	}
	
	public static String bytesToHex(byte[] bytes) {
	    final  char[] hexArray = "0123456789ABCDEF".toCharArray();
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	public static void main(String[] args) throws SignatureNotSupportedException
	{
		String header = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9";
		String payload = "eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE1MzU3MjA4NDQsImV4cCI6MTU2NzI1Njg0NCwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0";
		String signature = "tH7tf3xOpWvwKhaqVknC8lehybo7PeLQ86NF_vUjUyk";
		String algorithm = "HS256";
		String key = "goodday";
		DigitalSignatureProcessor signatureProcessor = new DigitalSignatureProcessor();
		for (Provider provider: Security.getProviders()) {
			  System.out.println(provider.getName());
			  for (String key1: provider.stringPropertyNames())
			    System.out.println("\t" + key1 + "\t" + provider.getProperty(key1));
			}
		DigitalSignature signatureObj = new DigitalSignature(header + "." + payload, signature, key, algorithm);
		//boolean signatureValid = signatureProcessor.verify(header, payload, signature, key, algorithm);
		boolean signatureValid = signatureProcessor.verify(signatureObj);
		System.out.println("Is Signature Valid: " + signatureValid);
		
	}
*/
}
