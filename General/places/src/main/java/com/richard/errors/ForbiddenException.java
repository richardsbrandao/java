package com.richard.errors;

import org.springframework.http.HttpStatus;


@SuppressWarnings("serial")
public class ForbiddenException extends RestHttpException {

	public ForbiddenException() {
		super("Email ou senha invalidos", HttpStatus.FORBIDDEN);
	}
	
	public ForbiddenException(String message) {
		super(message, HttpStatus.FORBIDDEN);
	}
	
	@Override
	public String toString() {
		return "{\"message\":" + getMessage() +", \"httpStatus\":403}";
	}

}
