package datastructures.trees;

import java.util.Comparator;

import datastructures.base.tree.ColorRedBlackNode;
import datastructures.base.tree.RedBlackNode;

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
		boolean isParentALeftChild = grandParent.getLeft() == parent;
		RedBlackNode<T> uncle = isParentALeftChild ? grandParent.getRight() : grandParent.getLeft();
		
		if(uncle.isRed()) {
			// if uncle and parent are red, then both of them can turn black and their grandparent becomes red
			if(grandParent != null) {
				grandParent.turnRed();
			}
			uncle.turnBlack();
			parent.turnBlack();
			rebalance(grandParent);
		} else {
//			boolean middleChild = 
		}
		
	}

	private RedBlackNode<T> insert(RedBlackNode<T> parent, RedBlackNode<T> head, RedBlackNode<T> element) {
		if(head == null) {
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
	
}
