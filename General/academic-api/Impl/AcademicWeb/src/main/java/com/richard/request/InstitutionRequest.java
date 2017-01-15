package com.richard.request;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@SuppressWarnings("serial")
@JsonSerialize(include=Inclusion.NON_NULL)
public class InstitutionRequest implements Serializable {

	private Long id;
	private String name;
	
	public InstitutionRequest() { }
	
	public InstitutionRequest(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}