package com.parking.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AbstractDao<T> {

	private SessionFactory sessionFactory;
	protected Class clazz;

	protected AbstractDao(Class clazz) {		
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	protected T findById(Long primaryKey) {
		T object = (T) session().load(clazz, primaryKey);
		return object;
	}

	protected T saveEntity(T entity) {
		session().saveOrUpdate(entity);
		return entity;
	}

	protected void deleteEntity(T entity) {
		session().delete(session().merge(entity));
	}

	@SuppressWarnings("unchecked")
	protected List<T> listAll() {
		Criteria criteria = session().createCriteria(clazz);
		return criteria.list();
	}
	
	public Session session() {
		if ( sessionFactory == null ) {
			throw new IllegalArgumentException( "SessionFactory is null check if your dao is autowired by type." );
		}
		return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}