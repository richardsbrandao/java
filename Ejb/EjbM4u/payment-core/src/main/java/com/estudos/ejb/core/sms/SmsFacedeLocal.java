package com.estudos.ejb.core.sms;

import javax.ejb.Local;

import com.estudos.ejb.core.domain.Sms;
import com.estudos.ejb.core.erros.ValidationException;

@Local
public interface SmsFacedeLocal {

	 public static final String JNDI_NAME = "java:app/payment-api/SmsFacede";

	 Sms send(Sms sms) throws ValidationException;
	 
}
