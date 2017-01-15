package com.richard.dao;

import java.util.List;

import com.richard.domain.Institution;

public interface InstitutionDao {

	Institution save(Institution institution);
	
	Institution findById(Long id);

	List<Institution> findAll();

	void delete(Institution institution);

}
