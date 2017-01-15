package com.richard.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.richard.domain.Institution;

public abstract class AbstractDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	public T saveEntity(T entity) {
		session().saveOrUpdate( entity );
		return entity;
	}

	@SuppressWarnings("unchecked")
	public T findById(Long id, Class<Institution> clazz) {
		return (T) session().get(clazz, id);
	}
	
	protected Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Institution> findAll(Class<Institution> clazz) {
		return session().createCriteria( clazz ).list();
	}

	public void delete(T entity) {
		session().delete( session().merge(entity) );
	}
	
}
