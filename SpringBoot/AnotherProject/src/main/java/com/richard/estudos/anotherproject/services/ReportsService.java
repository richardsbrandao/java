package com.richard.estudos.anotherproject.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richard.estudos.anotherproject.daos.ReportsDao;
import com.richard.estudos.anotherproject.errors.NotFoundErrorException;
import com.richard.estudos.anotherproject.models.Report;

@Service
public class ReportsService {

	@Autowired
	private ReportsDao reportsDao;
	
	public Report findByExternalIdAndRequester(UUID externalId, String requester) {
		Report report = reportsDao.findByExternalIdAndRequester(externalId, requester);
		if(report == null) {
			throw new NotFoundErrorException();
		}
		return report;
	}

	public void save(Report report) {
		reportsDao.save(report);
	}

}
