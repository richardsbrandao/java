package com.richard.exceptions;

@SuppressWarnings("serial")
public class ServiceException extends Exception {

	public ServiceException(String message, Exception cause) {
		super(message, cause);
	}
	
}
