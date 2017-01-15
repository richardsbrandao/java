package com.estudos.ejb.core.sms;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.jboss.annotation.ejb.LocalBinding;

import com.estudos.ejb.core.domain.Sms;
import com.estudos.ejb.core.erros.ValidationException;

@Stateless
@LocalBinding(jndiBinding=SmsFacedeLocal.JNDI_NAME)
public class SmsFacede implements SmsFacedeLocal {

	@EJB
	private SmsValidator validator;
	@EJB
	private SmsService service;
	@EJB
	private SmsDao dao;
	
	public Sms send(Sms sms) throws ValidationException {
		validator.validate(sms);
		service.send(sms);
		dao.save(sms);
		return sms;
	}
	
}
