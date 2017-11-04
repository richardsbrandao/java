package com.richard.estudos.anotherproject.system;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.richard.estudos.anotherproject.controllers.requests.UserRequest;
import com.richard.estudos.anotherproject.controllers.responses.UserResponse;
import com.richard.estudos.anotherproject.utils.RequestUtils;

@RunWith(SpringRunner.class)
public class CreateUserController extends AbstractSystemTest {

	@Test
	public void create_user() {
		UserRequest requestBody = RequestUtils.createUserRequest();
		ResponseEntity<UserResponse> response = restTemplate.postForEntity(endpoint("/users/"), requestBody, UserResponse.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		
		UserResponse userResponse = response.getBody();
		assertThat(userResponse.getId()).isNotNull();
		assertThat(userResponse.getName()).isEqualTo(requestBody.getName());
		assertThat(userResponse.getBornDate()).isEqualTo(requestBody.getBornDate());
	}
	
}
