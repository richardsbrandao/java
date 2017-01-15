package com.parking.controller;

import java.util.List;

import com.parking.model.entity.Customer;
import com.parking.model.entity.Vehicle;
import com.parking.model.service.CustomerService;
import com.parking.model.service.VehicleService;

public class VehicleController extends Controller {
	
	private VehicleService vehicleService;
	private CustomerService customerService;
	private Vehicle vehicle;
	private List<Customer> customers;
	
	public String load() {
		customers = customerService.findAllCustomers();
		return SUCCESS;
	}
	
	public String save(){
		if( !vehicle.isValidVehicle() ) {
			message.addError("A placa não é válida.");
			return ERROR;
		}
		message.addSuccess("Dados do veículo cadastrado com sucesso.");
		vehicleService.save(vehicle);
		return SUCCESS;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
}
