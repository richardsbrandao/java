package com.parking.controller;

import com.parking.model.entity.Parking;
import com.parking.model.service.ParkingService;

public class ParkingController extends Controller {
	
	private Parking parking;
	private ParkingService parkingService;
	
	public String load() {
		return SUCCESS;
	}
	
	public String save(){
		if(!parking.isValidParking()) {
			message.addError("Dados do estacionamento não foram enviados corretamente.");
			return ERROR;
		}
		parkingService.save(parking);
		message.addSuccess("Dados do estacionamento salvo com sucesso.");
		return SUCCESS;
	}

	public Parking getParking() {
		return parking;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
	}

	public void setParkingService(ParkingService parkingService) {
		this.parkingService = parkingService;
	}
	
}
