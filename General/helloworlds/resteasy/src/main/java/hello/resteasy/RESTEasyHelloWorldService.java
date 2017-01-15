package hello.resteasy;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/RESTEasyHelloWorld")
public class RESTEasyHelloWorldService {

	@GET
	@Path("/{pathParameter}")
	@Produces("application/json")
	public Response responseMsg( @PathParam("pathParameter") String pathParameter,
			@DefaultValue("Nothing to say") @QueryParam("queryParameter") String queryParameter) {

		String response = "{'hello': '" + queryParameter+"'}";

		return Response.status(200).entity(response).build();
	}
	
	@POST
	@Path("/comparametros")
	@Produces("application/json")
	public Response response(String message) {
		HelloMessage response = new HelloMessage();
		response.setMessage(message);
		return Response.status(200).entity(response).build();
	}
	
	@POST
	@Path("/comentity")
	@Consumes("application/json")
	@Produces("application/json")
	public Response res(HelloMessage req) {
		
		HelloMessageRes response = new HelloMessageRes();
		response.setMessage(req.getMessage());
		
		return Response.status(200).entity(response).build();
	}
	
}