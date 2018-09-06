package com.tools.security.ds;

public interface SignatureVerifier {
	
	public boolean verifySignature(DigitalSignature signature);

}
