package com.richard.studies.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Silvester")
@PrimaryKeyJoinColumn(name="IDAnimal")
public class Silvester extends Animal {

	private String habitat;

	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}
	
}
