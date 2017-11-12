package com.richard.estudos.anotherproject.system;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.richard.estudos.anotherproject.AbstractTest;
import com.richard.estudos.anotherproject.controllers.responses.CategoriesResponse;
import com.richard.estudos.anotherproject.controllers.responses.CategoryResponse;

@RunWith(SpringRunner.class)
public class ListCategoryController extends AbstractTest {
	@Test
	public void retrieve_categories() {
		ResponseEntity<CategoriesResponse> response = restTemplate.getForEntity(endpoint("/categories/"), CategoriesResponse.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	
		CategoriesResponse responseBody = response.getBody();
		assertThat(responseBody).hasSize(1);
		
		CategoryResponse firstCategoryResponse = responseBody.get(0);
		assertThat(firstCategoryResponse.getId()).isNotNull();
		assertThat(firstCategoryResponse.getName()).isEqualTo("DEFENDER");
	}
}
