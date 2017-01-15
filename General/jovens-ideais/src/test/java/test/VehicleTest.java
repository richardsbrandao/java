package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.parking.model.entity.Customer;
import com.parking.model.entity.Vehicle;

@RunWith(JUnit4.class)
public class VehicleTest {

	@Test
	public void test_vehicle_creation() {
		Customer customer = new Customer();
		Vehicle vehicle = new Vehicle();
		vehicle.setId(1L);
		vehicle.setModel("BMW");
		vehicle.setPlate("ABC-1234");
		vehicle.setCustomer(customer);
		
		assertEquals(1L, vehicle.getId(), 0);
		assertEquals("BMW", vehicle.getModel());
		assertEquals("ABC-1234", vehicle.getPlate());
		assertEquals(customer, vehicle.getCustomer());
	}
}
