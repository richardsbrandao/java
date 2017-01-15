package com.richard.dao.impl;

import java.util.List;

import com.richard.dao.InstitutionDao;
import com.richard.domain.Institution;

public class InstitutionDaoImpl extends AbstractDao<Institution> implements InstitutionDao {

	@Override
	public Institution save(Institution institution) {
		return super.saveEntity(institution);
	}

	@Override
	public Institution findById(Long id) {
		return super.findById(id, Institution.class);
	}

	@Override
	public List<Institution> findAll() {
		return super.findAll( Institution.class );
	}

	@Override
	public void delete(Institution institution) {
		super.delete(institution);
	}

}
