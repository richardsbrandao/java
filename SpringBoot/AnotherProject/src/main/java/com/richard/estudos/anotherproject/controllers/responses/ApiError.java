package com.richard.estudos.anotherproject.controllers.responses;

import java.util.HashMap;

import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@NoArgsConstructor
public class ApiError extends HashMap<String, String> {

	public ApiError(String message) {
		put("message", message);
	}
	
}
