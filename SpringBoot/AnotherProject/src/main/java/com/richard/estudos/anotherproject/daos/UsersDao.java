package com.richard.estudos.anotherproject.daos;

import org.springframework.data.repository.CrudRepository;

import com.richard.estudos.anotherproject.models.User;

public interface UsersDao extends CrudRepository<User, Long> {
	
}
