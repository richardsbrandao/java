package com.estudos.ejbtest.ejb_test;

import javax.persistence.EntityManager;

public class TelephoneDaoImpl {

	private EntityManager em;
	
	public TelephoneDaoImpl(EntityManager em) {
		this.em = em;
	}
	
	public void save(String tel) {
		System.out.println(em + " <-> " + tel);
	}

	
}
