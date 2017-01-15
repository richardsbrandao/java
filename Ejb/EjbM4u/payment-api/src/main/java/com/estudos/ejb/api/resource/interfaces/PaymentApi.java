package com.estudos.ejb.api.resource.interfaces;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/payment")
public interface PaymentApi {

	@GET
	@Produces({"text/plain"})
	public Response findAll();
	
}
