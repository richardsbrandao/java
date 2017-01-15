package com.richard.dao;

import com.richard.request.InstitutionRequest;

public interface InstitutionDao {

	InstitutionRequest save(InstitutionRequest institutionRequest) throws Exception;

}
