package com.richard.service;

import java.io.Serializable;
import java.util.List;

import com.richard.request.InstitutionRequest;

public interface InstitutionService {

	public Serializable save(InstitutionRequest institutionRequest) throws Exception;

	public List<InstitutionRequest> findAll();

}
