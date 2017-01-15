package com.richard.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.richard.domain.Institution;

@SuppressWarnings("serial")
@XmlRootElement(name="institutions")
@XmlSeeAlso({InstitutionResponse.class})
public class InstitutionListResponse extends ArrayList<InstitutionResponse> implements Serializable {

	public InstitutionListResponse() { }
	
	public InstitutionListResponse(List<Institution> institutions) {
		for( Institution institution : institutions ) {
			add( new InstitutionResponse(institution) );
		}
	}
	
	@XmlElement(name="institution")
	public List<InstitutionResponse> getInstitution() {
		return this;
	}

}
