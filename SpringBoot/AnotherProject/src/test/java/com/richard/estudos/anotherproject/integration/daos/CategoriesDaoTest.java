package com.richard.estudos.anotherproject.integration.daos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.richard.estudos.anotherproject.AbstractTest;
import com.richard.estudos.anotherproject.daos.CategoriesDao;
import com.richard.estudos.anotherproject.models.ReportCategoriesStatistics;

@RunWith(SpringRunner.class)
public class CategoriesDaoTest extends AbstractTest {

	@Autowired
	private CategoriesDao categoriesDao;
	
	@Test
	public void generate_categories_statistics() {
		List<ReportCategoriesStatistics> categoriesReport = categoriesDao.categoriesReport();
		
		assertThat(categoriesReport).hasSize(1);
		
		ReportCategoriesStatistics firstCategory = categoriesReport.get(0);
		assertThat(firstCategory.getCategoryName()).isEqualTo("DEFENDER");
		assertThat(firstCategory.getNumberOfUsers()).isEqualTo(1L);
	}
	
}
