package com.richard.errors;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.richard.json.ValidationError;


@SuppressWarnings("serial")
public class BadRequestException extends RestHttpException {

	public BadRequestException(String message, List<ValidationError> errors) {
		super(message, HttpStatus.BAD_REQUEST, errors);
	}
	
	public BadRequestException(String parseMessage) {
		super("Request mal formatado: " + parseMessage, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public String toString() {
		return "{\"message\":" + getMessage() +", \"httpStatus\":400}";
	}

}
