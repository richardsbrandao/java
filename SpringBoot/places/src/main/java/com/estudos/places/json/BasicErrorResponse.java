package com.estudos.places.json;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.richard.json.ValidationError;

@JsonSerialize(include=Inclusion.NON_NULL)
public class BasicErrorResponse {

	private Integer httpStatus;
	private String message;
	private List<ValidationError> errors;
	
	public BasicErrorResponse(String message, Integer httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}
	
	public BasicErrorResponse() { }
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationError> errors) {
		this.errors = errors;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}
	
}
