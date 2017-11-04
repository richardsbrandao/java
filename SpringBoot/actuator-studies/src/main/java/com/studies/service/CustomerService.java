package com.studies.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.studies.domain.Customer;

@Service
public class CustomerService {

	private static Log LOG = LogFactory.getLog(CustomerService.class);
	private static List<Customer> repository;
	static {
		repository = Arrays.asList(new Customer("1", "C1", "11111"), new Customer("2", "C2", "22222"));
	}
	
	public List<Customer> findAll() {
		cleanHateoas();
		LOG.info("Going to fake repo and returning something!");
		return repository;
	}

	private void cleanHateoas() {
		repository.forEach((customer) -> customer.removeLinks());
	}

	public Customer findById(String id) {
		cleanHateoas();
		return repository.get(0);
	}
	
}
