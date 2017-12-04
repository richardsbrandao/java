package algorithm.cases.delivery;

import java.util.Arrays;

public class Delivey {

	private Integer[] packagesWeight;
	private Integer maxWeightConstraint;

	public Delivey(Integer[] packagesWeight, Integer maxWeightConstraint) {
		this.packagesWeight = packagesWeight;
		this.maxWeightConstraint = maxWeightConstraint;
	}

	public int count() {
		Arrays.sort(packagesWeight);
		int counter = 0;
		int low = 0;
		int high = packagesWeight.length-1;
		while(low <= high) {
			int lighterPackage = packagesWeight[low];
			int heavierPackage = packagesWeight[high];
			
			if(lighterPackage+heavierPackage <= maxWeightConstraint) {
				counter++;
				low++;
				high--;
			} else {
				counter++;
				high--;
			}
		}
		return counter;
	}
	
}
