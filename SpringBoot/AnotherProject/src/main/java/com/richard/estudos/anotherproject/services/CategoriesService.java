package com.richard.estudos.anotherproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.richard.estudos.anotherproject.daos.CategoriesDao;
import com.richard.estudos.anotherproject.errors.NotFoundErrorException;
import com.richard.estudos.anotherproject.models.Category;

@Service
public class CategoriesService {

	@Autowired
	private CategoriesDao categoriesDao;
	
	@Cacheable(value="list-categories")
	public Iterable<Category> findAll() {
		return categoriesDao.findAll();
	}

	public Category save(Category category) {
		return categoriesDao.save(category);
	}

	@Cacheable(value="category", key="#id")
	public Category findById(Long id) {
		Category category = categoriesDao.findOne(id);
		if( category == null ) {
			throw new NotFoundErrorException();
		}
		return category;
	}
	
}
