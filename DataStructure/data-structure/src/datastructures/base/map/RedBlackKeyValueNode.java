package datastructures.base.map;

import datastructures.base.tree.ColorRedBlackNode;

public class RedBlackKeyValueNode<K extends Comparable<K>, V> {

	private RedBlackKeyValueNode<K, V> parent;
	private ColorRedBlackNode color;
	private K key;
	private V value;
	private RedBlackKeyValueNode<K, V> left;
	private RedBlackKeyValueNode<K, V> right;

	public RedBlackKeyValueNode(K key, V value) {
		this(key, value, ColorRedBlackNode.RED);
	}

	@SuppressWarnings("unchecked")
	public RedBlackKeyValueNode(K key, V value, ColorRedBlackNode color) {
		this.key = key;
		this.value = value;
		this.color = color;
		if(key != null) {
			this.left = RedBlackKeyValueNode.newLeaf(this);
			this.right = RedBlackKeyValueNode.newLeaf(this);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static RedBlackKeyValueNode newLeaf(RedBlackKeyValueNode parent) {
		RedBlackKeyValueNode leaf = new RedBlackKeyValueNode(null, null, ColorRedBlackNode.BLACK);
		leaf.setParent(parent);
		return leaf;
	}

	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}

	public ColorRedBlackNode getColor() {
		return color;
	}

	public RedBlackKeyValueNode<K, V> getLeft() {
		return left;
	}

	public void setLeft(RedBlackKeyValueNode<K, V> left) {
		this.left = left;
	}

	public RedBlackKeyValueNode<K, V> getRight() {
		return right;
	}

	public void setRight(RedBlackKeyValueNode<K, V> right) {
		this.right = right;
	}

	public RedBlackKeyValueNode<K, V> getParent() {
		return parent;
	}

	public void setParent(RedBlackKeyValueNode<K, V> parent) {
		this.parent = parent;
	}

	public boolean greaterThan(RedBlackKeyValueNode<K, V> anotherNode) {
		return greaterThanKey(anotherNode.key);
	}

	@Override
	public String toString() {
		return "RedBlackKeyValueNode [color=" + color + ", key=" + key + ", value=" + value + "]";
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

	public RedBlackKeyValueNode<K, V> getUncle(RedBlackKeyValueNode<K, V> grandParent) {
		boolean isParentALeftChild = grandParent.getLeft() == parent;
		return isParentALeftChild ? grandParent.getRight() : grandParent.getLeft();
	}

	public boolean isLeaf() {
		return key == null;
	}
	
	public boolean isLeftChildFrom(RedBlackKeyValueNode<K, V> possibleParent) {
		return possibleParent.left == this;
	}
	
	public boolean isRightChildFrom(RedBlackKeyValueNode<K, V> possibleParent) {
		return possibleParent.right == this;
	}

	public boolean equalKey(K key) {
		return this.key == key;
	}

	public boolean greaterThanKey(K key) {
		return this.key.compareTo(key) > 0;
	}

	public boolean equalValue(V value) {
		if(value == null && this.value == null) {
			return true;
		}
		return this.value.equals(value);
	}

	public void setValue(V value) {
		this.value = value;
	}

	public boolean keyBetween(K from, K to, boolean inclusive) {
		if(inclusive)
			return greaterThanOrEqualKey(from) && lessThanOrEqualKey(to);
		else
			return greaterThanKey(from) && lessThanKey(to);
	}

	public boolean lessThanKey(K otherKey) {
		return key.compareTo(otherKey) < 0;
	}

	public boolean lessThanOrEqualKey(K otherKey) {
		return key.compareTo(otherKey) <= 0;
	}

	public boolean greaterThanOrEqualKey(K otherKey) {
		return key.compareTo(otherKey) >= 0;
	}

}
