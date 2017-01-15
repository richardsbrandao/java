package com.richard.test.unit.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.richard.dao.InstitutionDao;
import com.richard.domain.Institution;
import com.richard.errors.NotFoundException;
import com.richard.errors.UnprocessableEntityException;
import com.richard.json.ValidationError;
import com.richard.service.InstitutionService;
import com.richard.service.impl.InstitutionServiceImpl;
import com.richard.test.DomainUtils;
import com.richard.validator.InstitutionValidator;

public class InstitutionServiceTests {

	private InstitutionService service;
	private InstitutionDao dao;
	private InstitutionValidator validator;
	
	private Institution institution;
	
	@Before
	public void setUp() {
		dao = mock( InstitutionDao.class );
		validator = mock( InstitutionValidator.class );
		service = new InstitutionServiceImpl();
		ReflectionTestUtils.setField(service, "institutionDao", dao);
		ReflectionTestUtils.setField(service, "institutionValidator", validator);
		
		institution = DomainUtils.getValidInstitution();
	}
	
	@Test
	public void createInstitution() throws Exception {
		when( validator.isValid( any( Institution.class ) ) ).thenReturn( Boolean.TRUE );
		when( dao.save( any( Institution.class ) ) ).thenReturn( institution );
		
		Institution savedInstitution = service.create(institution);
		
		assertNotNull( savedInstitution );
		verify( validator, times(1)).isValid( any(Institution.class) );
		verify( dao, times(1)).save( any(Institution.class) );
	}
	
	@Test
	public void errorCreateInvalidInstitution() throws ServiceException {
		when( validator.isValid( any(Institution.class) ) ).thenReturn( Boolean.FALSE );
		ArrayList<ValidationError> errorsList = new ArrayList<ValidationError>();
		errorsList.add(new ValidationError());
		when( validator.getReasons() ).thenReturn( errorsList );
		
		try {
			service.create(institution);
			fail("Deveria ter lançado UnprocessableEntityException");
		} catch (UnprocessableEntityException e) { 
			assertEquals( 1, e.getErrors().size() );
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void errorOnSaveInstitution() throws Exception  {
		when( validator.isValid( any(Institution.class) ) ).thenReturn( Boolean.TRUE );
		when( dao.save( any(Institution.class) ) ).thenThrow( RuntimeException.class );
		
		try {
			service.create(institution);
			fail("Deveria ter lançado ServiceException");
		} catch (RuntimeException e) { }
	}
	
	@Test
	public void updateInstitution() throws UnprocessableEntityException, NotFoundException {
		when( validator.isValid( any(Institution.class) ) ).thenReturn( Boolean.TRUE );
		when( dao.findById( anyLong() ) ).thenReturn( DomainUtils.getValidInstitution() );
		when( dao.save( any(Institution.class) ) ).thenReturn( DomainUtils.getValidInstitution() );
		
		Institution institutionRequest = DomainUtils.getValidInstitution();
		service.update(1L, institutionRequest );
		
		verify( validator, times(1)).isValid( any(Institution.class) );
		verify( dao, times(1)).findById( anyLong() );
		verify( dao, times(1)).save( any(Institution.class) );
	}
	
	@Test
	public void errorUpdateInvalidInstitution() throws UnprocessableEntityException, NotFoundException {
		when( validator.isValid( any(Institution.class) ) ).thenReturn( Boolean.TRUE );
		when( dao.findById( anyLong() ) ).thenReturn( null );
		
		Institution institutionRequest = DomainUtils.getValidInstitution();
		
		try {
			service.update(1L, institutionRequest );
			fail("Deveria ter lançado NotFoundException");
		} catch( NotFoundException e ) { }
	}
	
	@Test
	public void errorUpdateInexistentInstitution() throws NotFoundException {
		when( validator.isValid( any(Institution.class) ) ).thenReturn( Boolean.FALSE );
		when( validator.getReasons() ).thenReturn( new ArrayList<ValidationError>( Arrays.asList(new ValidationError()) ) );
		
		Institution institutionRequest = DomainUtils.getValidInstitution();
		
		try {
			service.update(1L, institutionRequest );
			fail("Deveria ter lançado NotFoundException");
		} catch( UnprocessableEntityException e ) { 
			assertEquals( 1, e.getErrors().size() );
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void errorUpdateInstitution() throws UnprocessableEntityException, NotFoundException {
		when( validator.isValid( any(Institution.class) ) ).thenReturn( Boolean.TRUE );
		when( dao.findById( anyLong() ) ).thenReturn( DomainUtils.getValidInstitution() );
		when( dao.save( any(Institution.class) ) ).thenThrow( RuntimeException.class );
		
		Institution institutionRequest = DomainUtils.getValidInstitution();
		try {
			service.update(1L, institutionRequest );
			fail("Deveria ter lançado RuntimeException");
		} catch(RuntimeException e) { }
	}
	
	@Test
	public void findInstitutionById() throws NotFoundException {
		institution.setId(1L);
		when( dao.findById( any(Long.class) ) ).thenReturn( institution );
		
		Institution findedInstitution = service.findById( 1L );
		
		assertEquals( 1L, findedInstitution.getId(), 1 );
		assertEquals( "Cta", findedInstitution.getName() );
	}
	
	@Test
	public void errorOnFindInexistentInstitution() {
		when( dao.findById( any(Long.class) ) ).thenReturn( null );
		
		try {
			service.findById(2L);
			fail("Deveria ter lançado DomainObjectNotFoundException");
		} catch( NotFoundException e ) { }
	}
	
	@Test
	public void findAllInstitutions() {
		when( dao.findAll() ).thenReturn( Arrays.asList( new Institution() ) );
		
		List<Institution> institutions = service.findAll();
		assertEquals( 1, institutions.size() );
	}
	
	@Test
	public void findAllInstitutionsWhenTableIsEmpty() {
		when( dao.findAll() ).thenReturn( new ArrayList<Institution>() );
		
		List<Institution> institutions = service.findAll();
		assertTrue( institutions.isEmpty() );
	}
	
	@Test
	public void deleteInstitution() throws Exception {
		when( dao.findById( anyLong() ) ).thenReturn( DomainUtils.getValidInstitution() );
		doNothing().when( dao ).delete( any(Institution.class) );
		
		service.delete( 1L );
	}
	
	@Test
	public void deteleInexistentInstitution() throws NotFoundException {
		when( dao.findById( anyLong() ) ).thenReturn( null );
		
		try {
			service.delete( 1L );
			fail("Deveria ter lançado NotFoundException");
		} catch ( NotFoundException e ) { }
	}
	
	@Test
	public void errorOnDeleteInstitution() throws NotFoundException {
		when( dao.findById( anyLong() ) ).thenReturn( DomainUtils.getValidInstitution() );
		Mockito.doThrow( RuntimeException.class ).when( dao ).delete( any(Institution.class) );
		
		try {
			service.delete( 1L );
			fail("Deveria ter lançado RuntimeException");
		} catch ( RuntimeException e ) { }
	}
	
}
