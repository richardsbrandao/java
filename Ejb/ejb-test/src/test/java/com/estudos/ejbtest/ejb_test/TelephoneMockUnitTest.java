package com.estudos.ejbtest.ejb_test;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

@RunWith(JUnit4.class)
public class TelephoneMockUnitTest {

	private TelephoneService service;

	@Before
	public void setUp() {
		this.service = new TelephoneService();
		EntityManager mockEM = Mockito.mock(EntityManager.class);
		System.out.println(mockEM);
		try {
			Field field = service.getClass().getField("em");
			field.setAccessible(true);
			field.set(service, mockEM);
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.postConstruct();
	}
	
	@Test
	public void test_mock() {
		service.save();
	}
	
}
