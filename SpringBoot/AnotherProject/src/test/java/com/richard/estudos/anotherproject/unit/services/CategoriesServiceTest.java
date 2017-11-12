package com.richard.estudos.anotherproject.unit.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.richard.estudos.anotherproject.daos.CategoriesDao;
import com.richard.estudos.anotherproject.errors.NotFoundErrorException;
import com.richard.estudos.anotherproject.models.Category;
import com.richard.estudos.anotherproject.models.Report;
import com.richard.estudos.anotherproject.models.ReportCategoriesStatistics;
import com.richard.estudos.anotherproject.services.CategoriesService;
import com.richard.estudos.anotherproject.services.ReportsService;
import com.richard.estudos.anotherproject.utils.ModelUtils;

@RunWith(MockitoJUnitRunner.class)
public class CategoriesServiceTest {

	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Mock
	private CategoriesDao categoriesDao;

	@Mock
	private ReportsService reportsService;
	
	@InjectMocks
	private CategoriesService categoriesService;
	
	@Test
	public void save_category() {
		Category category = ModelUtils.category(null, "Some name");
		when(categoriesDao.save(category)).thenReturn(category);
		
		categoriesService.save(category);
		
		verify(categoriesDao, times(1)).save(category);
	}
	
	@Test
	public void find_all_categories() {
		when(categoriesDao.findAll()).thenReturn(new ArrayList<Category>());
		
		Iterable<Category> categories = categoriesService.findAll();
		
		assertThat(categories).isEmpty();
		verify(categoriesDao, times(1)).findAll();
	}
	
	@Test
	public void find_existing_category_by_id() {
		when(categoriesDao.findOne(1L)).thenReturn(ModelUtils.category(1L, "Some name"));
		
		Category category = categoriesService.findById(1L);
		
		assertThat(category.getId()).isEqualTo(1L);
		verify(categoriesDao, times(1)).findOne(1L);
	}
	
	@Test
	public void find_not_existing_category_by_id() {
		when(categoriesDao.findOne(1L)).thenReturn(null);
		expected.expect(NotFoundErrorException.class);
		
		categoriesService.findById(1L);
		verify(categoriesDao, times(1)).findOne(1L);
	}
	
	@Test
	public void generate_categories_must_save_the_report() {
		when(categoriesDao.categoriesReport()).thenReturn(new ArrayList<ReportCategoriesStatistics>());
		doNothing().when(reportsService).save(any(Report.class));
		
		UUID externalId = UUID.randomUUID();
		categoriesService.generateReport(externalId, "Richard");
		
		ArgumentCaptor<Report> saveReportArgumentCaptor = ArgumentCaptor.forClass(Report.class);
		verify(reportsService, times(1)).save(saveReportArgumentCaptor.capture());
		
		assertThat(saveReportArgumentCaptor.getValue().getId()).isNull();
		assertThat(saveReportArgumentCaptor.getValue().getExternalId()).isEqualTo(externalId);
		assertThat(saveReportArgumentCaptor.getValue().getRequester()).isEqualTo("Richard");
		assertThat(saveReportArgumentCaptor.getValue().getContent()).isEqualTo("[]");
	}
}
