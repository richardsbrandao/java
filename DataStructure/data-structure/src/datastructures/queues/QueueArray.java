package datastructures.queues;

import datastructures.base.Queue;
import datastructures.errors.QueueOverflowError;

public class QueueArray<T> implements Queue<T> {

	private Object[] elements;
	private int size;
	private int capacity;
	private boolean dynamic;
	
	public QueueArray() {
		this(10, true);
	}
	
	public QueueArray(int capacity) {
		this(capacity, true);
	}
	
	public QueueArray(int capacity, boolean dynamic) {
		this.size = 0;
		this.capacity = capacity;
		this.dynamic = dynamic;
		this.elements = new Object[capacity];
	}
	
	@SuppressWarnings("unchecked")
	// Time: O(1) - Space: O(1)
	public T peek() {
		throwErrorIfIsEmpty();
		return (T) this.elements[0];
	}
	
	private void throwErrorIfIsEmpty() {
		if(isEmpty()) {
			throw new IllegalAccessError();
		}
	}

	// Time: O(1) - Space: O(1)
	// Worst Time: O(n) - Worst Space: O(n)
	public void push(T element) {
		increaseCapacityIfNecessary();
		throwErrorIfIsOverflow();
		this.elements[size] = element;
		size++;
	}
	
	private void throwErrorIfIsOverflow() {
		if(isFull()) {
			throw new QueueOverflowError();
		}
	}

	private void increaseCapacityIfNecessary() {
		if(!dynamic || size != capacity) {
			return;
		}
		
		int newCapacity = capacity + capacity/2;
		Object[] newArrayOfElements = new Object[newCapacity];
		
		for(int i = 0; i < size; i++) {
			newArrayOfElements[i] = this.elements[i];
		}
		
		this.elements = newArrayOfElements;
		this.capacity = newCapacity;
	}

	@SuppressWarnings("unchecked")
	// Time: O(n) - Space: O(1)
	public T pop() {
		throwErrorIfIsEmpty();
		T element = (T) this.elements[0];
		--size;
		for(int i = 0; i < size; i++) {
			this.elements[i] = this.elements[i+1];
		}
		this.elements[size] = null;
		return element;
	}
	
	// Time: O(1) - Space: O(1)
	public int size() {
		return size;
	}
	
	// Time: O(1) - Space: O(1)
	public boolean isFull() {
		return size == capacity;
	}
	
	// Time: O(1) - Space: O(1)
	public boolean isEmpty() {
		return size == 0;
	}
	
}
