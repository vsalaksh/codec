package com.codec.services.model;

public class JWTResponse extends CodecResponse {

	private String decodedJWTHeader;
	private String decodedJWTBody;
	private String alg;
	
	public JWTResponse()
	{
		super();
	}
	
	public JWTResponse(String output, String decodedJWTHeader, String decodedJWTBody, String alg)
	{
		super(output, "");
		this.decodedJWTHeader = decodedJWTHeader;
		this.decodedJWTBody = decodedJWTBody;
		this.setAlg(alg);
	}

	public String getDecodedJWTHeader() {
		return decodedJWTHeader;
	}

	public String getDecodedJWTBody() {
		return decodedJWTBody;
	}

	public String getAlg() {
		return alg;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}
	
}
