package com.parking.model.service;

import java.util.List;

import com.parking.model.dao.VehicleDao;
import com.parking.model.entity.Customer;
import com.parking.model.entity.Vehicle;

public class VehicleService {
	
	private VehicleDao vehicleDao;

	public void setVehicleDao(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	
	public void save(Vehicle vehicle){
		vehicleDao.save(vehicle);
	}

	public List<Vehicle> findAll() {
		return vehicleDao.findAll();
	}

	public Customer loadCustomerByVehicle(Vehicle vehicle) {
		return vehicleDao.loadCustomerByVehicle(vehicle);
	}

	public Vehicle loadVehicleById(Vehicle vehicle) {
		return vehicleDao.loadVehicleById(vehicle.getId());
	}
	
}
