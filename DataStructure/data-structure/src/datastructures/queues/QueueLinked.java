package datastructures.queues;

import datastructures.base.Queue;
import datastructures.base.linked.Node;
import datastructures.errors.QueueOverflowError;

public class QueueLinked<T> implements Queue<T> {

	private Node<T> head;
	private Node<T> last;
	
	private int size;
	private int capacity;
	private boolean dynamic;

	public QueueLinked() {
		this(10, true);
	}
	
	public QueueLinked(int capacity) {
		this(capacity, true);
	}
	
	public QueueLinked(int capacity, boolean dynamic) {
		this.size = 0;
		this.capacity = capacity;
		this.dynamic = dynamic;
	}
	
	@Override
	// Time: O(1) - Space: O(1)
	public T peek() {
		throwErrorifIsEmpty();
		return head.value();
	}

	private void throwErrorifIsEmpty() {
		if(isEmpty()) {
			throw new IllegalAccessError();
		}
	}

	@Override
	// Time: O(1) - Space: O(1)
	public T pop() {
		throwErrorifIsEmpty();
		T element = head.value();
		Node<T> newHead = head.next();
		
		if(newHead!= null) { 
			newHead.setPrev(null);
		}
		
		head = newHead;
		size--;
		return element;
	}

	@Override
	// Time: O(1) - Space: O(1)
	public void push(T element) {
		increaseCapacityIfNecessary();
		throwErrorIfIsOverflow();
		Node<T> oldLast = this.last;
		Node<T> newElement = new Node<T>(element, oldLast, null);
		
		if(this.head == null) {
			this.head = newElement;
		} else {
			oldLast.setNext(newElement);
		}
		
		this.last = newElement;
		this.size++;
	}

	private void increaseCapacityIfNecessary() {
		if(this.dynamic && isFull()) {
			this.capacity += this.capacity/2;
		}
	}

	private void throwErrorIfIsOverflow() {
		if(isFull()) {
			throw new QueueOverflowError();
		}
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
	// Time: O(1) - Space: O(1)
	public boolean isFull() {
		return capacity == size;
	}

}
