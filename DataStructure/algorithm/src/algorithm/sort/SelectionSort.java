package algorithm.sort;

public class SelectionSort<T extends Comparable<T>> extends AbstractSort<T> implements SortAlgorithm<T> {

	@Override
	public T[] sort(T[] items) {
		for(int i = 0; i < items.length; i++) {
			for(int j = i+1; j < items.length; j++) {
				if(items[i].compareTo(items[j]) > 0) {
					swap(items, i, j);
				}
			}
			print(items);
		}
		return items;
	}

}
