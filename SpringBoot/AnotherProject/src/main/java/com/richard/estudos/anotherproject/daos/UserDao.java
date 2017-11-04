package com.richard.estudos.anotherproject.daos;

import org.springframework.data.repository.CrudRepository;

import com.richard.estudos.anotherproject.models.User;

public interface UserDao extends CrudRepository<User, Long> {
	
}
