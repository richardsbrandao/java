package datastructures.lists;

import java.util.NoSuchElementException;

import datastructures.base.Deque;
import datastructures.base.List;
import datastructures.base.linked.Node;

public class Linked<T extends Object> implements List<T>, Deque<T> {

	private int size;
	private Node<T> first;
	private Node<T> last;
	
	@Override
	// Time: O(n) - Space: O(1)
	public T get(int index) {
		if(size == 0 || index < 0 || index >= size) { throw new ArrayIndexOutOfBoundsException(); }
		return node(index).value();
	}

	private Node<T> findElementByPreviusStrategy(int index, Node<T> node, int nodeIndex) {
		if(index == nodeIndex) {
			return node;
		}
		return findElementByPreviusStrategy(index, node.previus(), --nodeIndex);
	}

	private Node<T> findElementByNextStrategy(int index, Node<T> node, int nodeIndex) {
		if(index == nodeIndex) {
			return node;
		}
		return findElementByNextStrategy(index, node.next(), ++nodeIndex);
	}

	@Override
	// Time: O(1) - Space: O(1)
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
	// Time: O(n) - Space: O(1)
	public void remove(int index) {
		if(size == 0 || index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		Node<T> element = node(index);
		Node<T> previus = element.previus();
		Node<T> next = element.next();
		
		if(previus != null) {
			previus.setNext(next);
		} else {
			first = next; // means it's the first element
		}
		if(next != null) {
			next.setPrev(previus);
		} else {
			last = previus; // means it's the last element
		}
		
		size--;
	}

	private Node<T> node(int index) {
		return size / 2 > index ? findElementByNextStrategy(index, first, 0) : findElementByPreviusStrategy(index, last, size-1);
	}

	@Override
	// Time: O(n) - Space: O(1)
	public void addAll(List<T> list) {
		addElementToList(list, 0);
	}

	private void addElementToList(List<T> list, int index) {
		if(list.size() == index) {
			return;
		}
		add(list.get(index));
		addElementToList(list, ++index);
	}

	@Override
	// Time: O(n) - Space: O(1)
	public void add(int index, T value) {
		if(index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		if(size == 0 || size == index) { // last element
			add(value);
			return;
		}
		
		Node<T> element = node(index);
		Node<T> previus = element.previus();
		Node<T> newNode = new Node<T>(value, previus, element);
		
		if(previus != null) {
			previus.setNext(newNode);
		} else {
			first = newNode;
		}
		
		element.setPrev(element);
		size++;
	}

	@Override
	// Time: O(1) - Space: O(1)
	public int size() {
		return size;
	}

	@Override
	// Time: O(1) - Space: O(1)
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	// Time: O(n) - Space: O(1)
	public boolean contains(T theElement) {
		return contains(theElement, first, last);
	}

	private boolean contains(T theElement, Node<T> first, Node<T> last) {
		if(first == null || last == null) { // empty list
			return false;
		}
		if( (first.value() == null || last.value() == null) && theElement == null ) { // if theElement is null check for null elements in linkedList
			return true;
		}
		if(first.value().equals(theElement) || last.value().equals(theElement)) { // if theElement is a value check for a value in linkedList
			return true;
		}
		if(first == last) { // stop if first == last --> O(n/2)
			return false;
		}
		return contains(theElement, first.next(), last.previus());
	}

	@Override
	// Time: O(1) - Space: O(1)
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	@Override
	// Time: O(1) - Space: O(1)
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
	// Time: O(1) - Space: O(1)
	public void addLast(T element) {
		add(element);
	}

	@Override
	// Time: O(1) - Space: O(1)
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
	// Time: O(1) - Space: O(1)
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
	// Time: O(1) - Space: O(1)
	public T getFirst() {
		if(first == null) {
			throw new NoSuchElementException();
		}
		return first.value();
	}

	@Override
	// Time: O(1) - Space: O(1)
	public T getLast() {
		if(last == null) {
			throw new NoSuchElementException();
		}
		return last.value();
	}

	@Override
	// Time: O(n) - Space: O(n)
	public Object[] toArray() {
		Object[] elementsCopy = new Object[size];
		Node<T> current = this.first;
		int i = 0;
		while(current != null) {
			elementsCopy[i++] = current.value();
			current = current.next();
		}
		return elementsCopy;
	}
}
