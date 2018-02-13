package algorithm.cases.drones;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import algorithm.cases.drones.Drone;
import algorithm.cases.drones.DronesSelector;

public class DronesTest {

	@Test
	public void get_hights_flight_range_without_maintenance_ids() {
		Drone[] drones = new Drone[]{new Drone(1, 11), new Drone(2, 5), new Drone(3, 16), new Drone(4, 12)};
		Integer[] maintenanceIds = new Integer[] {}; 
		
		DronesSelector selector = new DronesSelector(drones, maintenanceIds);
		
		Integer[] selectAvailableDrones = selector.selectAvailableDrones(3);
		
		assertEquals(3, selectAvailableDrones.length);
		assertEquals(new Integer(3), selectAvailableDrones[0]);
		assertEquals(new Integer(4), selectAvailableDrones[1]);
		assertEquals(new Integer(1), selectAvailableDrones[2]);
	}
	
	@Test
	public void get_hights_flight_range_with_maintenance_ids() {
		Drone[] drones = new Drone[]{new Drone(1, 11), new Drone(2, 5), new Drone(3, 16), new Drone(4, 12), new Drone(5, 8), new Drone(6, 10)};
		Integer[] maintenanceIds = new Integer[] {3,6}; 
		
		DronesSelector selector = new DronesSelector(drones, maintenanceIds);
		
		Integer[] selectAvailableDrones = selector.selectAvailableDrones(4);
		
		assertEquals(4, selectAvailableDrones.length);
		assertEquals(new Integer(4), selectAvailableDrones[0]);
		assertEquals(new Integer(1), selectAvailableDrones[1]);
		assertEquals(new Integer(5), selectAvailableDrones[2]);
		assertEquals(new Integer(2), selectAvailableDrones[3]);
	}
	
}
