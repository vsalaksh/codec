package com.tools.security.model;

public class DigitalSignatureRequest {
	
	private String payload;
	private String signature;
	private String base64EncodedKey;
	private String alg;
	private String sharedSecret;
	
	public DigitalSignatureRequest()
	{
		
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getBase64EncodedKey() {
		return base64EncodedKey;
	}

	public void setBase64EncodedKey(String base64EncodedKey) {
		this.base64EncodedKey = base64EncodedKey;
	}

	public String getAlg() {
		return alg;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}

	public String getSharedSecret() {
		return sharedSecret;
	}

	public void setSharedSecret(String sharedSecret) {
		this.sharedSecret = sharedSecret;
	}
	
	

}
