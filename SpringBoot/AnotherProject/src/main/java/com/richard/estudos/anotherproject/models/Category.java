package com.richard.estudos.anotherproject.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name="Categories")
@Getter @Setter @NoArgsConstructor
public class Category implements Serializable {

	@Id 
	@Column(columnDefinition="serial")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@OneToMany(mappedBy="category")
	private List<User> users;
	
	public Category(Long id) {
		this.setId(id);
	}
	
}
