package com.richard.estudos.anotherproject.daos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.richard.estudos.anotherproject.models.Category;
import com.richard.estudos.anotherproject.models.ReportCategoriesStatistics;

public interface CategoriesDao extends CrudRepository<Category, Long> {
			
	@Query("SELECT new com.richard.estudos.anotherproject.models.ReportCategoriesStatistics(c.name, count(1)) FROM Category c JOIN c.users u GROUP BY c.name")
	public List<ReportCategoriesStatistics> categoriesReport();
	
}
