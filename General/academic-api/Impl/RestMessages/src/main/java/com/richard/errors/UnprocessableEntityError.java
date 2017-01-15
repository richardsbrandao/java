package com.richard.errors;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.google.common.base.Joiner;
import com.richard.json.RestResponse;

@SuppressWarnings("serial")
public class UnprocessableEntityError extends HttpError {

	private List<String> reasons;

	public UnprocessableEntityError(List<String> reasons, HttpServletResponse response) {
		super(response, HttpStatus.UNPROCESSABLE_ENTITY);
		this.reasons = reasons;
	}

	@Override
	public RestResponse toResponse() {
		RestResponse response = new RestResponse();
		response.setHttpStatus( HttpStatus.UNPROCESSABLE_ENTITY.value() );
		response.setErrorCode( 42201 );
		response.setMessage("Erro ao validar a entidade: " + Joiner.on("; ").join(reasons));
		return response;
	}

}
