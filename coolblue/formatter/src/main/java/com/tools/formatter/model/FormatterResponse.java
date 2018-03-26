package com.tools.formatter.model;

public class FormatterResponse {
	private String output;
	private String errorMessage;

	public FormatterResponse()
	{
		
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
