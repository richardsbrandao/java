package datastructures.base;

public interface Heap<T extends Comparable<T>> {
	
	public T get();
	public void add(T value);
	public T remove();
	public int size();
	
}
