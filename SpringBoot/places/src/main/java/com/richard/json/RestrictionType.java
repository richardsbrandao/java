package com.richard.json;

public enum RestrictionType {
	
	REQUIRED("required"), 
	NUMERIC("numeric"), 
	BUSINESS("business"), 
	INVALID("invalid"), 
	MAX_LENGTH("maxLength"), 
	MIN_LENGTH("minLength"), 
	EXACT_LENGTH("exactLength"),
	UNIQUE("unique"),
	GREATER_THAN("greaterThan"),
	LESS_THAN("lessThan");
	
	String type;
	
	RestrictionType(String type) {
		this.type = type;
	}
	
}
