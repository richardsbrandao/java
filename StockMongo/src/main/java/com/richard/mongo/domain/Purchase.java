package com.richard.mongo.domain;

public class Purchase {

	private Integer sku;
	private Integer subInventory;
	private Integer type;
	private Integer quantity;

	public Purchase(Integer sku, Integer subInventory, Integer type, Integer quantity) {
		this.sku = sku;
		this.subInventory = subInventory;
		this.type = type;
		this.quantity = quantity;
	}

	public Integer getSku() {
		return sku;
	}

	public Integer getSubInventory() {
		return subInventory;
	}

	public Integer getType() {
		return type;
	}

	public Integer getQuantity() {
		return quantity;
	}
	
}
