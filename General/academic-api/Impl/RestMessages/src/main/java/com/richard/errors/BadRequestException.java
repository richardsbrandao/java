package com.richard.errors;

import org.springframework.http.HttpStatus;


@SuppressWarnings("serial")
public class BadRequestException extends RestHttpException {

	public BadRequestException(String parseMessage) {
		super("Request mal formatado: " + parseMessage, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public String toString() {
		return "{\"message\":" + getMessage() +", \"httpStatus\":400}";
	}

}
