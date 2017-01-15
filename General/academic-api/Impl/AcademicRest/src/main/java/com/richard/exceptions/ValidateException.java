package com.richard.exceptions;

import java.util.List;

@SuppressWarnings("serial")
public class ValidateException extends Exception {

	private List<String> reasons;
	
	public ValidateException(List<String> reasons, Class<? extends Object> clazz) {
		super("Erro ao realizar validação da classe: " + clazz.getName());
		this.reasons = reasons;
	}

	public List<String> getReasons() {
		return reasons;
	}
	
}
