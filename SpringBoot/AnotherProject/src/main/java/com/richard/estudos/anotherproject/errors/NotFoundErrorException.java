package com.richard.estudos.anotherproject.errors;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class NotFoundErrorException extends ApiErrorException {

	public NotFoundErrorException() {
		super("Resource not found!");
		setHttpStatus(HttpStatus.NOT_FOUND);
	}
	
}
