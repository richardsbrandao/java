package datastructures.map;

import datastructures.base.tree.ColorRedBlackNode;

public class RedBlackEntryNode<K, V> {

	private ColorRedBlackNode color;
	private K key;
	private V value;
	private RedBlackEntryNode<K, V> left;
	private RedBlackEntryNode<K, V> right;
	
	public ColorRedBlackNode getColor() {
		return color;
	}
	public K getKey() {
		return key;
	}
	public V getValue() {
		return value;
	}
	public RedBlackEntryNode<K, V> getLeft() {
		return left;
	}
	public RedBlackEntryNode<K, V> getRight() {
		return right;
	}
	
}
