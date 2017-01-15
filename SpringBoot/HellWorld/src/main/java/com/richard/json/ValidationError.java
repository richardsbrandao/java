package com.richard.json;

public class ValidationError {

	private String field;
	private RestrictionType type;
	private String message;
	
	public ValidationError() { }
	
	public ValidationError(String field, RestrictionType type, String message) {
		this.field = field;
		this.type = type;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public RestrictionType getType() {
		return type;
	}

	public void setType(RestrictionType type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
