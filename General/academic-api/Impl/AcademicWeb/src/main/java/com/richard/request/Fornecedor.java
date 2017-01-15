package com.richard.request;

import java.util.List;

public class Fornecedor {

	private String name;
	private List<Contato> contacts;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Contato> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contato> contacts) {
		this.contacts = contacts;
	}
	
}
