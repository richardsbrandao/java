package com.richard.test.unit.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.richard.domain.Institution;
import com.richard.json.RestrictionType;
import com.richard.json.ValidationError;
import com.richard.test.DomainUtils;
import com.richard.validator.InstitutionValidator;

@RunWith(JUnit4.class)
public class InstitutionValidatorTests {

	private InstitutionValidator validator;
	
	@Before
	public void setUp() {
		validator = new InstitutionValidator();
	}
	
	@Test
	public void validInstitution() {
		assertTrue( validator.isValid( DomainUtils.getValidInstitution() ) );
	}
	
	@Test
	public void invalidInstitution() {
		assertFalse( validator.isValid( new Institution() ) );
		assertEquals( 1, validator.getReasons().size() );
		
		List<ValidationError> reasons = validator.getReasons();
		assertEquals( "name", reasons.get(0).getField() );
		assertEquals( RestrictionType.REQUIRED, reasons.get(0).getType() );
		assertEquals( "institution.error.name.blank", reasons.get(0).getMessage() );
	}
	
	@Test
	public void invalidateNullInstitution() {
		assertFalse( validator.isValid( null ) );
		assertEquals( 1, validator.getReasons().size() );
		
		ValidationError reason = validator.getReasons().get(0);
		assertEquals( "institution", reason.getField() );
		assertEquals( RestrictionType.INVALID, reason.getType() );
		assertEquals( "institution.error.domain.null", reason.getMessage() );
	}
	
}
