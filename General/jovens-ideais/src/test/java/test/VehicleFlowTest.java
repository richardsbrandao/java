package test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.parking.model.dao.VehicleFlowDao;
import com.parking.model.entity.Customer;
import com.parking.model.entity.Flow;
import com.parking.model.service.VehicleFlowService;

@RunWith(JUnit4.class)
public class VehicleFlowTest {
	
	private VehicleFlowService vehicleFlowService;
	private VehicleFlowDao vehicleFlowDao;
	private Customer customer;
	private Flow flow;
	
	@Before
	public void setUp() {
		vehicleFlowService = new VehicleFlowService();
		vehicleFlowDao = createMock(VehicleFlowDao.class);
		customer = new Customer();
		flow = new Flow();
		vehicleFlowService.setVehicleFlowDao(vehicleFlowDao);
	}
	
	@Test
	public void hasActiveFlow() {
		expect(vehicleFlowDao.findActiveFlow(customer)).andReturn(flow);
		replay(vehicleFlowDao);
		
		assertTrue(vehicleFlowService.hasActiveFlow(customer));
	}
	
	@Test
	public void doesntHasActiveFlow() {
		expect(vehicleFlowDao.findActiveFlow(customer)).andReturn(null);
		replay(vehicleFlowDao);
		assertFalse(vehicleFlowService.hasActiveFlow(customer));
	}
	
}
