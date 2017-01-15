package com.parking.helper;

public class Message {
	private String type;
	private String message;
	
	public String getType() {
		return type;
	}
	public String getMessage() {
		return message;
	}
	
	public void addSuccess(String message) {
		type = "Success";
		this.message = message;
	}
	
	public void addError(String message) {
		type = "Error";
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "Message [" + type + ", " + message + "]";
	}
	
}