package com.richard.studi.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.richard.studi.dominio.Cliente;

public class CriteriDaoAbstratto<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	protected T save(T entity) {
		session().saveOrUpdate(entity);
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	protected T findById(Long id, Class<? extends Object> persistenceClass) {
		return (T) session().get(persistenceClass, id);
	}

	protected void delete(T entity) {
		session().delete( session().merge(entity) );
	}
	
	protected Session session() {
		Session session = sessionFactory.getCurrentSession();
		if( session == null ) {
			throw new SessionException("No Hibernate Session at this moment");
		}
		return session;
	}

	@SuppressWarnings("unchecked")
	protected List<T> findAll(Class<? extends Object> persistenceClass) {
		return session().createCriteria( persistenceClass ).list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findBetween(Class<? extends Object> persistenceClass, String fieldName, Date lowerObject, Date highestObject) {
		return (List<T>) session().createCriteria( persistenceClass ).
				add( Restrictions.ge(fieldName, lowerObject) ).
				add( Restrictions.le(fieldName, highestObject) ).
				list();
	}

	@SuppressWarnings("unchecked")
	protected List<Cliente> findBy(Class<? extends Object> persistenceClass, List<Criterion> restrictions) {
		Criteria criteria = session().createCriteria(persistenceClass);
		for( Criterion restriction : restrictions ) {
			criteria.add( restriction );
		}
		return (List<Cliente>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> findAllByExample(T example) {
		return (List<T>) session().createCriteria(example.getClass()).add( Example.create(example) ).list();
	}

	@SuppressWarnings("unchecked")
	protected T findOneByExample(T example) {
		return (T) session().createCriteria(example.getClass()).add( Example.create(example) ).uniqueResult();
	}

	protected Integer count(Class<? extends Object> persistenceClass, List<Criterion> restrictions) {
		Criteria criteria = session().createCriteria(persistenceClass);
		for( Criterion restriction : restrictions ) {
			criteria.add( restriction );
		}
		return criteria.list().size();
	}

}
