package com.richard.estudos.anotherproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richard.estudos.anotherproject.daos.CategoriesDao;
import com.richard.estudos.anotherproject.models.Category;

@Service
public class CategoriesService {

	@Autowired
	private CategoriesDao categoriesDao;
	
	public Iterable<Category> findAll() {
		return categoriesDao.findAll();
	}

	public Category save(Category category) {
		return categoriesDao.save(category);
	}
	
}
