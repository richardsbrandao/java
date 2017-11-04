package com.richard.estudos.anotherproject.controllers.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.richard.estudos.anotherproject.models.Category;

@Getter @Setter @NoArgsConstructor
public class CategoryResponse {

	private Long id;
	private String name;
	
	public CategoryResponse(Category category) {
		setId(category.getId());
		setName(category.getName());
	}

}
