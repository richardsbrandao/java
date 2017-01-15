package com.estudos.boot.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.estudos.boot.domain.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
	
	Customer findByEmail(String email);
	
	Customer findByName(String name);
	
}
