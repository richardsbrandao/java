package com.richard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.richard.utils.UpdateUtils;

@Entity
public class Institution {

	@Id
	@SequenceGenerator(name="institution_id", sequenceName="institution_id")
	@GeneratedValue(generator="institution_id", strategy=GenerationType.AUTO)
	@Column(name="IDInstituicao")
	private Long id;
	@Column(name="StNome", nullable=false)
	private String name;
	
	public void mergeProperties(Institution source) {
		UpdateUtils.mergeProperties(Institution.class, this, source, new String[] { "name" });
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
