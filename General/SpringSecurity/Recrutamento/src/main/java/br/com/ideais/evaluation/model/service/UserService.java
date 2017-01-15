package br.com.ideais.evaluation.model.service;

import br.com.ideais.evaluation.model.dao.UserDao;
import br.com.ideais.evaluation.model.entity.User;

public class UserService {
	
	private UserDao userDao;

	public User findByName(String name) {
		return userDao.findByName(name);
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
