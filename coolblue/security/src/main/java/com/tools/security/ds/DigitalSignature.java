package com.tools.security.ds;

public class DigitalSignature {
	
	private String payload;
	private String signature;
	private String key;
	private String algorithm;
	
	public DigitalSignature(String payload, String signature, String key, String algorithm)
	{
		this.payload = payload;
		this.signature = signature;
		this.key = key;
		this.algorithm = algorithm;
	}

	public String getPayload() {
		return payload;
	}

	public String getSignature() {
		return signature;
	}

	public String getKey() {
		return key;
	}

	public String getAlgorithm() {
		return algorithm;
	}
	
	

}
