package com.richard.estudos.anotherproject.controllers.responses;

import java.util.ArrayList;

import lombok.NoArgsConstructor;

import com.richard.estudos.anotherproject.models.Category;

@SuppressWarnings("serial")
@NoArgsConstructor
public class CategoriesResponse extends ArrayList<CategoryResponse> {

	public CategoriesResponse(Iterable<Category> categories) {
		if( categories != null )
			categories.forEach(category -> add(new CategoryResponse(category)));
	}

}
