package com.estudos.ejb.core.sms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.estudos.ejb.core.domain.Sms;
import com.estudos.ejb.core.erros.ValidationException;

@Stateless
@LocalBean
public class SmsValidator {

	public void validate(Sms sms) throws ValidationException {
		if( sms == null ) {
			throw new ValidationException("Erro ao aplicar validacao de SMS", Arrays.asList("Sms inválido!"));
		}

		List<String> errors = new ArrayList<String>();
		
		if( isBlank( sms.getFrom() ) ) {
			errors.add("Campo 'From' é obrigatorio!");
		}
		
		if( isBlank( sms.getTo() ) ) {
			errors.add("Campo 'To' é obrigatorio!");
		}
		
		if( isBlank( sms.getText() ) ) {
			errors.add("Campo 'Text' é obrigatorio!");
		}
		
		if( ! errors.isEmpty() ) {
			throw new ValidationException("Erro ao aplicar validacao de SMS", errors);
		}
		
	}

	private boolean isBlank(String str) {
		return str == null || "".trim().equals(str);
	}

}
