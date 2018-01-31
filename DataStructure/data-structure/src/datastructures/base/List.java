package datastructures.base;

public interface List<T> {

	T get(int index);
	void add(T element);
	void remove(int index);

	void addAll(List<T> array);
	void add(int index, T value);

	int size();
	boolean isEmpty();
	boolean contains(T theElement);
	void clear();
	
	Object[] toArray();
}
