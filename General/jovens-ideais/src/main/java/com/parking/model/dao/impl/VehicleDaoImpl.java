package com.parking.model.dao.impl;

import java.util.List;

import com.parking.model.dao.AbstractDao;
import com.parking.model.dao.VehicleDao;
import com.parking.model.entity.Customer;
import com.parking.model.entity.Vehicle;

public class VehicleDaoImpl extends AbstractDao<Vehicle> implements VehicleDao {

	protected VehicleDaoImpl() {
		super(Vehicle.class);
	}

	@Override
	public void save(Vehicle Vehicle) {
		saveEntity(Vehicle);
	}

	@Override
	public List<Vehicle> findAll() {
		return listAll();
	}

	@Override
	public Customer loadCustomerByVehicle(Vehicle vehicle) {
		System.out.println(findById(vehicle.getId()).getCustomer());
		return null;
	}

	@Override
	public Vehicle loadVehicleById(Long primaryKey) {
		return findById(primaryKey);
	}
	
}
