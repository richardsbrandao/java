package com.richard.estudos.anotherproject.unit.controllers.requests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.richard.estudos.anotherproject.controllers.requests.CategoryRequest;
import com.richard.estudos.anotherproject.models.Category;
import com.richard.estudos.anotherproject.utils.RequestUtils;

@RunWith(JUnit4.class)
public class CategoryRequestTest {

	private CategoryRequest request;
	
	@Before
	public void setUp() {
		request = RequestUtils.createCategoryRequest();
	}
	
	@Test
	public void valid_convertion_to_model() {
		Category category = request.toModel();
		
		assertThat(category).isNotNull();
		assertThat(category.getId()).isNull();
		assertThat(category.getName()).isEqualTo("GOALKEEPER");
		assertThat(category.getUsers()).isNull();
	}
}
