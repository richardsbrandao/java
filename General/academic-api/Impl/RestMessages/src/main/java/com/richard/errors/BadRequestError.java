package com.richard.errors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.richard.json.RestResponse;


@SuppressWarnings("serial")
public class BadRequestError extends HttpError {

	private String parseMessage;

	public BadRequestError(String parseMessage, HttpServletResponse response) {
		super(response, HttpStatus.BAD_REQUEST);
		this.parseMessage = parseMessage;
	}

	@Override
	public RestResponse toResponse() {
		RestResponse response = new RestResponse();
		response.setHttpStatus( HttpStatus.BAD_REQUEST.value() );
		response.setErrorCode( 40001 );
		response.setMessage( "Erro ao executar o parse do Json: " + parseMessage );
		return response;
	}

}
