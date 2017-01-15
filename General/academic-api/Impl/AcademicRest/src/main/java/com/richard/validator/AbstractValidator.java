package com.richard.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.richard.json.RestrictionType;
import com.richard.json.ValidationError;

public class AbstractValidator {
	
	private List<ValidationError> reasons = new ArrayList<ValidationError>();
	private Boolean invalid;

	protected void clearState() {
		reasons.clear();
	}
	
	protected AbstractValidator setInvalid(Boolean state) {
		this.invalid = state;
		return this;
	}
	
	protected AbstractValidator isBlank(String string) {
		this.invalid = StringUtils.isBlank(string);
		return this;
	}
	
	public void addReason(String field, RestrictionType type, String placeholder) {
		if( invalid ) {
			reasons.add( new ValidationError(field, type, placeholder) );
		}
		invalid = false;
	}
	
	public List<ValidationError> getReasons() {
		return reasons;
	}
	
}
