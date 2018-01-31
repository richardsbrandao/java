package datastructures.lists;

import datastructures.base.List;

public class Array<T extends Object> implements List<T> {

	private Object[] elements;
	private int size;
	
	public Array(Integer size) {
		this.elements = new Object[size];
		this.size = 0;
	}

	// Time: O(1) - Space: O(1)
	public int size() {
		return size;
	}
	
	// Time: O(1) - Space: O(1)
	public boolean isEmpty() {
		return size == 0;
	}

	// Time: O(n) - Space: O(1)
	public boolean contains(T theElement) {
		for(int i = 0; i < size; i++) {
			if(elements[i].equals(theElement)) {
				return true;
			}
		}
		return false;
	}

	// Time: O(1) - Space: O(1)
	public void add(T element) {
		increaseCapacityIfReached();
		this.elements[size] = element;
		this.size++;
	}

	// Time: O(n) - Space: O(n)
	public void addAll(List<T> array) {
		for(int i = 0; i < array.size(); i++) {
			add(array.get(i));
		}
	}

	// Time: O(1) - Space: O(1)
	public void clear() {
		this.elements = new String[elements.length];
		this.size = 0;
	}

	// Time: O(1) - Space: O(1)
	@SuppressWarnings("unchecked")
	public T get(int index) {
		errorIfPositionIsNotValid(index);
		return (T) this.elements[index];
	}

	// Time: O(n) - Space: O(1)
	public void remove(int index) {
		errorIfPositionIsNotValid(index);
		
		for( int i = index; i < size-1; i++) {
			this.elements[i] = this.elements[i+1]; 
		}
		this.elements[size-1] = null;
		this.size--;
	}
	
	public void add(int index, T value) {
		errorIfPositionIsNotValid(index);
		increaseCapacityIfReached();
		
		for(int i = size; i > index; i--) {
			elements[i] = elements[i-1];
		}
		elements[index] = value;
		size++;
	}

	private void errorIfPositionIsNotValid(int index) {
		if(isNotAValidPosition(index)) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	private boolean isNotAValidPosition(int index) {
		return index < 0 || index > this.size;
	}
	
	private void increaseCapacity() {
		Object[] newElements = new Object[this.elements.length*2];
		for(int i = 0; i < size; i++) {
			newElements[i] = this.elements[i];
		}
		this.elements = newElements;
	}
	
	private void increaseCapacityIfReached() {
		if(elements.length == size) {
			increaseCapacity();
		}
	}

	@Override
	// Time: O(n) - Space: O(n)
	public Object[] toArray() {
		Object[] elementsCopy = new Object[size];
		for(int i = 0; i < this.size; i++) {
			elementsCopy[i] = this.elements[i];
		}
		return elementsCopy;
	}

}
