package com.parking.model.dao.impl;

import com.parking.model.dao.AbstractDao;
import com.parking.model.dao.ParkingDao;
import com.parking.model.entity.Parking;

public class ParkingDaoImpl extends AbstractDao<Parking> implements ParkingDao {

	public ParkingDaoImpl() {
		super(Parking.class);
	}

	@Override
	public void save(Parking parking) {
		saveEntity(parking);
	}

	@Override
	public Integer getTotalVacancy() {
		return findById(1L).getTotalVacancyNumber();
	}

	
}
