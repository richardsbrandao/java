package com.estudos.places.domain;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

public class Place {

	@Id
	private String id;
	
	private String name;
	private String city;
	private String country;
	
	@GeoSpatialIndexed
	private double[] position;
	
	public Place() { }
	
	public Place(String name, String city, String country, double[] position) {
		this.name = name;
		this.city = city;
		this.country = country;
		this.position = position;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double[] getPosition() {
		return position;
	}
	public void setPosition(double[] position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", name=" + name + ", city=" + city
				+ ", country=" + country + ", position="
				+ Arrays.toString(position) + "]";
	}
	
}
