package com.richard.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.richard.domain.Institution;

@SuppressWarnings("serial")
@JsonSerialize(include=Inclusion.NON_NULL)
@XmlRootElement(name="institution")
public class InstitutionResponse implements Serializable {

	private Long id;
	private String name;
	
	public InstitutionResponse() { }
	
	public InstitutionResponse(Long id) {
		this.id = id;
	}
	
	public InstitutionResponse(Institution institution) {
		this( institution.getId() );
		this.name = institution.getName();
	}

	public Institution toDomain() {
		Institution institution = new Institution();
		institution.setId(id);
		institution.setName(name);
		return institution;
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
