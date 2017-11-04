package com.richard.estudos.anotherproject.unit.controllers.requests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.assertj.core.api.Assertions.*;

import com.richard.estudos.anotherproject.controllers.requests.UserRequest;
import com.richard.estudos.anotherproject.models.User;
import com.richard.estudos.anotherproject.utils.RequestUtils;

@RunWith(JUnit4.class)
public class UserRequestTest {

	private UserRequest request;
	
	@Before
	public void setUp() {
		request = RequestUtils.createUserRequest();
	}
	
	@Test
	public void valid_convertion_to_model() {
		User user = request.toModel();
		
		assertThat(user).isNotNull();
		assertThat(user.getId()).isNull();
		assertThat(user.getName()).isEqualTo("Richard Brandao");
		assertThat(user.getBornDate()).isNotNull().isEqualTo("1991-11-26");
		assertThat(user.getCategory()).isNotNull();
		assertThat(user.getCategory().getId()).isEqualTo(1L);
	}
	
}
