package com.richard.consumer;

public enum HttpMethod {

	GET, DELETE, PATCH, POST, PUT;
	  
	public static HttpMethod findByMethod(String code) {
		for( HttpMethod method : values() ) {
			if( method.name().equals(code.toUpperCase()) ) {
				return method;
			}
		}
		return null;
	}
	
}
