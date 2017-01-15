package com.estudos.ejb.core.payment;

import javax.ejb.Local;

@Local
public interface PaymentFacedeLocal {

	 public static final String JNDI_NAME = "java:app/payment-api/PaymentFacede";

	 void sayHello();
	 
}
