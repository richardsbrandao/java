package com.parking.model.dao.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.parking.model.dao.AbstractDao;
import com.parking.model.dao.CustomerDao;
import com.parking.model.entity.Customer;

@Transactional
public class CustomerDaoImpl extends AbstractDao<Customer> implements CustomerDao {
	
	public CustomerDaoImpl() {
		super(Customer.class);
	}

	public void save(Customer customer) {
		saveEntity(customer);
	}

	@Override
	public List<Customer> findAllCustomers() {
		return listAll();
	}
	
}
