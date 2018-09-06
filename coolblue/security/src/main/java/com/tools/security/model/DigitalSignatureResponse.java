package com.tools.security.model;

public class DigitalSignatureResponse {
	
	private boolean isValid;
	private String errorMessage;
	
	public DigitalSignatureResponse()
	{
		
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	

}
