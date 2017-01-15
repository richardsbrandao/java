package com.estudos.ejb.api.resource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("payment-api")
public class PaymentApplication extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public PaymentApplication() {
		singletons.add(new PaymentResource());
		singletons.add(new SmsResource());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
}
