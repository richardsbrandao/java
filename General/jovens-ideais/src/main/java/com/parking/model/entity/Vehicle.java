package com.parking.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.parking.helper.Validation;

@Entity
public class Vehicle {
	
	@Id
	@SequenceGenerator(name="id", sequenceName="vehicle_id")
	@GeneratedValue(generator="id", strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne(targetEntity=Customer.class)
	private Customer customer;
	@OneToMany(mappedBy="vehicle")
	private List<Flow> flows;
	private String model;
	private String plate;

	public boolean isValidVehicle() {
		return plate != null;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		if(Validation.plate(plate)) {
			this.plate = plate;
		}
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Customer getCustomer() {
		return customer;
	}
	public List<Flow> getFlows() {
		return flows;
	}
	public void setFlows(List<Flow> flows) {
		this.flows = flows;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", plate=" + plate + "]";
	}
	
}
