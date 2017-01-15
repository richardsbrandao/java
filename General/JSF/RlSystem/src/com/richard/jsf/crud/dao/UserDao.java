package com.richard.jsf.crud.dao;

import java.util.List;

import com.richard.jsf.crud.FakeDb;
import com.richard.jsf.crud.domain.User;

public class UserDao {

	public void add(User user) {
		FakeDb.users.add(user);
	}

	public List<User> findAll() {
		return FakeDb.users;
	}

}
