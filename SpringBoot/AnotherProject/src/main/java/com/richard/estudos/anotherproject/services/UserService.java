package com.richard.estudos.anotherproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richard.estudos.anotherproject.daos.UserDao;
import com.richard.estudos.anotherproject.models.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	public Iterable<User> findAll() {
		return userDao.findAll();
	}
	
	public User save(User user) {
		return userDao.save(user);
	}
}
