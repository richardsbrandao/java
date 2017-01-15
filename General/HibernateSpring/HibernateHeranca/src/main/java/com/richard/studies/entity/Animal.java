package com.richard.studies.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="Animal")
@Inheritance(strategy=InheritanceType.JOINED)
public class Animal {
	
	@Id
	@Column(name="IDAnimal")
	private Long id;
	private String race;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	
}
