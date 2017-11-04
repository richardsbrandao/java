package com.richard.errors;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;

import com.richard.json.Response;
import com.richard.json.ValidationError;

@SuppressWarnings("serial")
public abstract class RestHttpException extends ServletException {

	private HttpStatus status;
	private List<ValidationError> errors;

	public RestHttpException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
	public RestHttpException(String message, HttpStatus status, List<ValidationError> errros) {
		super(message);
		this.status = status;
		this.errors = errros;
	}
	
	public Integer getStatus() {
		return status.value();
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

	public String toJson() {
		return new Response(status.value(), getMessage(), errors).toJson();
	}
	
	public String toXml() {
		return new Response(status.value(), getMessage(), errors).toXml(); 
	}

	public String to(String[] accepts) {
		for( String acceptHeader : accepts ) {
			if( acceptHeader.equals("application/json") ) {
				return toJson();
			}
			if( acceptHeader.equals("application/xml") ) {
				return toXml();
			}
		}
		
		return toJson();
	}
	
}
