package datastructures.maps;

import java.util.function.Consumer;

import datastructures.base.List;
import datastructures.base.NavigableMap;
import datastructures.base.map.Entry;
import datastructures.base.map.RedBlackKeyValueNode;
import datastructures.base.tree.ColorRedBlackNode;
import datastructures.lists.Linked;

public class TreeMap<K extends Comparable<K>, V> implements NavigableMap<K, V> {

	private RedBlackKeyValueNode<K, V> root;
	private int size;
	
	@Override
	public V get(K key) {
		return getOrDefault(key, null);
	}

	@Override
	public V getOrDefault(K key, V defaultValue) {
		if(key == null) {
			throw new NullPointerException();
		}
		V value = get(root, key);
		return value != null ? value : defaultValue;
	}

	private V get(RedBlackKeyValueNode<K, V> head, K key) {
		if(head.isLeaf()) {
			return null;
		}
		
		if(head.equalKey(key)) {
			return head.getValue();
		}
		
		if(head.greaterThanKey(key)) {
			return get(head.getLeft(), key);
		} else {
			return get(head.getRight(), key);
		}
	}

	@Override
	public boolean containsKey(K key) {
		return get(key) != null;
	}

	@Override
	public boolean containsValue(V value) {
		return getByValue(root, value);
	}

	private boolean getByValue(RedBlackKeyValueNode<K, V> head, V value) {
		if(head.isLeaf()) {
			return false;
		}
		
		if(head.equalValue(value)) {
			return true;
		}
		
		boolean founded = getByValue(head.getLeft(), value);
		if(founded) return true;
		founded = getByValue(head.getRight(), value);
		return founded;
	}

	@Override
	public boolean put(K key, V value) {
		if(key == null) {
			throw new NullPointerException();
		}
		if(root == null) {
			this.root = new RedBlackKeyValueNode<K, V>(key, value, ColorRedBlackNode.BLACK);
			this.size = 1;
			return true;
		}
		RedBlackKeyValueNode<K, V> newNode = new RedBlackKeyValueNode<K, V>(key, value);
		insert(this.root, null, newNode);
		balanceAfterInsert(newNode);
		return true;
	}

	private void balanceAfterInsert(RedBlackKeyValueNode<K, V> newNode) {
		if(newNode.isRoot()) {
			newNode.turnBlack();
			return;
		}
		
		RedBlackKeyValueNode<K, V> parent = newNode.getParent();

		if(parent.isBlack()) {
			return;
		}
		
		
		RedBlackKeyValueNode<K, V> grandParent = parent.getParent();
		RedBlackKeyValueNode<K, V> uncle = newNode.getUncle(grandParent);
		
		
		if(uncle.isRed()) {
			if(grandParent != null) {
				grandParent.turnRed();
			}
			
			uncle.turnBlack();
			parent.turnBlack();
			
			balanceAfterInsert(grandParent);
		} else {
			if(newNode.isRightChildFrom(parent) && parent.isLeftChildFrom(grandParent)) {
				rotateLeft(parent);
				
				newNode = newNode.getLeft();
				parent = newNode.getParent();
				grandParent = newNode.getParent().getParent();
				uncle = newNode.getUncle(grandParent);
			} else if(newNode.isLeftChildFrom(parent) && parent.isRightChildFrom(grandParent)) {
				rotateRight(parent);
				
				newNode = newNode.getRight();
				parent = newNode.getParent();
				grandParent = newNode.getParent().getParent();
				uncle = newNode.getUncle(grandParent);
			}
			
			if(parent.isRed() && uncle.isBlack()) {
				parent.turnBlack();
				grandParent.turnRed();
				
				if(newNode.isLeftChildFrom(parent) && parent.isLeftChildFrom(grandParent)) {
					rotateRight(grandParent);
				} else if(newNode.isRightChildFrom(parent) && parent.isRightChildFrom(grandParent)) {
					rotateLeft(grandParent);
				}
			}
		}
	}

