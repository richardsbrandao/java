package com.richard.studies.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Being")
@DiscriminatorValue("H")
public class Human extends Being {

	private Integer paws;
	private Integer age;
	
	public Integer getPaws() {
		return paws;
	}
	public void setPaws(Integer paws) {
		this.paws = paws;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
}
