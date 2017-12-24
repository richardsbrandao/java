package datastructures.lists.linked;

public class Node<T> {

	private T value;
	private Node<T> prev;
	private Node<T> next;
	
	public Node(T element) {
		this.value = element;
	}

	public Node(T element, Node<T> prev, Node<T> next) {
		this(element);
		this.prev = prev;
		this.next = next;
	}
	
	public T value() {
		return value;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

	public Node<T> previus() {
		return prev;
	}

	public Node<T> next() {
		return next;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}
}
