package com.richard.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="hello")
@RequestScoped
public class HelloWorld  {
	
	private String name;

	public String say() {
		return "saySomething";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
