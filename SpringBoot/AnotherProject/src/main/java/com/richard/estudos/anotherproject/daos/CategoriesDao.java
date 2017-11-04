package com.richard.estudos.anotherproject.daos;

import org.springframework.data.repository.CrudRepository;

import com.richard.estudos.anotherproject.models.Category;

public interface CategoriesDao extends CrudRepository<Category, Long> {

}
