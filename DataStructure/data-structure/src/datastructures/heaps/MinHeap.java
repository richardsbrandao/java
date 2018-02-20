package datastructures.heaps;

import java.lang.reflect.Array;

import datastructures.base.Heap;

public class MinHeap<T extends Comparable<T>> implements Heap<T> {

	private T[] elements;
	private int size;
	
	public MinHeap(Class<T> clazz) {
		this(clazz, 50);
	}
	
	@SuppressWarnings("unchecked")
	public MinHeap(Class<T> clazz, int capacity) {
		this.elements = (T[]) Array.newInstance(clazz, capacity);
		this.size = 0;
	}

	@Override
	public T get() {
		return null;
	}

	@Override
	public void add(T value) {
//		heap full
		this.elements[size] = value;
		shiftUp(size);
		size++;
	}

	private void shiftUp(int index) {
		int parentIndex = getParentIndex(index);
		if(isValidElement(parentIndex) && isCurrentSmallerThanParent(index, parentIndex)) {
			swap(index, parentIndex);
			shiftUp(parentIndex);
		}
	}

	private boolean isValidElement(int index) {
		return index != -1 || index >= size;
	}

	private void swap(int index, int parentIndex) {
		T tmp = this.elements[index];
		this.elements[index] = this.elements[parentIndex];
		this.elements[parentIndex] = tmp;
	}

	private int getParentIndex(int index) {
		if(index == 0 || index > this.elements.length) {
			return -1;
		}
		return (index - 1) / 2;
	}

	private boolean isCurrentSmallerThanParent(int index, int parentIndex) {
		return this.elements[index].compareTo(this.elements[parentIndex]) < 0;
	}

	@Override
	public T remove() {
		if(size == 0) {
			throw new IllegalAccessError();
		}
		T removedElement = this.elements[0];
		if(size-- == 1) {
			this.elements[0] = null;
			return removedElement;
		}
		this.elements[0] = this.elements[size];
		shiftDown(0);
		return removedElement;
	}

	private void shiftDown(int index) {
		int smallerChildIndex = getSmallerChildIndex(index);
		if(isValidElement(smallerChildIndex) && ! isCurrentSmallerThanParent(index, smallerChildIndex)) {
			swap(index, smallerChildIndex);
			shiftDown(smallerChildIndex);
		}
	}

	private int getSmallerChildIndex(int index) {
		int leftChildIndex = index * 2 + 1;
		int rightChildIndex = leftChildIndex + 1;
		if( leftChildIndex >= size ) {
			return -1;
		}
		if(this.elements[rightChildIndex] == null) {
			return leftChildIndex;
		}
		return this.elements[leftChildIndex].compareTo(this.elements[rightChildIndex]) <= 0 ? leftChildIndex : rightChildIndex;
	}

	@Override
	public int size() {
		return size;
	}

}
