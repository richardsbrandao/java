package com.parking.model.dao;

import com.parking.model.entity.Customer;
import com.parking.model.entity.Flow;

public interface VehicleFlowDao {
	public Integer getTotalVehicleFlow();
	public void allocate(Flow flow);
	public Flow findById(Flow flow);
	public Flow findActiveFlow(Customer customer);
}
