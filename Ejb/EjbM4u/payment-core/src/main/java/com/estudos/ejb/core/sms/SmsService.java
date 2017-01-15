package com.estudos.ejb.core.sms;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.estudos.ejb.core.domain.Sms;

@Stateless
@LocalBean
public class SmsService {

	@SuppressWarnings("unused")
	private final String url = "http://localhost:8085/sms-api/send";
	
	public void send(Sms sms) {
		if( sms.getFrom().equals("975121212") ) {
			throw new RuntimeException("Celular não existe");
		}
		
		if( sms.getFrom().equals("975131313") ) {
			throw new RuntimeException("Celular sem credito");
		}
		
		System.out.println("Requisição enviada!");
	}

}
