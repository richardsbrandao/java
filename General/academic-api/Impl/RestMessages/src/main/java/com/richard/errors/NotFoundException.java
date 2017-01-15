package com.richard.errors;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class NotFoundException extends RestHttpException {

	public NotFoundException() {
		super("Recurso não encontrado", HttpStatus.NOT_FOUND);
	}

}
