package com.jpm.accountvalidation.model;

public class Result {
	
	private String source;
	private boolean isValid;
	
	public Result(String source, boolean isValid) {
		this.source = source;
		this.isValid = isValid;
	}	
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public boolean isIsValid() {
		return isValid;
	}
	
	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}
}
