package datastructures.base.tree;

public class TreeNode<T extends Comparable<T>> {

	private T element;
	private TreeNode<T> left;
	private TreeNode<T> right;
	
	public TreeNode(T element) {
		this.element = element;
	}

	public T getElement() {
		return element;
	}

	public TreeNode<T> getLeft() {
		return left;
	}

	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}
	
	public TreeNode<T> getRight() {
		return right;
	}
	
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}
	
	public boolean greaterThan(TreeNode<T> value) {
		return greaterThan(value.getElement());
	}

	public boolean equalElement(T value) {
		return this.element.equals(value);
	}

	public boolean greaterThan(T value) {
		return this.element.compareTo(value) > 0;
	}

	@Override
	public String toString() {
		return "TreeNode [element=" + element + "]";
	}

	public boolean hasRight() {
		return right != null;
	}

	public boolean lessThan(T value) {
		return this.element.compareTo(value) < 0;
	}

	public boolean between(T min, T max) {
		return greaterThan(min) && lessThan(max);
	}

	public boolean hasOneChild() {
		return left == null ^ right == null;
	}
}
