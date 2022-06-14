package com.jpm.accountvalidation.model;

import java.util.List;

public class ResultResponse {
	
	List<Result> result;
	
	public ResultResponse(List<Result> result) {
		super();
		this.result = result;
	}

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}


	
	

}
