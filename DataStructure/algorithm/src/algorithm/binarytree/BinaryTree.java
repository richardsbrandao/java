package algorithm.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree<T extends Comparable<T>> {

	private Node<T> root;
	private LinkedList<T> valuesProcessed;

	public BinaryTree(Node<T> root) {
		this.root = root;
		this.valuesProcessed = new LinkedList<T>();
	}
	
	public void insert(T value) {
		insert(root, new Node<T>(value));
	}
	
	public Node<T> lookup(T data) {
		return lookup(root, data);
	}
	
	public T max() {
		return maximum(root).getData();
	}
	
	public Integer depth() {
		return depth(root);
	}
	
	public void mirror() {
		mirror(root);
	}

	private void mirror(Node<T> node) {
		if( node == null ) {
			return;
		}
		
		node.mirror();
		mirror(node.getLeft());
		mirror(node.getRight());
	}
	
	public List<T> elementsInRange(T min, T max) {
		List<T> collected = new ArrayList<T>();
		elementsInRange(root, collected, min, max);
		return collected;
	}

	private void elementsInRange(Node<T> node, List<T> collected, T min, T max) {
		if(node == null) {
			return;
		}
		
		if ( node.inRange(min, max) ) {
			collected.add(node.getData());
		}
		
		if(!node.greaterThanOrEqual(min)) { // break unnecessary left recursions
			elementsInRange(node.getRight(), collected, min, max);
			return;
		}
		
		if(!node.lessThanOrEqual(max)) { // break unnecessary right recursions
			elementsInRange(node.getLeft(), collected, min, max);
			return;
		}
		
		elementsInRange(node.getLeft(), collected, min, max);
		elementsInRange(node.getRight(), collected, min, max);
	}

	public void breadthProcess() {
		if(root == null) {
			return;
		}
		
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(root);
		
		while(!queue.isEmpty()) {
			Node<T> node = queue.remove();
			proccessNode(node);
			
			if(node.getLeft() != null) {
				queue.add(node.getLeft());
			}
			
			if(node.getRight() != null) {
				queue.add(node.getRight());
			}
		}
	}

	private void proccessNode(Node<T> node) {
		System.out.println(node);
		this.valuesProcessed.add(node.getData());
	}
	
	public void preOrderProcess() {
		preOrder(root);
	}
	
	public void postOrderProcess() {
		postOrder(root);
	}
	
	public void inOrderProcess() {
		inOrder(root);
	}
	
	public LinkedList<T> getValuesProcessed() {
		return valuesProcessed;
	}
	
	public Node<T> getRoot() {
		return root;
	}
	
	private Integer depth(Node<T> node) {
		if( node == null ) {
			return 0;
		}
		
		if( node.isLeaf() ) {
			return 0;
		}
		
		Integer left = 1 + depth(node.getLeft());
		Integer right = 1 + depth(node.getRight());
		
		return Math.max(left, right);
	}

	private Node<T> maximum(Node<T> node) {
		if( node.getRight() == null ) {
			return node;
		}
		
		return maximum(node.getRight());
	}
	
	private Node<T> insert(Node<T> node, Node<T> value) {
		if( node == null ) {
			return value;
		}
		
		if( node.getData().compareTo(value.getData()) >= 0 ) {
			node.setLeft( insert(node.getLeft(), value) );
		} else {
			node.setRight( insert(node.getRight(), value) );
		}	
		
		return node;
	}

	private Node<T> lookup(Node<T> node, T data) {
		if( node == null ) {
			return null;
		}
		if( node.getData().equals(data) ) {
			return node;
		}
		
		if( node.getData().compareTo(data) >= 0 ) {
			return lookup(node.getLeft(), data);
		} else {
			return lookup(node.getRight(), data);
		}
	}
	
	private void preOrder(Node<T> node) {
		if(node == null) {
			return;
		}
		
		proccessNode(node);
		preOrder(node.getLeft());
		preOrder(node.getRight());
	}

	private void postOrder(Node<T> node) {
		if(node == null) {
			return;
		}
		
		postOrder(node.getLeft());
		postOrder(node.getRight());
		proccessNode(node);
	}

	private void inOrder(Node<T> node) {
		if(node == null) {
			return;
		}
		
		inOrder(node.getLeft());
		proccessNode(node);
		inOrder(node.getRight());
	}

	public Boolean isValid() {
		//works only for Numbers
		return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	@SuppressWarnings("unchecked")
	private Boolean isValid(Node<T> node, Integer min, Integer max) {
		if( node == null ) {
			return true;
		}
		if(!node.inRange((T) min, (T) max)) {
			return false;
		}
		
		return isValid(node.getLeft(), min, (Integer) node.getData()) && isValid(node.getRight(), (Integer) node.getData(), max);
	}

}
