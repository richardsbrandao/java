package com.richard.errors;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class NotFoundException extends RestHttpException {

	public NotFoundException() {
		super("Recurso nao encontrado", HttpStatus.NOT_FOUND);
	}
	
	public NotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}

}
