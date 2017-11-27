package algorithm.sort;

import java.util.Arrays;

public class QuickSort<T extends Comparable<T>> extends AbstractSort<T> implements SortAlgorithm<T> {

	@Override
	public T[] sort(T[] items) {
		Integer pivotIndex = partition(items, 0, items.length-1);
		quickSort(items, 0, pivotIndex - 1);
		quickSort(items, pivotIndex + 1, items.length-1);
		return items;
	}
	
	private T[] quickSort(T[] items, int low, int high) {
		if(low >= high) {
			return items;
		}
		System.out.println(Arrays.toString(items));
		Integer pivotIndex = partition(items, low, high);
		quickSort(items, low, pivotIndex - 1);
		quickSort(items, pivotIndex + 1, high);
		return items;
	}

	public Integer partition(T[] items, int low, int high) {
		T pivot = items[low];
		int l = low;
		int h = high;
		while(l < h) {
			while( items[l].compareTo(pivot) <= 0 && l < h ) 
				l++;
			while( items[h].compareTo(pivot) > 0 ) 
				h--;
			if(l < h) 
				swap(items, l, h);
		}
		swap(items, low, h);
		return h;
	}
}
