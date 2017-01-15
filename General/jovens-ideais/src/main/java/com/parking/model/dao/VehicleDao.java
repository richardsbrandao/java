package com.parking.model.dao;

import java.util.List;

import com.parking.model.entity.Customer;
import com.parking.model.entity.Vehicle;

public interface VehicleDao {
	public void save(Vehicle vehicle);
	public List<Vehicle> findAll();
	public Customer loadCustomerByVehicle(Vehicle vehicle);
	public Vehicle loadVehicleById(Long primaryKey);
}
