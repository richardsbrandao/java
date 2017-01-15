package com.richard.studies.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="owner")
@AttributeOverrides({
	@AttributeOverride(name="name", column=@Column(name="name")),
	@AttributeOverride(name="email", column=@Column(name="email"))
})
public class Owner extends Person {
	
	private Integer percentOwner;
	private String role;

	public Integer getPercentOwner() {
		return percentOwner;
	}
	public void setPercentOwner(Integer percentOwner) {
		this.percentOwner = percentOwner;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}

