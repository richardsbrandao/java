package com.richard.estudos.anotherproject.unit.controllers.responses;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.assertj.core.api.Assertions.*;

import com.richard.estudos.anotherproject.controllers.responses.CategoriesResponse;
import com.richard.estudos.anotherproject.models.Category;
import com.richard.estudos.anotherproject.utils.ModelUtils;

@RunWith(JUnit4.class)
public class CategoriesResponseTest {

	private List<Category> categories;

	@Before
	public void setUp() {
		this.categories = ModelUtils.categories(1L);
	}
	
	@Test
	public void to_json() {
		CategoriesResponse categoriesResponse = new CategoriesResponse(categories);
		assertThat(categoriesResponse).hasSize(1);
	}
	
	@Test
	public void to_json_null() {
		CategoriesResponse categoriesResponse = new CategoriesResponse(null);
		assertThat(categoriesResponse).hasSize(0);
	}
	
}
