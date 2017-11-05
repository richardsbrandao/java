package com.richard.estudos.anotherproject.controllers.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.richard.estudos.anotherproject.controllers.responses.ApiError;
import com.richard.estudos.anotherproject.errors.ApiErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ApiErrorException.class)
	public ResponseEntity<ApiError> apiErrorException(ApiErrorException e) {
		return new ResponseEntity<ApiError>(new ApiError(e.getMessage()), e.getHttpStatus());
	}
	
}
