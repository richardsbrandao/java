package com.parking.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Flow {
	
	@Id
	@SequenceGenerator(name = "flow_id", sequenceName = "flow_id")
	@GeneratedValue(generator = "flow_id", strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(targetEntity=Vehicle.class)
	private Vehicle vehicle;
	private Date entry;
	private Date departure;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Date getEntry() {
		return entry;
	}
	public void setEntry(Date date) {
		this.entry = date;
	}
	public Date getDeparture() {
		return departure;
	}
	public void setDeparture(Date departure) {
		this.departure = departure;
	}
	public Customer getCustomer() {
		return vehicle.getCustomer();
	}
	
}
