package algorithm.cases.drones;

/**
 * Receive: A list of drones (with flight range and id) and another list with drones in maintenance
 * Solution: Return a list of enabled drones sorted by flight range
 *
 * Complexity: Runtime O(n) Space O(n)
 */
public class DronesSelector {

	private Drone[] drones;
	private Integer[] maintenanceDrones;

	public DronesSelector(Drone[] drones, Integer[] maintenanceDrones) {
		this.drones = drones;
		this.maintenanceDrones = maintenanceDrones;
	}

	public Integer[] selectAvailableDrones(Integer number) {
		sortDrones(drones, 0, drones.length-1);
		return subList(drones, number);
	}

	private Integer[] subList(Drone[] drones, Integer number) {
		Integer[] selectedDrones = new Integer[number];
		int numberOfSelectedDrones = 0;
		for(int i = 0; numberOfSelectedDrones < number; i++) {
			if( !isInMaintenance(drones[i]) ) {
				selectedDrones[numberOfSelectedDrones++] = drones[i].getId();
			}
		}
		return selectedDrones;
	}

	private void sortDrones(Drone[] drones, int low, int high) {
		if( low > high ) {
			return;
		}
		
		int pivotIndex = partition(drones, low, high);
		sortDrones(drones, low, pivotIndex-1);
		sortDrones(drones, pivotIndex+1, high);
	}

	private int partition(Drone[] drones, int low, int high) {
		Drone pivot = drones[low];
		int l = low;
		int h = high;
		while(l < h) {
			while(pivot.compareTo(drones[l]) <= 0 && l < h) {
				l++;
			}
			
			while(pivot.compareTo(drones[h]) > 0) {
				h--;
			}
			
			if( l < h ) {
				swap(drones, l, h);
			}
		}
		
		swap(drones, low, h);
		return h;
	}

	private boolean isInMaintenance(Drone drone) {
		for( Integer maintenanceDroneId : maintenanceDrones ) {
			if( drone.getId().equals(maintenanceDroneId) ) {
				return true;
			}
		}
		return false;
	}

	private void swap(Drone[] drones, int indexOne, int indexTwo) {
		Drone aux = drones[indexOne];
		drones[indexOne] = drones[indexTwo];
		drones[indexTwo] = aux;
	}
	
}
