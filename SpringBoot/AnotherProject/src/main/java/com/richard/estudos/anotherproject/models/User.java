package com.richard.estudos.anotherproject.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name="Users")
@Getter @Setter
public class User implements Serializable {

	@Id 
	@Column(columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDate bornDate;
	
	@ManyToOne(targetEntity=Category.class)
	@JoinColumn(name="category_id", referencedColumnName="id", nullable=false)
	private Category category;
	
}
