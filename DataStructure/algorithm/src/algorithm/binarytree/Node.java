package algorithm.binarytree;

public class Node<T> {

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

	@Override
	public String toString() {
		return "Node[" + data + "]";
	}
}
