package com.estudos.ejbtest.ejb_test;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SmsDaoImpl implements SmsDao {

	@PersistenceContext(unitName="tutorial")
	private EntityManager em;
	
	@TransactionAttribute(value=TransactionAttributeType.REQUIRED)
	public void save(Sms sms) {
		em.persist(sms);
	}

}
