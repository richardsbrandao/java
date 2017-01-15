package com.estudos.ejb.core.erros;

import java.util.List;

@SuppressWarnings("serial")
public class ValidationException extends Exception {

	private List<String> errors;

	public ValidationException(String message, List<String> erros) {
		super(message);
		this.errors = erros;
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
}
