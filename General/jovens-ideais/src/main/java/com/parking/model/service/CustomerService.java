package com.parking.model.service;

import java.util.ArrayList;
import java.util.List;

import com.parking.helper.CustomerType;
import com.parking.model.dao.CustomerDao;
import com.parking.model.entity.Customer;
import com.parking.model.service.CustomerService;

public class CustomerService {
	
	private CustomerDao customerDao;
	
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	public List<Customer> findAllCustomers() {
		return customerDao.findAllCustomers();
	}
	
	public List<String> getCustomerTypes() {
		List<String> customerType = new ArrayList<String>();
		for(CustomerType type : CustomerType.values()){
			customerType.add(type.name());
		}
		return customerType;
	}
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
}
