package com.richard.validator;

import com.richard.domain.Institution;
import com.richard.json.RestrictionType;

public class InstitutionValidator extends AbstractValidator {

	public Boolean isValid(Institution institution) {
		clearState();
		
		if( institution == null ) {
			setInvalid( true ).addReason("institution", RestrictionType.INVALID, "institution.error.domain.null");
			return false;
		}
		
		isBlank( institution.getName() ).addReason("name", RestrictionType.REQUIRED, "institution.error.name.blank");
		
		return getReasons().isEmpty();
	}

}
