package algorithm.binary;

import algorithm.sort.QuickSort;

public class BinarySearch<T extends Comparable<T>> {
	
	private T[] elements;

	public BinarySearch(T[] elements) {
		this.elements = sort(elements);
	}

	private T[] sort(T[] elements) {
		return new QuickSort<T>().sort(elements);
	}
	
	public Integer index(T element) {
		return binarySearch(element, 0, elements.length-1);
	}

	private Integer binarySearch(T element, int min, int max) {
		Integer midpoint = min + (max - min) / 2;
		if( min > max ) {
			return null;
		}
		if( elements[midpoint].equals(element) ) {
			return midpoint;
		}
		min = elements[midpoint].compareTo(element) > 0 ? min : midpoint+1;
		max = elements[midpoint].compareTo(element) < 0 ? max : midpoint-1; 
		
		return binarySearch(element, min, max);
	}
	
}
