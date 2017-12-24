package datastructures.lists;

import java.util.NoSuchElementException;

import datastructures.base.LinkedStructure;
import datastructures.base.List;
import datastructures.lists.linked.Node;

public class Linked<T extends Object> implements List<T>, LinkedStructure<T> {

	private int size;
	private Node<T> first;
	private Node<T> last;
	
	@Override
	public T get(int index) {
		return null;
	}

	@Override
	public void add(T element) {
		Node<T> oldLast = last;
		Node<T> newLastNode = new Node<T>(element, last, null);
		last = newLastNode;
		if(oldLast == null) {
			first = newLastNode;
		} else {
			oldLast.setNext(newLastNode);
		}
		
		size++;
	}

	@Override
	public void remove(int index) {
		
	}

	@Override
	public void addAll(List<T> array) {
		
	}

	@Override
	public void add(int index, T value) {
		
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(T theElement) {
		return false;
	}

	@Override
	public void clear() {
		
	}

	@Override
	public void addFirst(T element) {
		Node<T> oldFirst = first;
		first = new Node<T>(element, null, oldFirst);
		if( oldFirst != null ) {
			oldFirst.setPrev(first);
		} else {
			last = first;
		}
	}

	@Override
	public void addLast(T element) {
		add(element);
	}

	@Override
	public void removeFirst() {
		if(first == null) {
			throw new NoSuchElementException();
		}
		Node<T> newFirst = first.next();
		if( newFirst == null ) {
			last = newFirst;
		} else {
			newFirst.setPrev(null);
		}
		first = newFirst;
		size--;

	}

	@Override
	public void removeLast() {
		if(last == null) {
			throw new NoSuchElementException();
		}
		Node<T> newLast = last.previus();
		if( newLast == null ) {
			first = newLast;
		} else {
			newLast.setNext(null);
		}
		last = newLast;
		size--;
	}

	@Override
	public T getFirst() {
		if(first == null) {
			throw new NoSuchElementException();
		}
		return first.value();
	}

	@Override
	public T getLast() {
		if(last == null) {
			throw new NoSuchElementException();
		}
		return last.value();
	}
}
