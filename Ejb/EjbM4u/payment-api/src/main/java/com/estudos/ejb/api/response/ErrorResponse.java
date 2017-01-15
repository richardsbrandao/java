package com.estudos.ejb.api.response;

import java.util.List;

public class ErrorResponse {

	private String code;
	private String message;
	private List<String> errors;
	
	public ErrorResponse(String code, String message, List<String> errors) {
		this.code = code;
		this.message = message;
		this.errors = errors;
	}

	public ErrorResponse() { }
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
}
