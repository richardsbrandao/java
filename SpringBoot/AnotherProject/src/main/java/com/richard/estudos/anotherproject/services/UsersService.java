package com.richard.estudos.anotherproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richard.estudos.anotherproject.daos.UsersDao;
import com.richard.estudos.anotherproject.models.User;

@Service
public class UsersService {
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private CategoriesService categoriesService;
	
	public Iterable<User> findAll() {
		return usersDao.findAll();
	}
	
	public User save(User user) {
		categoriesService.findById(user.getCategory().getId());
		return usersDao.save(user);
	}
}
