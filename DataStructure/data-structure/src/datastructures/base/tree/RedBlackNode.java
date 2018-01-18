package datastructures.base.tree;

public class RedBlackNode<T extends Comparable<T>> {

	private RedBlackNode<T> parent;
	private ColorRedBlackNode color;
	private T element;
	private RedBlackNode<T> left;
	private RedBlackNode<T> right;
	
	public RedBlackNode(T element) {
		this.element = element;
		this.color = ColorRedBlackNode.RED;
	}
	
	public RedBlackNode(T element, ColorRedBlackNode color) {
		this.element = element;
		this.color = color;
	}
	
	public T getElement() {
		return element;
	}
	
	public ColorRedBlackNode getColor() {
		return color;
	}
	
	public RedBlackNode<T> getLeft() {
		return left;
	}
	
	public void setLeft(RedBlackNode<T> left) {
		this.left = left;
	}
	
	public RedBlackNode<T> getRight() {
		return right;
	}
	
	public void setRight(RedBlackNode<T> right) {
		this.right = right;
	}
	
	public RedBlackNode<T> getParent() {
		return parent;
	}
	
	public void setParent(RedBlackNode<T> parent) {
		this.parent = parent;
	}

	public boolean greaterThan(RedBlackNode<T> anotherNode) {
		return this.element.compareTo(anotherNode.element) > 0;
	}

	@Override
	public String toString() {
		return "RedBlackNode [color=" + color + ", element=" + element + "]";
	}

	public boolean isRoot() {
		return parent == null;
	}

	public void turnBlack() {
		this.color = ColorRedBlackNode.BLACK;
	}

	public void turnRed() {
		this.color = ColorRedBlackNode.RED;
	}

	public boolean isBlack() {
		return this.color == ColorRedBlackNode.BLACK;
	}

	public boolean isRed() {
		return this.color == ColorRedBlackNode.RED;
	}

	public RedBlackNode<T> getUncle(RedBlackNode<T> grandParent) {
		boolean isParentALeftChild = grandParent.getLeft() == parent;
		return isParentALeftChild ? grandParent.getRight() : grandParent.getLeft();
	}
}
