package com.richard.estudos.anotherproject.controllers.requests;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

import com.richard.estudos.anotherproject.models.Category;
import com.richard.estudos.anotherproject.models.User;

@Getter @Setter
public class UserRequest {
	
	private String name;
	private String bornDate;
	private Long categoryId;
	
	public User toModel() {
		User user = new User();
		user.setName(name);
		user.setBornDate(LocalDate.parse(bornDate));
		user.setCategory(new Category(categoryId));
		return user;
	}
	
}
