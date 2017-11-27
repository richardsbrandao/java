package algorithm.sort;

public class BubbleSort<T extends Comparable<T>> extends AbstractSort<T> implements SortAlgorithm<T> {

	@Override
	public T[] sort(T[] items) {
		for( int i = 0; i < items.length; i++ ) {
			Boolean swapped = false;
			for(int j = i+1; j < items.length; j++) {
				if(items[i].compareTo(items[j]) > 0) {
					swap(items, i, j);
					swapped = true;
				}
			}
			if(!swapped) { break; }
		}
		return items;
	}

}
