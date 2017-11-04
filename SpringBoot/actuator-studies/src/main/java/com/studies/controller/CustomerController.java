package com.studies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studies.domain.Customer;
import com.studies.service.CustomerService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value="/customers")
	public ResponseEntity<List<Customer>> findAll() {
		List<Customer> customers = customerService.findAll();
		for(Customer customer : customers) {
			customer.add(linkTo(methodOn(CustomerController.class).findAll()).withSelfRel());
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/customers/{id}")
	public ResponseEntity<Customer> findById(@PathVariable("id") String id) {
		Customer customer = customerService.findById(id);
		customer.add(linkTo(methodOn(CustomerController.class).findById(id)).withSelfRel());
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
}
