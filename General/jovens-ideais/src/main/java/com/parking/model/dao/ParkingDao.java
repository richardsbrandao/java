package com.parking.model.dao;

import com.parking.model.entity.Parking;

public interface ParkingDao {

	public Integer getTotalVacancy();
	public void save(Parking parking);

}
