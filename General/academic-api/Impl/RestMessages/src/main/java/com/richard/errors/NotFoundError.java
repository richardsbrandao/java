package com.richard.errors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.richard.json.RestResponse;

@SuppressWarnings("serial")
public class NotFoundError extends HttpError {

	public NotFoundError(HttpServletResponse response) {
		super(response, HttpStatus.NOT_FOUND);
	}

	@Override
	public RestResponse toResponse() {
		RestResponse response = new RestResponse();
		response.setHttpStatus( HttpStatus.NOT_FOUND.value() );
		response.setErrorCode( 40401 );
		response.setMessage("Recurso não encontrado com este identificador.");
		return response;
	}

}
