package com.richard.studi.dao.impl;

import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HQLDaoAbstratto<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	protected List<T> trovareTutto(Class<? extends Object> persistenzaClasse) {
		return session().createQuery("from " + persistenzaClasse.getName()).list(); 
	}
	
	@SuppressWarnings("unchecked")
	protected T trovareDaId(Class<? extends Object> persistenzaClasse, Long id) {
		Query query = session().createQuery("from "+persistenzaClasse.getName()+" where id = :id");
		query.setParameter("id", id);
		return (T) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> trovareDa(Class<? extends Object> persistenzaClasse, Properties properties) {
		StringBuilder sql = new StringBuilder("from "+persistenzaClasse.getName()+" where ");
		
		for(Object property : properties.keySet()) {
			sql.append( property + " = " + ":" + property );
		}
		
		Query query = session().createQuery( sql.toString() );
		
		for(Entry<Object, Object> KeySet : properties.entrySet() ) {
			query.setParameter((String) KeySet.getKey(), KeySet.getValue());
		}
		
		return query.list();
	}
	
	protected Session session() {
		Session session = sessionFactory.getCurrentSession();
		if( session == null ) {
			throw new SessionException("No Hibernate Session at this moment");
		}
		return session;
	}

	public Long contare(Class<? extends Object> clazz) {
		return (Long) session().createQuery( "SELECT count(id) from " + clazz.getSimpleName() + " " + clazz.getSimpleName().toLowerCase() ).uniqueResult();
	}
	
}
