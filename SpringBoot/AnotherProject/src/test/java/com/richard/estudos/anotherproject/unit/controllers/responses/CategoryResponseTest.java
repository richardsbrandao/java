package com.richard.estudos.anotherproject.unit.controllers.responses;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.richard.estudos.anotherproject.controllers.responses.CategoryResponse;
import com.richard.estudos.anotherproject.models.Category;
import com.richard.estudos.anotherproject.utils.ModelUtils;

@RunWith(JUnit4.class)
public class CategoryResponseTest {

	private Category category;

	@Before
	public void setUp() {
		this.category = ModelUtils.category(1L, "Category Name");
	}
	
	@Test
	public void to_json() {
		CategoryResponse categoryResponse = new CategoryResponse(category);
		assertThat(categoryResponse.getId()).isEqualTo(1L);
		assertThat(categoryResponse.getName()).isEqualTo("Category Name");
	}
	
}
