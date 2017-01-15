package com.richard.service;

import java.util.List;

import com.richard.domain.Institution;
import com.richard.errors.NotFoundException;
import com.richard.errors.UnprocessableEntityException;

public interface InstitutionService {

	Institution create(Institution institution) throws UnprocessableEntityException;
	
	Institution update(Long id, Institution institutionRequest) throws UnprocessableEntityException, NotFoundException;

	Institution findById(Long institutionId) throws NotFoundException;

	List<Institution> findAll();

	void delete(Long institution) throws NotFoundException;
	
}
