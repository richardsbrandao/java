package com.richard.json;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@SuppressWarnings("serial")
@JsonSerialize(include=Inclusion.NON_NULL)
public class RestResponse implements Serializable {

	private Integer httpStatus;
	private Integer errorCode;
	private String message;
	
	public static RestResponse OK(String message) {
		RestResponse restResponse = new RestResponse();
		restResponse.message = message;
		restResponse.httpStatus = HttpStatus.OK.value();
		return restResponse;
	}
	
	public Integer getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
