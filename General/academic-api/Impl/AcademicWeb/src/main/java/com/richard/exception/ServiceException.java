package com.richard.exception;

@SuppressWarnings("serial")
public class ServiceException extends RuntimeException {
	
	public ServiceException(String message) {
		super(message);
	}
	
}
