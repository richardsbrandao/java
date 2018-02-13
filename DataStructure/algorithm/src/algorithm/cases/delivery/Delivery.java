package algorithm.cases.delivery;

import java.util.Arrays;

/**
 * Receive: a list of packages with their weight and the max weight per delivery
 * Result: The smaller number of deliveries that can be performed
 * 
 * Solution: Runtime O(n log n) - Space O(1)
 */
public class Delivery {

	private Integer[] packagesWeight;
	private Integer maxWeightConstraint;

	public Delivery(Integer[] packagesWeight, Integer maxWeightConstraint) {
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
			counter++;
			high--;

			if(lighterPackage+heavierPackage <= maxWeightConstraint) {
				low++;
			}
		}
		return counter;
	}
	
}
