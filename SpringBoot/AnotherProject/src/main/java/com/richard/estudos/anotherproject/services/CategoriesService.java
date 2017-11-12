package com.richard.estudos.anotherproject.services;

import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.richard.estudos.anotherproject.daos.CategoriesDao;
import com.richard.estudos.anotherproject.errors.NotFoundErrorException;
import com.richard.estudos.anotherproject.models.Category;
import com.richard.estudos.anotherproject.models.Report;
import com.richard.estudos.anotherproject.models.ReportCategoriesStatistics;

@Service
public class CategoriesService {

	private static final Log LOG = LogFactory.getLog(CategoriesService.class);
	
	@Autowired
	private CategoriesDao categoriesDao;
	
	@Autowired
	private ReportsService reportsService;
	
	@Cacheable(value="list-categories")
	public Iterable<Category> findAll() {
		return categoriesDao.findAll();
	}

	public Category save(Category category) {
		return categoriesDao.save(category);
	}

	@Cacheable(value="category", key="#id")
	public Category findById(Long id) {
		Category category = categoriesDao.findOne(id);
		if( category == null ) {
			throw new NotFoundErrorException();
		}
		return category;
	}

	@Async("threadPoolReportAsyncExecutor")
	public void generateReport(UUID externalId, String requester) {
		try {
			List<ReportCategoriesStatistics> categoriesReport = categoriesDao.categoriesReport();
			String reportAsString = toJson(categoriesReport);
			Report report = new Report(externalId, requester);
			report.setContent(reportAsString);
			reportsService.save(report);
		} catch (Exception e) {
			LOG.error(String.format("Error generating report %s", requester));
		}
	}

	private String toJson(List<ReportCategoriesStatistics> categoriesReport) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(categoriesReport);
	}
}
