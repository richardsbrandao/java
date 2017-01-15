package com.estudos.ejb.core.payment;

import javax.ejb.Stateless;

import org.jboss.annotation.ejb.LocalBinding;

@Stateless
@LocalBinding(jndiBinding=PaymentFacedeLocal.JNDI_NAME)
public class PaymentFacede implements PaymentFacedeLocal {

	public void sayHello() {
		System.out.println("Hello World");
	}
	
}
