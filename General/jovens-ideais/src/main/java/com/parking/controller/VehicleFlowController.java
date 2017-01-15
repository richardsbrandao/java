package com.parking.controller;

import java.util.Date;
import java.util.List;

import com.parking.model.entity.Flow;
import com.parking.model.entity.Vehicle;
import com.parking.model.service.ParkingService;
import com.parking.model.service.VehicleFlowService;
import com.parking.model.service.VehicleService;

public class VehicleFlowController extends Controller {
	
	private VehicleFlowService vehicleFlowService;
	private ParkingService parkingService;
	private VehicleService vehicleService;
	private Flow flow;
	private List<Vehicle> vehicles;
	
	public String load() {
		vehicles = vehicleService.findAll();
		return SUCCESS;
	}
	
	public String allocate() {
		flow.setVehicle(vehicleService.loadVehicleById(flow.getVehicle()));
		if(allocateIsNotValid()){
			return ERROR;
		}
		flow.setEntry(new Date());
		vehicleFlowService.allocateVacancy(flow);
		message.addSuccess("Vaga preenchida!");
		return SUCCESS;
	}

	private boolean allocateIsNotValid() {
		if(!parkingService.hasVacancy()) {
			message.addError("O estacionamento está lotado!");
			return true;
		}
		if(!flow.getVehicle().getCustomer().isSubscriber()) {
			message.addError("Apenas clientes mensalistas podem entrar no estacionamento no momento!");
			return true;
		}
		if( vehicleFlowService.hasActiveFlow(flow.getCustomer()) ) {
			message.addError("Cliente já possui mais de um veículo no estacionamento.");
			return true;
		}
		return false;
	}

	public Flow getFlow() {
		return flow;
	}

	public void setFlow(Flow flow) {
		this.flow = flow;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public void setVehicleFlowService(VehicleFlowService vehicleFlowService) {
		this.vehicleFlowService = vehicleFlowService;
	}

	public void setParkingService(ParkingService parkingService) {
		this.parkingService = parkingService;
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	
}