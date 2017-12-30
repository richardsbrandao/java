package datastructures.base;

public interface Deque<T> {

	void addFirst(T element);
	void addLast(T element);
	
	void removeFirst();
	void removeLast();
	
	T getFirst();
	T getLast();
	
}
