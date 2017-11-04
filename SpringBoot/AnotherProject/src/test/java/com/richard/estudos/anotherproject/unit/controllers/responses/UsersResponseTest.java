package com.richard.estudos.anotherproject.unit.controllers.responses;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.richard.estudos.anotherproject.controllers.responses.UsersResponse;
import com.richard.estudos.anotherproject.models.User;
import com.richard.estudos.anotherproject.utils.ModelUtils;

@RunWith(JUnit4.class)
public class UsersResponseTest {

	private List<User> users;

	@Before
	public void setUp() {
		this.users = ModelUtils.users(1L);
	}
	
	@Test
	public void to_json() {
		UsersResponse usersResponse = new UsersResponse(users);
		assertThat(usersResponse).hasSize(1);
	}
	
	@Test
	public void to_json_null() {
		UsersResponse usersResponse = new UsersResponse(null);
		assertThat(usersResponse).hasSize(0);
	}
	
}
