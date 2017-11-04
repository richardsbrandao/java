package com.richard.errors;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class InternalServerException extends RestHttpException {

	public InternalServerException() {
		super("Erro interno no servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
