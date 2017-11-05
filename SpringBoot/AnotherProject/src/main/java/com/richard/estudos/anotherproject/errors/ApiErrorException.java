package com.richard.estudos.anotherproject.errors;

import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
@Getter @Setter
public class ApiErrorException extends RuntimeException {

	private HttpStatus httpStatus;

	public ApiErrorException(String message) {
		super(message);
	}	
}
