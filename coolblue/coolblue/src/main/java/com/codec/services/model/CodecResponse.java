package com.codec.services.model;

public class CodecResponse {
	
	private String output;
	private String errorMessage;
	
	public CodecResponse()
	{
		
	}
	
	public CodecResponse(String output, String errorMessage)
	{
		this.output = output;
		this.errorMessage = errorMessage;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
