package algorithm.cases.arrays;

public class LinkedArray {

	private int[] linkedNumbers;

	public LinkedArray(int[] linkedNumbers) {
		this.linkedNumbers = linkedNumbers;
	}
	
	public int pathToTheEnnd() {
		return pathToTheEnd(0, 0);
	}

	private int pathToTheEnd(int index, int count) {
		if(linkedNumbers[index] == -1) {
			return count;
		}
		return pathToTheEnd(linkedNumbers[index], count+1);
	}
	
}
