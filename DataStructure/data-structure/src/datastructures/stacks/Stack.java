package datastructures.stacks;

public class Stack<T extends Object> {

	private Object[] elements;
	private int size;
	private int capacity;
	private boolean dynamic;

	public Stack() {
		this(10, true);
	}
	
	public Stack(int capacity) {
		this(capacity, true);
	}
	
	public Stack(int capacity, boolean dynamic) {
		this.size = 0;
		this.capacity = capacity;
		this.dynamic = dynamic;
		this.elements = new Object[capacity];
	}
	
	@SuppressWarnings("unchecked")
	public T peek() {
		throwErrorIfIsEmpty();
		return (T) this.elements[size-1];
	}
	
	private void throwErrorIfIsEmpty() {
		if(size == 0) {
			throw new IllegalAccessError();
		}
	}

	public void push(T element) {
		increaseCapacityIfNecessary();
		throwErrorIfIsOverflow();
		this.elements[size++] = element;
	}
	
	private void throwErrorIfIsOverflow() {
		if(isFull()) {
			throw new StackOverflowError();
		}
	}

	private void increaseCapacityIfNecessary() {
		if(!this.dynamic || this.capacity != this.size) {
			return;
		}
		
		int newCapcity = capacity * capacity/2;
		Object[] newElements = new Object[newCapcity];
		
		for(int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		
		this.capacity = newCapcity;
		this.elements = newElements;
	}

	@SuppressWarnings("unchecked")
	public T pop() {
		throwErrorIfIsEmpty();
		Object element = this.elements[size-1];
		this.elements[--size] = null;
		return (T) element;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isFull() {
		return size == capacity;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}
