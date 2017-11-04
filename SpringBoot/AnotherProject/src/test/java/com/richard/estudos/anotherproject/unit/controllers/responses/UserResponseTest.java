package com.richard.estudos.anotherproject.unit.controllers.responses;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.richard.estudos.anotherproject.controllers.responses.UserResponse;
import com.richard.estudos.anotherproject.models.User;
import com.richard.estudos.anotherproject.utils.ModelUtils;

@RunWith(JUnit4.class)
public class UserResponseTest {

	private User user;

	@Before
	public void setUp() {
		this.user = ModelUtils.user(1L, "User Name", "2000-10-11");
	}
	
	@Test
	public void to_json() {
		UserResponse userResponse = new UserResponse(user);
		assertThat(userResponse.getId()).isEqualTo(1L);
		assertThat(userResponse.getName()).isEqualTo("User Name");
		assertThat(userResponse.getBornDate()).isEqualTo("2000-10-11");
		assertThat(userResponse.getCategoryId()).isEqualTo(1L);
	}
	
}
