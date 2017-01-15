package com.parking.model.service;

import com.parking.model.dao.VehicleFlowDao;
import com.parking.model.entity.Customer;
import com.parking.model.entity.Flow;

public class VehicleFlowService {

	private VehicleFlowDao vehicleFlowDao;

	public void setVehicleFlowDao(VehicleFlowDao vehicleFlowDao) {
		this.vehicleFlowDao = vehicleFlowDao;
	}
	
	public void allocateVacancy(Flow flow) {
		vehicleFlowDao.allocate(flow);
	}

	public Boolean hasActiveFlow(Customer customer) {
		return vehicleFlowDao.findActiveFlow(customer) != null;
	}

	
}
