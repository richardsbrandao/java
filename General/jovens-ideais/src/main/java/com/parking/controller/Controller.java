package com.parking.controller;

import com.parking.helper.Message;

public class Controller {

	protected final String SUCCESS = "success";
	protected final String ERROR = "error";
	protected static final String AJAX = "ajax";
	
	private String url = "http://localhost:8080/JovensIdeais/";
	protected Message message;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Message getMessage() {
		return message;
	}
	
	public void setMessage(Message message) {
		this.message = message;
	}

}
