package com.jpm.accountvalidation.controller;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jpm.accountvalidation.model.Account;
import com.jpm.accountvalidation.model.AccountValidation;
import com.jpm.accountvalidation.model.Result;
import com.jpm.accountvalidation.model.ResultResponse;

@RestController
public class AccountValidationController {
		
	@Value("${source1}")
	private String source1;
	
	@Value("${source2}")
	private String source2;
	
	private String reqSource1 = "source1";
	private String reqSource2 = "source2";
	
	@GetMapping()	
	@ResponseBody
	public ResponseEntity<ResultResponse> validateAccount(
			@RequestBody AccountValidation acct) 
			throws IOException, InterruptedException {
		
		List<Result> resultResponses = new ArrayList<>();
		
		// if request's sources are null, use sources from properties file
		if (acct.getSources() == null) {
			acct.setSources(new String[] {reqSource1, reqSource2});
		}
		
		// for each source, send a validation request to that source
		for (String source : acct.getSources()) {

			HttpClient client = HttpClient.newHttpClient();

			String reqUri = source.equals(reqSource1) ? source1 : source2;
			
			String json = "{ \"accountNumber\": \"" + acct.getAccountNumber() + "\" } ";

			HttpRequest request = 
					HttpRequest.newBuilder()
					.POST(HttpRequest.BodyPublishers.ofString(json))
					.uri(URI.create(reqUri))
	                .setHeader("User-Agent", "Java 11 HttpClient Bot") 
	                .header("Content-Type", "application/json")
					.build();

			HttpResponse<String> response = 
					client.send(request, HttpResponse.BodyHandlers.ofString());

			// set isValid
			if (response.body().indexOf("true") > -1) {
				resultResponses.add(new Result(source, true));
			} else {
				resultResponses.add(new Result(source, false));
			}
		}
		
		return new ResponseEntity<ResultResponse>(
				new ResultResponse(resultResponses), HttpStatus.OK);
	}
}