	private void rotateRight(RedBlackKeyValueNode<K, V> node) {
		RedBlackKeyValueNode<K, V> parent = node.getParent();
		RedBlackKeyValueNode<K, V> leftChild = node.getLeft();
		RedBlackKeyValueNode<K, V> upperGrandson = leftChild.getRight(); // right child from left child
		
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

	private void rotateLeft(RedBlackKeyValueNode<K, V> node) {
		RedBlackKeyValueNode<K, V> parent = node.getParent();
		RedBlackKeyValueNode<K, V> rightChild = node.getRight();
		RedBlackKeyValueNode<K, V> lowerGrandson = rightChild.getLeft(); // left child from right child
		
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

	private RedBlackKeyValueNode<K, V> insert(RedBlackKeyValueNode<K, V> head, RedBlackKeyValueNode<K, V> parent, RedBlackKeyValueNode<K, V> newNode) {
		if(head.isLeaf()) {
			size++;
			newNode.setParent(parent);
			return newNode;
		}
		
		if(head.equalKey(newNode.getKey())) {
			head.setValue(newNode.getValue());
			return head;
		}
		
		if( head.greaterThan(newNode) ) {
			head.setLeft( insert(head.getLeft(), head, newNode) );
		} else {
			head.setRight( insert(head.getRight(), head, newNode) );
		}
		return head;
	}

	@Override
	public boolean remove(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeIfKeyEqualValue(K key, V value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public Object[] keys() {
		List<Object> elements = new Linked<>();
		return inOrder(root, elements, (node) -> elements.add(node.getKey())).toArray();
	}

	private List<Object> inOrder(RedBlackKeyValueNode<K, V> head, List<Object> collectedElements, Consumer<RedBlackKeyValueNode<K, V>> collector) {
		if(head == null || head.isLeaf()) {
			return collectedElements;
		}
		
		inOrder(head.getLeft(), collectedElements, collector);
		collector.accept(head);
		inOrder(head.getRight(), collectedElements, collector);
		
		return collectedElements;
	}

	@Override
	public Object[] values() {
		List<Object> elements = new Linked<>();
		return inOrder(root, elements, (node) -> elements.add(node.getValue())).toArray();
	}

	@Override
	public Entry<K, V> highest(K key) {
		if(key == null)
			throw new NullPointerException();
		if(root == null)
			return null;
		RedBlackKeyValueNode<K,V> highest = highest(root, key);
		if(highest == null) {
			return null;
		}
		return new Entry<K, V>(highest.getKey(), highest.getValue());
	}

	private RedBlackKeyValueNode<K, V> highest(RedBlackKeyValueNode<K, V> head, K key) {
		if(head.greaterThanKey(key)) {
			if(head.getLeft().isLeaf()) {
				return head;
			} else {
				return highest(head.getLeft(), key);
			}
		} else {
			if(head.getRight().isLeaf()) {
				RedBlackKeyValueNode<K, V> parent = head.getParent();
				while(!head.isRoot() && head == parent.getRight()) {
					head = parent;
                    parent = parent.getParent();
				}
				return parent;
			} else {
				return highest(head.getRight(), key);
			}
		}
	}

	@Override
	public Entry<K, V> lowest(K key) {
		if(key == null) 
			throw new NullPointerException();
		if(root == null)
			return null;
		RedBlackKeyValueNode<K,V> lowest = lowest(root, key);
		if(lowest == null)
			return null;
		return new Entry<K, V>(lowest.getKey(), lowest.getValue());
	}

	private RedBlackKeyValueNode<K, V> lowest(RedBlackKeyValueNode<K, V> head, K key) {
		if(head.greaterThanKey(key)) {
			if(head.getLeft().isLeaf()) {
				RedBlackKeyValueNode<K, V> parent = head.getParent();
				while(!head.isRoot() && head == parent.getLeft()) {
					head = parent;
                    parent = parent.getParent();
				}
				return parent;
			} else {
				return lowest(head.getLeft(), key);
			}
		} else {
			if(head.getRight().isLeaf()) {
				return head;
			} else {
				return lowest(head.getRight(), key);
			}
		}
	}

	@Override
	public NavigableMap<K, V> subMap(K from, K to, boolean inclusive) {
		if(from.compareTo(to) > 0) {
			throw new IllegalArgumentException();
		}
		NavigableMap<K, V> subMap = new TreeMap<K, V>();
		if(root == null) {
			return subMap;
		}
		return subMap(root, from, to, inclusive, subMap);
	}

	private NavigableMap<K, V> subMap(RedBlackKeyValueNode<K, V> head, K from, K to, boolean inclusive, NavigableMap<K, V> subMap) {
		if(head.isLeaf()) {
			return subMap;
		}
		
		subMap(head.getLeft(), from, to, inclusive, subMap);
		if(head.keyBetween(from, to, inclusive)) {
			subMap.put(head.getKey(), head.getValue());
		}
		subMap(head.getRight(), from, to, inclusive, subMap);
		
		return subMap;
	}

	@Override
	public NavigableMap<K, V> tailMap(K from, boolean inclusive) {
		NavigableMap<K, V> collectedElements = new TreeMap<K, V>();
		if(isEmpty()) {
			return collectedElements;
		}
		Consumer<RedBlackKeyValueNode<K, V>> collector = (node) -> {
			if( (!inclusive && node.greaterThanKey(from)) ^ (inclusive && node.greaterThanOrEqualKey(from)) ) {
				collectedElements.put(node.getKey(), node.getValue());
			}
		};
		return inOrder(root, collectedElements, collector);
	}

	private NavigableMap<K, V> inOrder(RedBlackKeyValueNode<K, V> head, NavigableMap<K, V> collectedElements, Consumer<RedBlackKeyValueNode<K, V>> collector) {
		if(head.isLeaf()) {
			return collectedElements;
		}
		inOrder(head.getLeft(), collectedElements, collector);
		collector.accept(head);
		inOrder(head.getRight(), collectedElements, collector);
		return collectedElements;
	}

	@Override
	public NavigableMap<K, V> headMap(K from, boolean inclusive) {
		NavigableMap<K, V> collectedElements = new TreeMap<K, V>();
		if(isEmpty()) {
			return collectedElements;
		}
		Consumer<RedBlackKeyValueNode<K, V>> collector = (node) -> {
			if( (!inclusive && node.lessThanKey(from)) ^ (inclusive && node.lessThanOrEqualKey(from)) ) {
				collectedElements.put(node.getKey(), node.getValue());
			}
		};
		return inOrder(root, collectedElements, collector);
	}

	@Override
	public String toString() {
		StringBuilder draw = new StringBuilder();
		drawElement(draw, root, 0);
		return draw(draw, root, 1).toString();
	}

	private StringBuilder draw(StringBuilder draw, RedBlackKeyValueNode<K, V> head, Integer level) {
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
	private void drawElement(StringBuilder draw, RedBlackKeyValueNode<K, V> head, Integer level) {
		for(int i = 0; i < level; i++) {
			draw.append(VERTICAL_PREFIX);
		}
		
		draw.append(PREFIX)
			.append("[Key=").append(head.getKey())
			.append(", Value=").append(head.getValue())
			.append(", Color=").append(head.getColor())
			.append("]").append("\n");
	}

}
