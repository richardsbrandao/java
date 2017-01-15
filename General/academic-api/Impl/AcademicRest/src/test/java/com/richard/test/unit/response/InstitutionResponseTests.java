package com.richard.test.unit.response;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.richard.domain.Institution;
import com.richard.response.InstitutionResponse;
import com.richard.test.DomainUtils;

@RunWith(JUnit4.class)
public class InstitutionResponseTests {

	@Test
	public void createJson() {
		Institution institution = DomainUtils.getValidInstitution();
		institution.setId(1L);
		
		InstitutionResponse institutionJson = new InstitutionResponse(institution);
		
		assertEquals( 1L, institutionJson.getId(), 1 );
		assertEquals( "Cta", institution.getName() );
	}
	
	@Test
	public void toDomain() {
		InstitutionResponse json = new InstitutionResponse();
		json.setId(1L);
		json.setName("Cta");
		
		Institution domain = json.toDomain();
		
		assertEquals( 1L, domain.getId(), 1 );
		assertEquals( "Cta", domain.getName() );
	}
	
}
