package quicksort;

import java.util.concurrent.RecursiveAction;

public class QuickSortTask extends RecursiveAction {

	private static final long serialVersionUID = 1L;
	
	public Integer[] numbers;
	private int low;
	private int high;

	public QuickSortTask(Integer[] numbers) {
		this(numbers, 0, numbers.length-1);
	}

	public QuickSortTask(Integer[] numbers, int low, int high) {
		this.numbers = numbers;
		this.low = low;
		this.high = high;
	}

	@Override
	protected void compute() {
		if(low < high) {
			int pivot = partition(low, high);
			invokeAll(	new QuickSortTask(numbers, low, pivot-1), 
						new QuickSortTask(numbers, pivot+1, high));
		}
	}

	private int partition(int low, int high) {
		int pivot = numbers[low];
		int l = low;
		int h = high;
		while(l < h) {
			while(numbers[l] <= pivot && l < h)
				l++;
			
			while(numbers[h] > pivot) 
				h--;
			
			if(l < h)
				swap(l, h);
		}
		swap(low, h);
		return h;
	}

	private void swap(int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
}
