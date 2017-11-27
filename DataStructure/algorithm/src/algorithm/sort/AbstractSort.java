package algorithm.sort;

import java.util.Arrays;

public abstract class AbstractSort<T> {
	
	protected void print(T[] items) {
		System.out.println(Arrays.toString(items));
	}

	protected void swap(T[] items, int indexOne, int indexTwo) {
		T aux = items[indexOne];
		items[indexOne] = items[indexTwo];
		items[indexTwo] = aux;
	}
	
}
