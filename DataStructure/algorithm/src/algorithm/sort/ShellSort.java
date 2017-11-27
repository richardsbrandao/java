package algorithm.sort;

public class ShellSort<T extends Comparable<T>> extends AbstractSort<T> implements SortAlgorithm<T> {

	@Override
	public T[] sort(T[] items) {
		Integer increment = items.length / 2;
		while(increment >= 1) { // while increment is not one (when increment is one we'll do regular insertion sort)
			for(int startIndex = 0; startIndex < increment; startIndex++) { // controls the startIndex 
				insertionSort(items, startIndex, increment);
			}
			increment = increment / 2;
		}
		return items;
	}

	private void insertionSort(T[] items, int startIndex, Integer increment) {
		for(int i = startIndex; i < items.length; i = i + increment) {
			for(int j = Math.min(i + increment, items.length - 1); j - increment >= 0; j = j - increment) {
				if( items[j-increment].compareTo(items[j]) < 0 ) {
					break;
				}
				swap(items, j, j-increment);
			}
		}
	}

}
