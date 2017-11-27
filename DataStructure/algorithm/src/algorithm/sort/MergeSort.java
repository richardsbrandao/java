package algorithm.sort;

import java.lang.reflect.Array;

public class MergeSort<T extends Comparable<T>> implements SortAlgorithm<T> {

	private Class<T> clazz;

	public MergeSort(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] sort(T[] items) {
		if(items.length == 1) {
			return items;
		}

		int midPoint = items.length / 2;
		T[] firstHalf = (T[]) Array.newInstance(clazz, midPoint);
		T[] secondHalf = (T[]) Array.newInstance(clazz, items.length-midPoint);

		split(items, firstHalf, secondHalf);

		firstHalf = sort(firstHalf);
		secondHalf = sort(secondHalf);
		
		return merge(items, firstHalf, secondHalf);
	}

	private void split(T[] items, T[] firstHalf, T[] secondHalf) {
		for(int i = 0; i < items.length; i++) {
			if( i >= firstHalf.length ) {
				secondHalf[i - firstHalf.length] = items[i];
			} else {
				firstHalf[i] = items[i];
			}
		}
	}

	private T[] merge(T[] items, T[] firstHalf, T[] secondHalf) {
		int mergeIndex = 0;
		int firstHalfIndex = 0;
		int secondHalfIndex = 0;
		
		while( firstHalfIndex < firstHalf.length && secondHalfIndex < secondHalf.length ) {
			if( firstHalf[firstHalfIndex].compareTo(secondHalf[secondHalfIndex]) < 0 ) {
				items[mergeIndex++] = firstHalf[firstHalfIndex++];
			} else {
				items[mergeIndex++] = secondHalf[secondHalfIndex++];
			}
		}
		
		while(firstHalfIndex < firstHalf.length) {
			items[mergeIndex++] = firstHalf[firstHalfIndex++];
		}
		
		while(secondHalfIndex < secondHalf.length) {
			items[mergeIndex++] = secondHalf[secondHalfIndex++];
		}
//		if( firstHalfIndex < firstHalf.length ) {
//			while(mergeIndex < items.length) {
//				items[mergeIndex++] = firstHalf[firstHalfIndex++];
//			}
//		}
//		
//		if (secondHalfIndex < secondHalf.length) {
//			while(mergeIndex < items.length) {
//				items[mergeIndex++] = secondHalf[secondHalfIndex++];
//			}
//		}
		
		return items;
	}

}
