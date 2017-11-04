package com.richard.estudos.anotherproject.system;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.richard.estudos.anotherproject.controllers.requests.CategoryRequest;
import com.richard.estudos.anotherproject.controllers.responses.CategoryResponse;
import com.richard.estudos.anotherproject.utils.RequestUtils;

@RunWith(SpringRunner.class)
public class CreateCategoryController extends AbstractSystemTest {

	@Test
	public void create_category() {
		CategoryRequest requestBody = RequestUtils.createCategoryRequest();
		ResponseEntity<CategoryResponse> response = restTemplate.postForEntity(endpoint("/categories/"), requestBody, CategoryResponse.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		CategoryResponse categoryResponse = response.getBody();
		assertThat(categoryResponse.getId()).isNotNull();
		assertThat(categoryResponse.getName()).isEqualTo(requestBody.getName());
	}
	
}
