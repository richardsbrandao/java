package com.parking.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Parking {
	
	@Id
	@SequenceGenerator(name="parking_id", sequenceName="parking_id")
	@GeneratedValue(generator="parking_id", strategy=GenerationType.AUTO)
	private Long id;
	private Integer totalVacancyNumber;

	public boolean isValidParking() {
		return totalVacancyNumber != null;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotalVacancyNumber() {
		return totalVacancyNumber;
	}

	public void setTotalVacancyNumber(Integer totalVacancyNumber) {
			this.totalVacancyNumber = totalVacancyNumber;
	}
	
}
