package com.estudos.ejb.payment_core.sms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.estudos.ejb.core.domain.Sms;
import com.estudos.ejb.core.erros.ValidationException;
import com.estudos.ejb.core.sms.SmsValidator;

@RunWith(JUnit4.class)
public class SmsValidatorTest {

	private SmsValidator validator = new SmsValidator();
	
	@Test
	public void validate_sms() throws ValidationException {
		Sms sms = getValidSms();
		validator.validate(sms);
	}
	
	@Test
	public void invalid_sms() {
		try {
			validator.validate(new Sms());
			fail("Não deveria ter validado");
		} catch(ValidationException e) {
			assertEquals( 3, e.getErrors().size() );
		}
	}

	private Sms getValidSms() {
		Sms sms = new Sms();
		sms.setFrom("21975599630");
		sms.setTo("21975496140");
		sms.setText("Oi meu Amor! Eu te amo!");
		return sms;
	}
	
}
