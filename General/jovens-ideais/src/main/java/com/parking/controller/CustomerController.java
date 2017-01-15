package com.parking.controller;

import java.util.ArrayList;
import java.util.List;

import com.parking.model.entity.Customer;
import com.parking.model.service.CustomerService;

public class CustomerController extends Controller {
	
	private Customer customer;
	private CustomerService customerService;
	private List<String> customerType = new ArrayList<String>();

	public String load() {
		customerType = customerService.getCustomerTypes();
		return SUCCESS;
	}
	
	public String save() {
		if(!customer.isValidCustomer()) {
			message.addError("O cpf informado para este cliente não é válido.");
			return ERROR;
		}
		customerService.save(customer);
		message.addSuccess("Cliente cadastrado com sucesso.");
		return SUCCESS;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public List<String> getCustomerType() {
		return customerType;
	}

}
