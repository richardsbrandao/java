package com.richard.mongo.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Inventory {

	@Id
	private String id;
	private Integer sku;
	private List<Stock> stocks;
	
	public Inventory(Integer sku, List<Stock> stocks) {
		this.sku = sku;
		this.stocks = stocks;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSku() {
		return sku;
	}

	public void setSku(Integer sku) {
		this.sku = sku;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", sku=" + sku + ", stocks=" + stocks + "]";
	}
	
}
