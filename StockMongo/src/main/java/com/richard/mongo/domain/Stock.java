package com.richard.mongo.domain;

public class Stock {
	
	private Integer subInventory;
	private Integer warehouse;
	private Integer type;
	private Integer available;
	private Integer purchased;
	
	public Stock(Integer subInventory, Integer warehouse, Integer type, Integer available, Integer purchased) {
		this.subInventory = subInventory;
		this.warehouse = warehouse;
		this.type = type;
		this.available = available;
		this.purchased = purchased;
	}
	public Integer getSubInventory() {
		return subInventory;
	}
	public void setSubInventory(Integer subInventory) {
		this.subInventory = subInventory;
	}
	public Integer getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Integer warehouse) {
		this.warehouse = warehouse;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getPurchased() {
		return purchased;
	}

	public void setPurchased(Integer purchased) {
		this.purchased = purchased;
	}
	@Override
	public String toString() {
		return "Stock [subInventory=" + subInventory + ", warehouse="
				+ warehouse + ", type=" + type + ", available=" + available
				+ ", purchased=" + purchased + "]";
	}
	
}
