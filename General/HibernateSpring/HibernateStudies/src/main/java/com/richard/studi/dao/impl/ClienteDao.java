package com.richard.studi.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.richard.studi.dominio.Cliente;

public class ClienteDao extends CriteriDaoAbstratto<Cliente> {

	public Cliente save(Cliente customer) {
		return super.save(customer);
	}

	public Cliente findById(Long id) {
		return super.findById(id, Cliente.class);
	}

	public void delete(Cliente customer) {
		super.delete(customer);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public List<Cliente> trovareTutto() {
		return super.findAll(Cliente.class);
	}

	public List<Cliente> trovareTra(Date startDate, Date endDate) {
		return super.findBetween(Cliente.class, "nascita", startDate, endDate);
	}

	public List<Cliente> trovareDaEmailEPassword(String email, String password) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.eq("email", email) );
		restrictions.add( Restrictions.eq("password", password) );
		return super.findBy( Cliente.class, restrictions );
	}

	public List<Cliente> findByCustomer(Cliente customer) {
		return super.findAllByExample(customer);
	}

	public Cliente trovareUnoDaCliente(Cliente customer) {
		return super.findOneByExample(customer);
	}

	public Integer contareDaEmail(String email) {
		List<Criterion> restrictions = new ArrayList<Criterion>();
		restrictions.add( Restrictions.eq("email", email) );
		return super.count(Cliente.class, restrictions);
	}
	
	public Integer contare() {
		return super.count(Cliente.class, new ArrayList<Criterion>());
	}

}
