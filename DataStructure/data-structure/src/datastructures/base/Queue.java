package datastructures.base;

public interface Queue<T> {

	T peek();
	T pop();
	void push(T element);
	int size();
	boolean isEmpty();
	boolean isFull();
	
}
