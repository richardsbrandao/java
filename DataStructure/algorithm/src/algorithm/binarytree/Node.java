package algorithm.binarytree;

public class Node<T extends Comparable<T>> {

	private T data;
	private Node<T> left;
	private Node<T> right;

	public Node(T data) {
		this(data, null, null);
	}
	
	public Node(T data, Node<T> left, Node<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public T getData() {
		return data;
	}
	
	public Node<T> getLeft() {
		return left;
	}
	
	public Node<T> getRight() {
		return right;
	}
	
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	
	public void setRight(Node<T> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "Node[" + data + "]";
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}
	
	public boolean inRange(T min, T max) {
		return greaterThanOrEqual(min) && lessThanOrEqual(max);
	}

	public boolean lessThanOrEqual(T value) {
		return data.compareTo(value) <= 0;
	}

	public boolean greaterThanOrEqual(T value) {
		return data.compareTo(value) >= 0;
	}
	
	public void mirror() {
		Node<T> aux = left;
		left = right;
		right = aux;
	}
}
