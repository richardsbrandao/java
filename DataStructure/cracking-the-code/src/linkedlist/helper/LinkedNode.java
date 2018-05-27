package linkedlist.helper;

public class LinkedNode<T> {
	private T value;
	private LinkedNode<T> next;
	
	public LinkedNode(T value, LinkedNode<T> next) {
		this.value = value;
		this.next = next;
	}
	
	public LinkedNode() {
	}

	public LinkedNode<T> getNext() {
		return next;
	}
	
	public void setNext(LinkedNode<T> next) {
		this.next = next;
	}
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "["+value+"]";
	}
	
}
