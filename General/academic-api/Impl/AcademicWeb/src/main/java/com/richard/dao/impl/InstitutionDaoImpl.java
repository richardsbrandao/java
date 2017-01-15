package com.richard.dao.impl;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.richard.dao.InstitutionDao;
import com.richard.request.InstitutionRequest;

public class InstitutionDaoImpl extends AbstractDao implements InstitutionDao {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public InstitutionRequest save(InstitutionRequest request) throws Exception {
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/AcademicRest/institution", request, String.class);
		return new ObjectMapper().readValue(responseEntity.getBody(), InstitutionRequest.class);
	}

}
