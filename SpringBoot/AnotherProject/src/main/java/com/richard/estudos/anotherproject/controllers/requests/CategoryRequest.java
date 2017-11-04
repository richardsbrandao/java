package com.richard.estudos.anotherproject.controllers.requests;

import com.richard.estudos.anotherproject.models.Category;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class CategoryRequest {

	private String name;

	public Category toModel() {
		Category category = new Category();
		category.setName(name);
		return category ;
	}

}
