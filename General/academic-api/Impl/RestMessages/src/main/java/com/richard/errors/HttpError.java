package com.richard.errors;

import java.io.Serializable;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.richard.json.RestResponse;

@SuppressWarnings("serial")
public abstract class HttpError implements Serializable {

	public HttpError(HttpServletResponse response, HttpStatus status) {
		response.setContentType( "application/json" );
		response.setStatus( status.value() );
	}
	
	public abstract RestResponse toResponse();
	
}
