package com.richard.estudos.anotherproject.controllers.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.richard.estudos.anotherproject.models.User;

@Getter @Setter @NoArgsConstructor
public class UserResponse {

	private Long id;
	private String name;
	private String bornDate;
	private Long categoryId;
	
	public UserResponse(User user) {
		setId(user.getId());
		setName(user.getName());
		setBornDate(user.getBornDate().toString());
		setCategoryId(user.getCategory().getId());
	}

}
