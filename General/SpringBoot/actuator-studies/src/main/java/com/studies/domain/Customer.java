package com.studies.domain;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class Customer extends ResourceSupport {

	private String id;
	private String name;
	private String cpf;

	public Customer(String id, String name, String cpf) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
	}

	public Customer() {  }
	
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
