package com.jpm.accountvalidation.model;

import java.util.Arrays;

public class AccountValidation {
	
	private String accountNumber;
	private String[] sources;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String[] getSources() {
		return sources;
	}
	
	public void setSources(String[] sources) {
		this.sources = sources;
	}
	
}
