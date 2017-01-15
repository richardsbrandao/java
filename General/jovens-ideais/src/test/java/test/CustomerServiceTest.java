package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.parking.model.service.CustomerService;

@RunWith(JUnit4.class)
public class CustomerServiceTest {
	
	@Test
	public void test_customer_types_enums() {
		CustomerService customerService = new CustomerService();
		List<String> customerTypes = new ArrayList<String>();
		customerTypes.add("MENSALISTA");
		customerTypes.add("ROTATIVO");
		assertEquals(customerTypes, customerService.getCustomerTypes());
	}
	
}

