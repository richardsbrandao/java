package com.estudos.ejb.api.resource;

import javax.ws.rs.core.Response;

import com.estudos.ejb.api.resource.interfaces.PaymentApi;
import com.estudos.ejb.core.payment.PaymentFacedeLocal;
import com.estudos.ejb.core.util.BeanLocator;

public class PaymentResource implements PaymentApi {

	private PaymentFacedeLocal paymentFacede;

	public PaymentResource() {
		this.paymentFacede = (PaymentFacedeLocal) BeanLocator.lookupLocal(PaymentFacedeLocal.JNDI_NAME);
	}

	@Override
	public Response findAll() {
		paymentFacede.sayHello();
		return Response.status(200).entity( "Richard" ).build();
	}

}
