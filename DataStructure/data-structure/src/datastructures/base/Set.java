package datastructures.base;

public interface Set<T> {

	void add(T element);
	void remove(T element);

	void clear();
	int size();
	boolean isEmpty();
	boolean contains(T element);
	
	Object[] elements();
}
