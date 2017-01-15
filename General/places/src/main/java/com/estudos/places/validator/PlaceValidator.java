package com.estudos.places.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.richard.errors.BadRequestException;
import com.richard.json.RestrictionType;
import com.richard.json.ValidationError;

@Component
public class PlaceValidator {

	public void validate(String lat, String lon) throws BadRequestException {
		List<ValidationError> errors = new ArrayList<ValidationError>();
		
		if( ! NumberUtils.isNumber(lat) ) {
			errors.add(new ValidationError("lat", RestrictionType.INVALID, "latitude invalida"));
		}
		
		if( ! NumberUtils.isNumber(lon) ) {
			errors.add(new ValidationError("lon", RestrictionType.INVALID, "longitude invalido"));
		}
		
		if( !errors.isEmpty() ) {
			throw new BadRequestException("Erro ao processar entidade", errors);
		}
	}

}
