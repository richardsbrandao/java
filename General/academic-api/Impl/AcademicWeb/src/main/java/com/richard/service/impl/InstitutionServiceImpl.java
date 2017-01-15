package com.richard.service.impl;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.client.HttpClientErrorException;

import com.richard.dao.InstitutionDao;
import com.richard.exception.ServiceException;
import com.richard.exception.ValidateException;
import com.richard.request.InstitutionRequest;
import com.richard.request.RestResponse;
import com.richard.service.InstitutionService;

public class InstitutionServiceImpl implements InstitutionService {

	@Autowired
	private InstitutionDao institutionDao;
	@Autowired
	private MessageSource messageSource;
	

	@Override
	public InstitutionRequest save(InstitutionRequest institutionRequest) throws Exception {
		try {
			return institutionDao.save( institutionRequest );
		} catch (HttpClientErrorException e) {
			String responseBodyAsString = e.getResponseBodyAsString();
			RestResponse restResponse = new ObjectMapper().readValue(responseBodyAsString, RestResponse.class);
			throwException(restResponse);
			return null;
		}
	}

	private void throwException(RestResponse restResponse) throws ValidateException {
		if( restResponse.isUnprocessableEntity() ) {
			String placeHolders = restResponse.getMessage().replace("Erro ao validar a entidade: ", "");
			StringBuilder message = new StringBuilder();
			for(String placeHolder : placeHolders.split(";")) {
				message.append( messageSource.getMessage(placeHolder, null, placeHolder, null) );
			}
			throw new ValidateException(message.toString());
		} else if( restResponse.isInternalServerError() ) {
			throw new ServiceException(restResponse.getMessage());
		}
	}

	@Override
	public List<InstitutionRequest> findAll() {
		return null;
	}

	
}
