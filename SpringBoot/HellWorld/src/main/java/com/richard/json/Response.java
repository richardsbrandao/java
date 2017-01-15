package com.richard.json;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

@SuppressWarnings("serial")
@JsonSerialize(include=Inclusion.NON_NULL)
@XmlRootElement
public class Response implements Serializable {

	private Integer httpStatus;
	private String message;
	private List<ValidationError> errors;

	public Response() { }
	
	public Response(Integer httpStatuCode, String message) {
		this.httpStatus = httpStatuCode;
		this.message = message;
	}
	
	public Response(Integer httpStatuCode, String message, List<ValidationError> errors) {
		this.httpStatus = httpStatuCode;
		this.message = message;
		this.errors = errors;
	}
	
	public Integer getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<ValidationError> getErrors() {
		return errors;
	}
	public void setErrors(List<ValidationError> errors) {
		this.errors = errors;
	}

	public String toJson() {
		try {
			return new ObjectMapper().writeValueAsString(this);
		} catch (Exception e) {
			return "Erro no parse da resposta";
		}
	}

	public String toXml() {
		try {
			StringWriter writer = new StringWriter();
			JAXBContext context = JAXBContext.newInstance( getClass() );
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(this, writer);
			return writer.toString();
		} catch (JAXBException e) {
			return "Erro no parse da resposta";
		}
	}
}
