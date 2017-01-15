package com.richard.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.richard.controller.InstitutionController;
import com.richard.dao.InstitutionDao;
import com.richard.domain.Institution;
import com.richard.errors.NotFoundException;
import com.richard.errors.UnprocessableEntityException;
import com.richard.service.InstitutionService;
import com.richard.validator.InstitutionValidator;

public class InstitutionServiceImpl implements InstitutionService {

	private static final Logger LOG = Logger.getLogger( InstitutionController.class );

	@Autowired
	private InstitutionDao institutionDao;
	@Autowired
	private InstitutionValidator institutionValidator;

	@Override
	public Institution create(Institution institution) throws UnprocessableEntityException {
		if( ! institutionValidator.isValid(institution) ) {
			throw new UnprocessableEntityException(institutionValidator.getReasons());
		}
		
		return institutionDao.save(institution);
	}
	
	@Override
	public Institution update(Long id, Institution institutionRequest) throws UnprocessableEntityException, NotFoundException {
		if( ! institutionValidator.isValid(institutionRequest) ) {
			throw new UnprocessableEntityException(institutionValidator.getReasons());
		}
		
		Institution institution = findById(id);
		institution.mergeProperties(institutionRequest);
		return institutionDao.save(institution);
	}

	@Override
	public Institution findById(Long institutionId) throws NotFoundException {
		Institution institution = institutionDao.findById(institutionId);
		
		if( institution == null ) {
			LOG.info("Nenhuma instituição com ID " + institutionId + " foi encontrado");
			throw new NotFoundException();
		}
		
		return institution;
	}

	@Override
	public List<Institution> findAll() {
		return institutionDao.findAll();
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		Institution institution = findById(id);
		institutionDao.delete(institution);
	}
	
}
