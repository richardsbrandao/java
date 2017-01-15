package helloworld.ws;

import helloworld.dao.CustomerDao;
import helloworld.domain.Customer;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/customers")
@Stateless
@LocalBean
public class CustomerController {

	@EJB
	private CustomerDao customerDao;

	@GET
	@Path("/{id}")
	@Produces({"application/json","application/xml"})
	public Customer getCustomer(@PathParam("id") int id) {
		return customerDao.getCustomer(id);
	}

	@POST
	public void addCustomers(List<Customer> customers) {
		customerDao.addCustomers(customers);
	}

}
