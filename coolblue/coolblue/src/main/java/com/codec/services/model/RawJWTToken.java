package com.codec.services.model;

import com.codec.services.util.JSONStringFormatter;

public class RawJWTToken {
	
	private String header = "";
	private String body = "";
	private String signature = "";
	
	public RawJWTToken(String header, String body, String signature)
	{
		this.header = header;
		this.body = body;
		this.signature = signature;
	}
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String toString()
	{
		return JSONStringFormatter.formatJSONString(header) + "\n" + JSONStringFormatter.formatJSONString(body) + "\n";
	}

}
