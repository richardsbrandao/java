package com.parking.model.dao;

import java.util.List;

import com.parking.model.entity.Customer;

public interface CustomerDao {

	public void save(Customer customer);
	public List<Customer> findAllCustomers();

}
