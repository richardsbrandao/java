package datastructures.trees;

import datastructures.base.Tree;
import datastructures.base.tree.TreeNode;
import datastructures.lists.Array;

public class BinarySearchTree<T extends Comparable<T>> implements Tree<T> {

	private TreeNode<T> root;
	private int size;
	
	public BinarySearchTree(T root) {
		if(root == null) {
			throw new IllegalArgumentException();
		}
		this.root = new TreeNode<T>(root);
		size = 1;
	}
	
	@Override
	public void add(T value) {
		insert(root, new TreeNode<T>(value));
		size++;
	}

	private TreeNode<T> insert(TreeNode<T> node, TreeNode<T> value) {
		if(node == null) {
			return value;
		}
		
		if(node.greaterThan(value)) {
			node.setLeft( insert(node.getLeft(), value) );
		} else {
			node.setRight( insert(node.getRight(), value) );
		}
		
		return node;
	}

	@Override
	public void remove(T value) {
		TreeNode<T> nodeToRemove = binarySearch(root, value);
		if(nodeToRemove == null) {
			return;
		}
		TreeNode<T> parent = parent(root, null, nodeToRemove);
		
		if(parent == null && size == 1) {
			throw new IllegalArgumentException();
		}
		
		if(!nodeToRemove.isLeaf()) { // for a while, only leaf nodes
			throw new IllegalArgumentException();
		}
		
		if(parent.greaterThan(nodeToRemove)) {
			parent.setLeft(null);
		} else {
			parent.setRight(null);
		}
		
		size--;
	}

	private TreeNode<T> parent(TreeNode<T> head, TreeNode<T> parent, TreeNode<T> node) {
		if(head == null) {
			return null;
		}
		
		if(node.equalElement(head.getElement())) {
			return parent;
		}
		
		if(head.greaterThan(node)) {
			return parent(head.getLeft(), head, node);
		} else {
			return parent(head.getRight(), head, node);
		}
	}

	@Override
	public TreeNode<T> get(T value) {
		return binarySearch(root, value);
	}

	private TreeNode<T> binarySearch(TreeNode<T> head, T value) {
		if(head == null) {
			return null;
		}
		
		if(head.equalElement(value)) {
			return head;
		}
		
		if(head.greaterThan(value)) {
			return binarySearch(head.getLeft(), value);
		} else {
			return binarySearch(head.getRight(), value);
		}
	}

	@Override
	public boolean contains(T value) {
		return binarySearch(root, value) != null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T max() {
		return max(root);
	}
	
	private T max(TreeNode<T> head) {
		if(head.getRight() == null) {
			return head.getElement();
		}
		
		return max(head.getRight());
	}

	@Override
	public T min() {
		return min(root);
	}

	private T min(TreeNode<T> head) {
		if(head.getLeft() == null) {
			return head.getElement();
		}
		
		return min(head.getLeft());
	}

	@Override
	public int height() {
		return height(root);
	}

	private int height(TreeNode<T> head) {
		if(head == null) {
			return 0;
		}
		
		int leftHeight = 1 + height(head.getLeft());
		int rightHeight = 1 + height(head.getRight());
		return Math.max(leftHeight, rightHeight);
	}

	@Override
	public int deapth(T value) {
		return deapth(root, value, 0);
	}

	private int deapth(TreeNode<T> head, T value, int deapth) {
		if(head == null) {
			return -1;
		}
		
		if(head.equalElement(value)) {
			return deapth;
		}
		
		if(head.greaterThan(value)) {
			return deapth(head.getLeft(), value, deapth+1);
		} else {
			return deapth(head.getRight(), value, deapth+1);
		}
	}

	@Override
	public Array<Object> preOrder() {
		Array<Object> elements = new Array<Object>(size);
		return preOrder(root, elements);
	}

	private Array<Object> preOrder(TreeNode<T> head, Array<Object> elements) {
		if(head == null) {
			return elements;
		}
		
		elements.add(head.getElement());
		preOrder(head.getLeft(), elements);
		preOrder(head.getRight(), elements);
		return elements;
	}

	@Override
	public Array<Object> inOrder() {
		Array<Object> elements = new Array<Object>(size);
		return inOrder(root, elements);
	}

	private Array<Object> inOrder(TreeNode<T> head, Array<Object> elements) {
		if(head == null) {
			return elements;
		}
		
		inOrder(head.getLeft(), elements);
		elements.add(head.getElement());
		inOrder(head.getRight(), elements);
		return elements;
	}

	@Override
	public Array<Object> postOrder() {
		Array<Object> elements = new Array<Object>(size);
		return postOrder(root, elements);
	}

	private Array<Object> postOrder(TreeNode<T> head, Array<Object> elements) {
		if(head == null) {
			return elements;
		}
		
		postOrder(head.getLeft(), elements);
		postOrder(head.getRight(), elements);
		elements.add(head.getElement());
		
		return elements;
	}

	@Override
	public Array<T> elementsIn(T min, T max) {
		Array<T> elements = new Array<T>(size);
		return elementsIn(root, elements, min, max);
	}

	private Array<T> elementsIn(TreeNode<T> head, Array<T> elements, T min, T max) {
		if(head == null) {
			return elements;
		}
		
		if(head.between(min, max)) {
			elements.add(head.getElement());
		}

		if(!head.greaterThan(min)) {
			elementsIn(head.getRight(), elements, min, max);
			return elements;
		}
		
		if(!head.lessThan(max)) {
			elementsIn(head.getLeft(), elements, min, max);
			return elements;
		}
		
		elementsIn(head.getRight(), elements, min, max);
		elementsIn(head.getLeft(), elements, min, max);
		return elements;
	}

}
