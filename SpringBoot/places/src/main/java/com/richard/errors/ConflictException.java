package com.richard.errors;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ConflictException extends RestHttpException {

	public ConflictException() {
		this("Este recurso ja existe");
	}
	
	public ConflictException(String message) {
		super(message, HttpStatus.CONFLICT);
	}

}
