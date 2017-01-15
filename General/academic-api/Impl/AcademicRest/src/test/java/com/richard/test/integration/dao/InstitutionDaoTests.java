package com.richard.test.integration.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.hibernate.PropertyValueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.richard.dao.InstitutionDao;
import com.richard.domain.Institution;
import com.richard.test.DomainUtils;
import com.richard.test.integration.AbstractIntegrationTests;

@RunWith(SpringJUnit4ClassRunner.class)
public class InstitutionDaoTests extends AbstractIntegrationTests {

	@Autowired
	private InstitutionDao dao;
	
	@Test
	public void createInstitution() {
		Institution institution = DomainUtils.getValidInstitution();
		dao.save( institution );
		
		Institution savedInstitution = findInstitutionByName("Cta");
		
		assertNotNull( savedInstitution.getId() );
		assertEquals( "Cta", savedInstitution.getName() );
	}
	
	@Test
	public void errorCreateInstitution() {
		try {
			dao.save( new Institution() );
			fail("Deveria ter lançado ConstraintViolationException");
		} catch (PropertyValueException e) { }
	}

	@Test
	public void updateInstitution() {
		Institution institution = dao.findById(1L);
		institution.setName("Escola");
		dao.save( institution );
		
		Institution updatedInstitution = findInstitutionByName("Escola");
		assertEquals( 1L, updatedInstitution.getId(), 1 );
	}
	
	@Test
	public void getInstitution() {
		Institution institution = dao.findById( 1L );
		
		assertEquals( 1L, institution.getId(), 1 );
		assertEquals( "Santa Mônica", institution.getName() );
	}
	
	@Test
	public void getInexistentInstitution() {
		Institution institution = dao.findById( 10L );
		
		assertNull( institution );
	}
	
	@Test
	public void findAllInstitution() {
		List<Institution> institutions = dao.findAll();
		
		assertEquals( 1, institutions.size() );
		
		Institution institution = institutions.get(0);
		assertEquals(1L, institution.getId(), 1);
		assertEquals("Santa Mônica", institution.getName());
	}
	
	@Test
	public void deleteInstitution() {
		Institution institution = new Institution();
		institution.setId(1L);
		
		dao.delete(institution);
		
		assertNull( findInstitutionByName( "Santa Mônica" ) );
	}
	
	@Test
	public void errorOnDeleteInexistentInstitution() {
		Institution institution = new Institution();
		institution.setId(999L);
		
		try {
			dao.delete(institution);
			fail("Deveria ter lançado PropertyValueException");
		} catch( PropertyValueException e ) { }
	}
	
}
