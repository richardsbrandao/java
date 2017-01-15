package test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.parking.model.dao.ParkingDao;
import com.parking.model.dao.VehicleFlowDao;
import com.parking.model.service.ParkingService;

@RunWith(JUnit4.class)
public class ParkingServiceTest {
	
	private ParkingDao parkingDao;
	private VehicleFlowDao vehicleFlowDao;

	@Before
	public void setUp() {
		parkingDao = createMock(ParkingDao.class);
		vehicleFlowDao = createMock(VehicleFlowDao.class);
	}

	@Test
	public void test_has_vacancy() {
		expect(parkingDao.getTotalVacancy()).andReturn(45);
		replay(parkingDao);
		
		expect(vehicleFlowDao.getTotalVehicleFlow()).andReturn(40);
		replay(vehicleFlowDao);
		
		ParkingService parkingService = new ParkingService();
		
		parkingService.setVehicleFlowDao(vehicleFlowDao);
		parkingService.setParkingDao(parkingDao);
		
		assertTrue(parkingService.hasVacancy());
	}
	
	public void test_doesnt_have_vacancy() {
		expect(parkingDao.getTotalVacancy()).andReturn(40);
		replay(parkingDao);
		
		expect(vehicleFlowDao.getTotalVehicleFlow()).andReturn(40);
		replay(vehicleFlowDao);

		ParkingService parkingService = new ParkingService();
		
		parkingService.setVehicleFlowDao(vehicleFlowDao);
		parkingService.setParkingDao(parkingDao);
		
		assertTrue(!parkingService.hasVacancy());
	}

	@Test(expected=NullPointerException.class)
	public void test_with_daos_returning_null() {
		expect(parkingDao.getTotalVacancy()).andReturn(null);
		replay(parkingDao);
		
		expect(vehicleFlowDao.getTotalVehicleFlow()).andReturn(null);
		replay(vehicleFlowDao);

		ParkingService parkingService = new ParkingService();
		
		parkingService.setVehicleFlowDao(vehicleFlowDao);
		parkingService.setParkingDao(parkingDao);
	}
}
