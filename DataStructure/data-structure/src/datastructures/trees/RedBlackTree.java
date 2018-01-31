package datastructures.trees;

import datastructures.base.tree.ColorRedBlackNode;
import datastructures.base.tree.RedBlackNode;

/**
 * Legend Tree
 * 
 * - -> Node
 * * -> newNode
 * + -> Parent
 * @ -> GrandParent
 * 
 */

public class RedBlackTree<T extends Comparable<T>> {

	private RedBlackNode<T> root;
	
	public RedBlackTree(T element) {
		this.root = new RedBlackNode<T>(element, ColorRedBlackNode.BLACK);
	}
	
	public void insert(T element) {
		RedBlackNode<T> newNode = new RedBlackNode<T>(element);
		insert(null, root, newNode);
		rebalance(newNode);
	}

	private void rebalance(RedBlackNode<T> newNode) {
		if(newNode.isRoot()) {
			newNode.turnBlack(); // Root node is always BLACK
			return;
		}
		
		RedBlackNode<T> parent = newNode.getParent();
		
		if(parent.isBlack()) {
			return; 
		}
		
		// Parent cannot be RED
		RedBlackNode<T> grandParent = parent.getParent();
		RedBlackNode<T> uncle = newNode.getUncle(grandParent);
		
		if(uncle.isRed()) {
			// if uncle and parent are red, then both of them can turn black and their grandparent becomes red
			if(grandParent != null) {
				grandParent.turnRed();
			}
			uncle.turnBlack();
			parent.turnBlack();
			
			rebalance(grandParent);
		} else {
			if(newNode == parent.getRight() && parent == grandParent.getLeft()) {
				/**
				 *     @
				 *   +
				 *     *
				 */
				
				rotateLeft(parent);
				
				newNode = newNode.getLeft();
				parent = newNode.getParent();
				grandParent = newNode.getParent().getParent();
				uncle = newNode.getUncle(grandParent);
			} else if(newNode == parent.getLeft() && parent == grandParent.getRight()) {
				/**
				 * @
				 *   +
				 * *  
				 */
				
				rotateRight(parent);
				
				newNode = newNode.getRight();
				parent = newNode.getParent();
				grandParent = newNode.getParent().getParent();
				uncle = newNode.getUncle(grandParent);
			}
			
			if(parent.isRed() && uncle.isBlack()) {
				parent.turnBlack();
				grandParent.turnRed();
				
				if(newNode == parent.getLeft() && parent == grandParent.getLeft()) {
					/**
					 *     @
					 *   +
					 * *
					 */
					
					rotateRight(grandParent);
				} else if(newNode == parent.getRight() && parent == grandParent.getRight()) {
					/**
					 * @
					 *   +
					 *     *
					 */
					
					rotateLeft(grandParent);
				}
			}
		}
		
	}

	private void rotateRight(RedBlackNode<T> node) {
		RedBlackNode<T> parent = node.getParent();
		RedBlackNode<T> leftChild = node.getLeft();
		RedBlackNode<T> upperGrandson = leftChild.getRight(); // right child from left child
		
		leftChild.setRight(node);
		node.setParent(leftChild);
		
		node.setLeft(upperGrandson);
		
		if(upperGrandson != null) {
			upperGrandson.setParent(node);
		}
		
		if(parent != null) { // is not root
			if(node == parent.getLeft()) { // i'm left child!
				parent.setLeft(leftChild);
			} else if(node == parent.getRight()) { // i'm right child!
				parent.setRight(leftChild);
			} else {
				throw new RuntimeException("I'm not a child?!");
			}
			leftChild.setParent(parent);
		} else { // is root
			root = leftChild;
			root.setParent(null);
		}
	}

	private void rotateLeft(RedBlackNode<T> node) {
		RedBlackNode<T> parent = node.getParent();
		RedBlackNode<T> rightChild = node.getRight();
		RedBlackNode<T> lowerGrandson = rightChild.getLeft(); // left child from right child
		
		rightChild.setLeft(node);
		node.setParent(rightChild);
		
		node.setRight(lowerGrandson);
		
		if(lowerGrandson != null) {
			lowerGrandson.setParent(node);
		}
		
		if(parent != null) { // is not root
			if(node == parent.getLeft()) { // i'm left child!
				parent.setLeft(rightChild);
			} else if(node == parent.getRight()) { // i'm right child!
				parent.setRight(rightChild);
			} else {
				throw new RuntimeException("I'm not a child?!");
			}
			rightChild.setParent(parent);
		} else { // is root
			root = rightChild;
			root.setParent(null);
		}
	}

	private RedBlackNode<T> insert(RedBlackNode<T> parent, RedBlackNode<T> head, RedBlackNode<T> element) {
		if(head.isLeaf()) {
			element.setParent(parent);
			return element;
		}
		
		if(head.greaterThan(element)) {
			head.setLeft(insert(head, head.getLeft(), element));
		} else {
			head.setRight(insert(head, head.getRight(), element));
		}
		return head;
	}
	
	@Override
	public String toString() {
		StringBuilder draw = new StringBuilder();
		drawElement(draw, root, 0);
		return draw(draw, root, 1).toString();
	}

	private StringBuilder draw(StringBuilder draw, RedBlackNode<T> head, Integer level) {
		if(head.isLeaf()) {
			return draw;
		}
		
		drawElement(draw, head.getLeft(), level);
		draw(draw, head.getLeft(), level+1);
		
		drawElement(draw, head.getRight(), level);
		draw(draw, head.getRight(), level+1);
		
		return draw;
	}

	private final String PREFIX = " ____";
	private final String VERTICAL_PREFIX = "|   ";
	private void drawElement(StringBuilder draw, RedBlackNode<T> head, Integer level) {
		for(int i = 0; i < level; i++) {
			draw.append(VERTICAL_PREFIX);
		}
		
		draw.append(PREFIX).append("[Element=").append(head.getElement()).append(", Color=").append(head.getColor()).append("]").append("\n");
	}
	
}
