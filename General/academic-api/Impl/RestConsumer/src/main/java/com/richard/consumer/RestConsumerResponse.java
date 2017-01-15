package com.richard.consumer;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.codehaus.jackson.map.ObjectMapper;

public class RestConsumerResponse {

	private HttpStatus httpStatus;
	private HttpMethod httpMethod;
	private String responseText;

	public RestConsumerResponse(HttpStatus status, HttpMethod method) {
	    this.httpStatus = status;
	    this.httpMethod = method;
	}
	  
	public boolean is204() {
		return HttpStatus.NO_CONTENT.equals(httpStatus);
	}
	
	@SuppressWarnings("unchecked")
	public<T extends Object> T fromJsonTo(Class<T> clazz) throws Exception {
		if( is204() ) {
			return (T) "";
		}
		
		try {
			return new ObjectMapper().readValue(responseText, clazz);
		} catch(Exception e) {
			throw new Exception("Ocorreu um erro ao realizar o parsing do response ["+responseText+"] para a classe ["+clazz.getSimpleName()+"]", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public<T extends Object> T fromXmlTo(Class<T> clazz) throws Exception {
		if( is204() ) {
			return (T) "";
		}
		
		try {
			JAXBContext context = JAXBContext.newInstance( clazz );
			Unmarshaller marshaller = context.createUnmarshaller();
		    return (T) marshaller.unmarshal( new StringReader(responseText) );
		} catch(Exception e) {
			throw new Exception("Ocorreu um erro ao realizar o parsing do response ["+responseText+"] para a classe ["+clazz.getSimpleName()+"]", e);
		}
	}

	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

}
