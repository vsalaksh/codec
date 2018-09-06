package com.tools.security.ds;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import com.tools.security.util.SignatureMappings;

public class RSASignatureVerifier implements SignatureVerifier
{

	public boolean verifySignature(DigitalSignature signature) {
		
		boolean isSignatureValid = false;
		System.out.println("JWT : RSA Signature Verifier");
		try
		{
			Charset asciiCs = Charset.forName("UTF-8");
			Signature sig = Signature.getInstance(SignatureMappings.getSignatureAlgorithm(signature.getAlgorithm()));
			X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(signature.getKey().getBytes(asciiCs));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			sig.initVerify(keyFactory.generatePublic(pubKeySpec));
			sig.update(signature.getPayload().getBytes(asciiCs));
			isSignatureValid = sig.verify(signature.getSignature().getBytes(asciiCs));
		}
		catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JWT : " + e.getMessage());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JWT : " + e.getMessage());
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JWT : " + e.getMessage());
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JWT : " + e.getMessage());
		}
		
		return isSignatureValid;
	}

}
