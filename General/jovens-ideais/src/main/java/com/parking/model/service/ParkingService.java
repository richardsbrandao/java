package com.parking.model.service;

import com.parking.model.dao.ParkingDao;
import com.parking.model.dao.VehicleFlowDao;
import com.parking.model.entity.Parking;

public class ParkingService {
	private ParkingDao parkingDao;
	private VehicleFlowDao vehicleFlowDao;

	public void save(Parking parking) {
		parkingDao.save(parking);
	}

	public boolean hasVacancy() {
		return parkingDao.getTotalVacancy() >= vehicleFlowDao.getTotalVehicleFlow();
	}

	public void setParkingDao(ParkingDao parkingDao) {
		this.parkingDao = parkingDao;
	}
	
	public void setVehicleFlowDao(VehicleFlowDao vehicleFlowDao) {
		this.vehicleFlowDao = vehicleFlowDao;
	}
}
