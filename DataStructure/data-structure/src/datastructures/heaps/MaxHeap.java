package datastructures.heaps;

import java.lang.reflect.Array;

import datastructures.base.Heap;

public class MaxHeap<T extends Comparable<T>> implements Heap<T> {

	private Class<T> clazz;
	private T[] elements;
	private int size;

	public MaxHeap(Class<T> clazz) {
		this.clazz = clazz;
		this.elements = initializeElements(50);
		this.size = 0;
	}
	
	@SuppressWarnings("unchecked")
	private T[] initializeElements(int capacity) {
		return (T[]) Array.newInstance(this.clazz, capacity);
	}

	@Override
	public T get() {
		throwErrorIfIsEmpty();
		return this.elements[0];
	}

	private boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void add(T value) {
		doubleCapacityIfReched();
		this.elements[size] = value;
		shiftUp(size);
		size++;
	}

	private void doubleCapacityIfReched() {
		if(isFull()) {
			T[] newElements = initializeElements(size*2);
			for(int i = 0; i < size; i++) {
				newElements[i] = this.elements[i];
			}
			this.elements = newElements;
		}
	}

	private boolean isFull() {
		return this.elements.length == size;
	}

	private void shiftUp(int index) {
		int parentIndex = getParentFromIndex(index);
		if(isValidIndex(parentIndex) && isCurrentGreaterThanParent(index, parentIndex)) {
			swap(parentIndex, index);
			shiftUp(parentIndex);
		}
	}

	private void swap(int index, int anotherIndex) {
		T tmp = this.elements[index];
		this.elements[index] = this.elements[anotherIndex];
		this.elements[anotherIndex] = tmp;
	}

	private boolean isCurrentGreaterThanParent(int index, int parentIndex) {
		return this.elements[index].compareTo(this.elements[parentIndex]) > 0;
	}

	private int getParentFromIndex(int index) {
		if(index == 0 || index > this.elements.length) {
			return -1;
		}
		return (index - 1) / 2;
	}

	private boolean isValidIndex(int index) {
		return index != -1 && index <= size;
	}

	@Override
	public T remove() {
		throwErrorIfIsEmpty();
		T removedElement = this.elements[0];
		if(--size == 0) {
			this.elements[0] = null;
			return removedElement;
		}
		this.elements[0] = this.elements[size];
		this.elements[size] = null;
		shiftDown(0);
		return removedElement;
	}

	private void shiftDown(int index) {
		int smallerChildIndex = getBiggestChildIndex(index);
		if(isValidIndex(smallerChildIndex) && ! isCurrentGreaterThanParent(index, smallerChildIndex)) {
			swap(smallerChildIndex, index);
			shiftDown(smallerChildIndex);
		}
	}

	private int getBiggestChildIndex(int index) {
		int leftChild = index * 2 + 1;
		int rightChild = leftChild + 1;
		if(this.elements[leftChild] == null) {
			return -1;
		}
		if(this.elements[rightChild] == null) {
			return leftChild;
		}
		return this.elements[leftChild].compareTo(this.elements[rightChild]) > 0 ? leftChild : rightChild;
	}

	private void throwErrorIfIsEmpty() throws IllegalAccessError {
		if(isEmpty()) {
			throw new IllegalAccessError();
		}
	}

	@Override
	public int size() {
		return size;
	}

}
