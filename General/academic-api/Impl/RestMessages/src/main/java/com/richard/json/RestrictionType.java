package com.richard.json;

public enum RestrictionType {
	
	REQUIRED("required"), 
	BUSINESS("business"), 
	INVALID("invalid"), 
	MAX_LENGTH("maxLength"), 
	MIN_LENGTH("minLength"), 
	UNIQUE("unique");
	
	String type;
	
	RestrictionType(String type) {
		this.type = type;
	}
	
}
