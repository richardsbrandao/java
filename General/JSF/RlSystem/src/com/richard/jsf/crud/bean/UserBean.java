package com.richard.jsf.crud.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import com.richard.jsf.crud.dao.UserDao;
import com.richard.jsf.crud.domain.User;

@ManagedBean(name="userController")
public class UserBean {

	private UserDao userDao = new UserDao();
	
	private User user = new User();

	private DataModel<User> users;
	
	public String add() {
		userDao.add( user );
		return "list";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DataModel<User> getUsers() {
		users = new ListDataModel<User>( userDao.findAll() );
		return users;
	}

	public void setUsers(DataModel<User> users) {
		this.users = users;
	}
	
}
