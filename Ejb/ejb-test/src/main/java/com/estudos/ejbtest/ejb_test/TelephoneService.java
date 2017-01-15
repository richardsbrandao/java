package com.estudos.ejbtest.ejb_test;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TelephoneService implements ITelephoneService {

	@PersistenceContext(unitName="tutorial")
	private EntityManager em;
	
	private TelephoneDaoImpl dao;
	
	@PostConstruct
	public void postConstruct() {
		dao = new TelephoneDaoImpl(em);
	}
	
	public void save() {
		dao.save("String tel");
	}
	
}
