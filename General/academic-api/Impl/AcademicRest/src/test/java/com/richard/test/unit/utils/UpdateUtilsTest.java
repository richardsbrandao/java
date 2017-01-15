package com.richard.test.unit.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.richard.domain.Institution;
import com.richard.test.DomainUtils;
import com.richard.utils.UpdateUtils;

@RunWith(JUnit4.class)
public class UpdateUtilsTest {

	@Test
	public void updateUsingSetterMethods() throws Exception {
		Institution institution = DomainUtils.getValidInstitution();
		institution.setId(1L);
		
		Institution newInstitutionValues = new Institution();
		newInstitutionValues.setId(2L);
		newInstitutionValues.setName("UpdateUtils");
		
		UpdateUtils.mergeProperties(Institution.class, institution, newInstitutionValues, new String[] {"id", "name"});
		
		assertEquals( 2L, institution.getId(), 1 );
		assertEquals( "UpdateUtils", institution.getName() );
	}
	
	@Test
	public void updateUsingWhiteList() throws Exception {
		Institution institution = DomainUtils.getValidInstitution();
		institution.setId(1L);
		
		Institution newInstitutionValues = new Institution();
		newInstitutionValues.setId( 2L );
		newInstitutionValues.setName( "UpdateUtils" );
		
		UpdateUtils.mergeProperties(Institution.class, institution, newInstitutionValues, new String[] {"name"});
		
		assertEquals( 1L, institution.getId(), 1 );
		assertEquals( "UpdateUtils", institution.getName() );
	}
	
	@Test
	public void errorOnUpdateWithoutSetter() {
		TestClass testClass = new TestClass();
		testClass.attr = "Test";
		
		TestClass newTestClass = new TestClass();
		newTestClass.attr = "Outro";
		
		try {
			UpdateUtils.mergeProperties(TestClass.class, testClass, newTestClass, new String[]{ "attr" });
			fail("Deveria ter lançado NoSuchMethodException");
		} catch (NoSuchMethodError e) { }
	}
	
	private class TestClass {
		
		public String attr;

		@SuppressWarnings("unused")
		public String getAttr() {
			return attr;
		}
		
	}
	
}
