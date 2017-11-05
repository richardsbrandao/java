package com.richard.estudos.anotherproject.unit.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.richard.estudos.anotherproject.daos.CategoriesDao;
import com.richard.estudos.anotherproject.errors.NotFoundErrorException;
import com.richard.estudos.anotherproject.models.Category;
import com.richard.estudos.anotherproject.services.CategoriesService;
import com.richard.estudos.anotherproject.utils.ModelUtils;

@RunWith(MockitoJUnitRunner.class)
public class CategoriesServiceTest {

	@Rule
	public ExpectedException expected = ExpectedException.none();
	
	@Mock
	private CategoriesDao categoriesDao;
	
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
	
}
