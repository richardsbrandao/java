package com.estudos.ejb.core.sms;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.estudos.ejb.core.domain.Sms;

@Stateless
@LocalBean
public class SmsDao {

	@PersistenceContext
	private EntityManager em;
	
	public void save(Sms sms) {
		em.persist(sms);
	}

}
