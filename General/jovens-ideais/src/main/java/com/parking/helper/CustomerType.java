package com.parking.helper;

public enum CustomerType {
	
	MENSALISTA("MENSALISTA"), ROTATIVO("ROTATIVO");
	
	private String type;

	CustomerType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
}