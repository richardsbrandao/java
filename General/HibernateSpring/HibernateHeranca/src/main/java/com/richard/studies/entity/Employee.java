package com.richard.studies.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="employee")
@AttributeOverrides({
	@AttributeOverride(name="name", column=@Column(name="name")),
	@AttributeOverride(name="email", column=@Column(name="email"))
})
public class Employee extends Person {

	private Date joinnedDate;
	private String departament;
	
	public Date getJoinnedDate() {
		return joinnedDate;
	}
	public void setJoinnedDate(Date joinnedDate) {
		this.joinnedDate = joinnedDate;
	}
	public String getDepartament() {
		return departament;
	}
	public void setDepartament(String departament) {
		this.departament = departament;
	}
	
}
