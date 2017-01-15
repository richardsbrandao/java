package com.estudos.ejb.api.resource;

import javax.ws.rs.core.Response;

import com.estudos.ejb.api.resource.interfaces.SmsApi;
import com.estudos.ejb.api.response.ErrorResponse;
import com.estudos.ejb.core.domain.Sms;
import com.estudos.ejb.core.erros.ValidationException;
import com.estudos.ejb.core.sms.SmsFacedeLocal;
import com.estudos.ejb.core.util.BeanLocator;

public class SmsResource implements SmsApi {

	private SmsFacedeLocal smsFacede;

	public SmsResource() {
		this.smsFacede = (SmsFacedeLocal) BeanLocator.lookupLocal(SmsFacedeLocal.JNDI_NAME);
	}

	@Override
	public Response send(Sms sms) {
		try {
			return Response.status(201).entity( smsFacede.send(sms) ).build();
		} catch (ValidationException e) {
			ErrorResponse errorResponse = new ErrorResponse("422", e.getMessage(), e.getErrors());
			return Response.status(422).entity(errorResponse).build();
		} catch (Exception e) {
			return Response.status(500).entity(new ErrorResponse()).build();
		}
	}
	
	

}
