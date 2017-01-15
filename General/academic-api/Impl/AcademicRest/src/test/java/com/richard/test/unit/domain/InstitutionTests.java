package com.richard.test.unit.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.richard.domain.Institution;
import com.richard.test.DomainUtils;

@RunWith(JUnit4.class)
public class InstitutionTests {

	@Test
	public void mergeProperties() {
		Institution institution = DomainUtils.getValidInstitution();
		
		Institution institutionRequest = new Institution();
		institutionRequest.setId(2L);
		institutionRequest.setName("Outra");
		
		institution.mergeProperties(institutionRequest);
		
		assertEquals( "Outra", institution.getName() );
		assertNull( institution.getId() );
	}
	
}
