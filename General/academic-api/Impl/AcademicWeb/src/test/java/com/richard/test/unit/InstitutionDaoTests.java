package com.richard.test.unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.mockito.Mockito.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.richard.dao.InstitutionDao;
import com.richard.dao.impl.InstitutionDaoImpl;
import com.richard.request.InstitutionRequest;
import com.richard.util.RequestUtil;

@RunWith(JUnit4.class)
public class InstitutionDaoTests {

	private InstitutionDao institutionDao;
//	private RequestUtil requestUtil;
	private RestTemplate restTemplate;
	
	@Before
	public void onSetUp() {
		institutionDao = new InstitutionDaoImpl();
		restTemplate = mock( RestTemplate.class );
		ReflectionTestUtils.setField(institutionDao, "restTemplate", restTemplate);
	}
	
	@Test
	public void saveInstitution() {
		ResponseEntity<String> response = new ResponseEntity<String>("ok", HttpStatus.OK);
		when( restTemplate.postForEntity(any( String.class ), any( InstitutionRequest.class ), eq( String.class )) ).thenReturn( response );
		
//		InstitutionRequest request = new InstitutionRequest(id)
//		institutionDao.post(request);
	}
	
	public void errorOnSaveInstitution() {
		
	}
	
}
