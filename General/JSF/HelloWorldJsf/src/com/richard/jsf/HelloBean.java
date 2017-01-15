package com.richard.jsf;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@SuppressWarnings("serial")
@ManagedBean(name="hello")
public class HelloBean implements Serializable {

	private String name = "Olá";

	public String welcome() {
		return "welcome";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
