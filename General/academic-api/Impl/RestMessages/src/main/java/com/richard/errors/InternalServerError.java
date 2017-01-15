package com.richard.errors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.richard.json.RestResponse;

@SuppressWarnings("serial")
public class InternalServerError extends HttpError {

	public InternalServerError(HttpServletResponse response) {
		super(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public RestResponse toResponse() {
		RestResponse response = new RestResponse();
		response.setHttpStatus( HttpStatus.INTERNAL_SERVER_ERROR.value() );
		response.setErrorCode( 50001 );
		response.setMessage("Erro inexperado no servidor ao realizar a operação.");
		return response;
	}
	
}
