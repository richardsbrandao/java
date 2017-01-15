package com.richard.errors;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.richard.json.ValidationError;

@SuppressWarnings("serial")
public class UnprocessableEntityException extends RestHttpException {

	public UnprocessableEntityException(List<ValidationError> errors) {
		super("Erro ao validar a entidade", HttpStatus.UNPROCESSABLE_ENTITY, errors);
	}

}
