package com.richard.estudos.anotherproject.system;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.richard.estudos.anotherproject.AbstractTest;
import com.richard.estudos.anotherproject.controllers.responses.UserResponse;
import com.richard.estudos.anotherproject.controllers.responses.UsersResponse;

@RunWith(SpringRunner.class)
public class ListUserController extends AbstractTest {
	@Test
	public void retrieve_users() {
		ResponseEntity<UsersResponse> response = restTemplate.getForEntity(endpoint("/users/"), UsersResponse.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	
		UsersResponse responseBody = response.getBody();
		assertThat(responseBody).hasSize(1);
		
		UserResponse firstUsersResponse = responseBody.get(0);
		assertThat(firstUsersResponse.getId()).isNotNull();
		assertThat(firstUsersResponse.getName()).isEqualTo("Nesta");
		assertThat(firstUsersResponse.getBornDate()).isEqualTo("1976-03-19");
		assertThat(firstUsersResponse.getCategoryId()).isNotNull();
	}
}
