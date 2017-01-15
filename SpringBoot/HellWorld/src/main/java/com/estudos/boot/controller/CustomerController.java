package com.estudos.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.boot.dao.CustomerRepository;
import com.estudos.boot.domain.Customer;

@RestController
@RequestMapping(value="/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> create() throws Exception {
		Customer customer = new Customer("Richard", "richardsbrandao@gmail.com");
		customerRepository.save(customer);
		return new ResponseEntity<String>("Feito", HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Customer>> findAll() throws Exception {
		
		List<Customer> findAll = customerRepository.findAll();
		
		System.out.println(findAll);
		return new ResponseEntity<List<Customer>>(findAll, HttpStatus.CREATED);
	}
	
}
