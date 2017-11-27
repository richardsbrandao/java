package algorithm.sort;

public class Sorter<T> {

	private SortAlgorithm<T> algorithm;
	private T[] items;

	public Sorter(SortAlgorithm<T> algorithm, T[] items) {
		this.algorithm = algorithm;
		this.items = items;
	}

	public T[] sort() {
		return algorithm.sort(items);
	}
	
}
