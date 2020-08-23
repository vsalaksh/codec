package com.tools.decompiler.services.model;

public class DecompilerResponse {
	
	private String decompiledCode;
	private String fileName;
	
	public DecompilerResponse()
	{
		
	}

	public String getDecompiledCode() {
		return decompiledCode;
	}

	public void setDecompiledCode(String decompiledCode) {
		this.decompiledCode = decompiledCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	

}
