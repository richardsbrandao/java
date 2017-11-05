package com.richard.estudos.anotherproject.system;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.richard.estudos.anotherproject.controllers.requests.UserRequest;
import com.richard.estudos.anotherproject.controllers.responses.ApiError;
import com.richard.estudos.anotherproject.controllers.responses.UserResponse;
import com.richard.estudos.anotherproject.daos.CategoriesDao;
import com.richard.estudos.anotherproject.models.Category;
import com.richard.estudos.anotherproject.utils.RequestUtils;

@RunWith(SpringRunner.class)
public class CreateUserController extends AbstractSystemTest {

	@Autowired
	private CategoriesDao categoriesDao;
	
	@Test
	public void create_user() {
		Category someCategory = categoriesDao.findAll().iterator().next();
		UserRequest requestBody = RequestUtils.createUserRequest();
		requestBody.setCategoryId(someCategory.getId());
		ResponseEntity<UserResponse> response = restTemplate.postForEntity(endpoint("/users/"), requestBody, UserResponse.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		UserResponse userResponse = response.getBody();
		assertThat(userResponse.getId()).isNotNull();
		assertThat(userResponse.getName()).isEqualTo(requestBody.getName());
		assertThat(userResponse.getBornDate()).isEqualTo(requestBody.getBornDate());
	}
	
	@Test
	public void create_user_with_invalid_category() {
		UserRequest requestBody = RequestUtils.createUserRequest();
		requestBody.setCategoryId(99999L);
		ResponseEntity<ApiError> response = restTemplate.postForEntity(endpoint("/users/"), requestBody, ApiError.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		
		ApiError errorResponse = response.getBody();
		assertThat(errorResponse.get("message")).isEqualTo("Resource not found!");
	}
	
}
