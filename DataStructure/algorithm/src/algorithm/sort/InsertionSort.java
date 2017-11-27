package algorithm.sort;

public class InsertionSort<T extends Comparable<T>> extends AbstractSort<T> implements SortAlgorithm<T> {

	@Override
	public T[] sort(T[] items) {
		for(int i = 0; i < items.length-1; i++) {
			for(int j = i+1; j > 0; j--) {
				if(items[j-1].compareTo(items[j]) <= 0) {
					break;
				}
				swap(items, j-1, j);
			}
		}
		return items;
	}

}
