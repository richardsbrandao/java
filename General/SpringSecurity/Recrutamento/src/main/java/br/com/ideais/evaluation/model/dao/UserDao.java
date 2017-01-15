package br.com.ideais.evaluation.model.dao;

import br.com.ideais.evaluation.model.entity.User;

public interface UserDao {

	public User findByName(String name);

}
