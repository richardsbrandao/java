package datastructures.maps;

import java.util.function.Function;

import datastructures.base.Map;
import datastructures.base.map.LinkedHashMapNode;

public class LinkedHashMap<K, V> implements Map<K, V> {

	private int buckets;
	private float loadFactor;
	private int size;
	private LinkedHashMapNode<K, V>[] table;

	private LinkedHashMapNode<K, V> head;
	private LinkedHashMapNode<K, V> tail;

	public LinkedHashMap() {
		this(16, .75f);
	}

	@SuppressWarnings("unchecked")
	public LinkedHashMap(int buckets, float loadFactor) {
		this.buckets = buckets;
		this.loadFactor = loadFactor;
		this.size = 0;
		this.table = (LinkedHashMapNode<K, V>[]) new LinkedHashMapNode[buckets];
	}

	@Override
	// Time: O(1) - Space: O(1)
	public V get(K key) {
		return getOrDefault(key, null);
	}

	private LinkedHashMapNode<K, V> getLinearBucketByKey(LinkedHashMapNode<K, V> linkedHashMapNode, K key) {
		if(linkedHashMapNode == null) {
			return null;
		}
		if(linkedHashMapNode.equalKey(key)) {
			return linkedHashMapNode;
		}
		return getLinearBucketByKey(linkedHashMapNode.getNext(), key);
	}

	@Override
	// Time: O(1) - Space: O(1)
	public V getOrDefault(K key, V defaultValue) {
		int bucket = findRightBucket(key);
		LinkedHashMapNode<K, V> node = getLinearBucketByKey(this.table[bucket], key);
		return node != null ? node.getValue() : defaultValue;
	}

	@Override
	// Time: O(1) - Space: O(1)
	public boolean containsKey(K key) {
		return get(key) != null;
	}

	@Override
	// Time: O(n) - Space: O(1)
	public boolean containsValue(V value) {
		return containsValue(0, value);
	}

	private boolean containsValue(int bucketIndex, V value) {
		if(bucketIndex >= this.table.length) {
			return false;
		}
		if(this.table[bucketIndex] == null) {
			return containsValue(++bucketIndex, value);
		}
		if(getLinearBucketByValue(this.table[bucketIndex], value) != null) {
			return true;
		}
		return containsValue(++bucketIndex, value);
	}

	private LinkedHashMapNode<K, V> getLinearBucketByValue(LinkedHashMapNode<K, V> linkedHashMapNode, V value) {
		if(linkedHashMapNode == null) {
			return null;
		}
		
		if(linkedHashMapNode.equalValue(value)) {
			return linkedHashMapNode;
		}
		
		return getLinearBucketByValue(linkedHashMapNode.getNext(), value);
	}

	@Override
	// Time: O(1) - Space: O(n)
	// Worst Case: Time: O(n) - Space: O(n)
	public boolean put(K key, V value) {
		LinkedHashMapNode<K, V> newNode = new LinkedHashMapNode<K, V>(key, value, this.tail, null);

		if(isFull()) {
			resize();
		}

		linkNextInBucket(key, newNode);
		
		return true;
	}

	private void linkInsertionOrder(LinkedHashMapNode<K, V> newNode) {
		LinkedHashMapNode<K, V> oldTail = this.tail;

		if(oldTail != null) {
			oldTail.setAfter(newNode);
		} else {
			head = newNode;
		}

		this.tail = newNode;
	}

	private void linkNextInBucket(K key, LinkedHashMapNode<K, V> newNode) {
		int bucket = findRightBucket(key);
		if( this.table[bucket] != null ) {
			putNewNodeOnHashTable(this.table[bucket], newNode);
		} else {
			this.table[bucket] = newNode;
			linkInsertionOrder(newNode);
			size++;
		}
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		this.buckets *= 2;
		this.size = 0;

		LinkedHashMapNode<K, V>[] oldTable = this.table;
		this.table = (LinkedHashMapNode<K, V>[]) new LinkedHashMapNode[this.buckets];
		for(int i = 0; i < oldTable.length; i++) {
			if(oldTable[i] == null) {
				continue;
			}
			
			LinkedHashMapNode<K, V> node = oldTable[i];
			put(node.getKey(), node.getValue());
			while(node.hasNext()) {
				node = node.getNext();
				put(node.getKey(), node.getValue());
			}
		}
	}

	private boolean isFull() {
		return size > buckets * loadFactor;
	}

	private void putNewNodeOnHashTable(LinkedHashMapNode<K, V> linkedHashMapNode, LinkedHashMapNode<K, V> newNode) {
		if(linkedHashMapNode.equalKey(newNode.getKey())) {
			linkedHashMapNode.update(newNode);
			return;
		}
		
		if(!linkedHashMapNode.hasNext()) {
			linkedHashMapNode.setNext(newNode);
			linkInsertionOrder(newNode);
			size++;
			return;
		}

		putNewNodeOnHashTable(linkedHashMapNode.getNext(), newNode);
	}

	private int findRightBucket(K key) {
		if(key == null) {
			return 0;
		}
		return Math.abs(key.hashCode())%this.buckets;
	}

	@Override
	public boolean remove(K key) {
		int bucket = findRightBucket(key);
		return removeLinkedBucketByKey(null, this.table[bucket], key, (LinkedHashMapNode<K, V> node) -> node.equalKey(key));
	}

	// Time: O(1) - Space: O(1)
	private boolean removeLinkedBucketByKey(LinkedHashMapNode<K, V> lastNode, LinkedHashMapNode<K, V> node, K key, Function<LinkedHashMapNode<K, V>, Boolean> match) {
		if(node == null) {
			return false;
		}

		if(!match.apply(node)) {
			return removeLinkedBucketByKey(node, node.getNext(), key, match);
		}
		
		removeFromLinkedInsertion(node);
		removeFromBucket(lastNode, node, key);
		
		size--;
		return true;
	}

	private void removeFromBucket(LinkedHashMapNode<K, V> lastNode, LinkedHashMapNode<K, V> node, K key) {
		if(lastNode != null) { // middle
			lastNode.setNext(node.getNext());
			node = null;
		} else { // first element
			int bucket = findRightBucket(key);
			if(this.table[bucket].hasNext()) { // not unique
				this.table[bucket] = this.table[bucket].getNext(); 
			} else { // unique
				this.table[bucket] = null;
			}
		}
	}

	private void removeFromLinkedInsertion(LinkedHashMapNode<K, V> node) {
		LinkedHashMapNode<K, V> beforeNode = node.getBefore();
		LinkedHashMapNode<K, V> afterNode = node.getAfter();
		if(beforeNode != null) {
			beforeNode.setAfter(node.getAfter());
		} else {
			head = node.getAfter();
		}
		if(afterNode != null) {
			afterNode.setBefore(beforeNode);
		} else {
			tail = beforeNode;
		}
	}

	@Override
	// Time: O(1) - Space: O(1)
	public boolean removeIfKeyEqualValue(K key, V value) {
		int bucket = findRightBucket(key);
		return removeLinkedBucketByKey(null, this.table[bucket], key, (LinkedHashMapNode<K, V> node) -> node.equalKeyValue(key, value));
	}

	@Override
	// Time: O(1) - Space: O(1)
	public int size() {
		return size;
	}

	@Override
	// Time: O(1) - Space: O(1)
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	// Time: O(n) - Space: O(n)
	public Object[] keys() {
		Object[] keys = new Object[size];
		return linkAndGetFrom(head, keys, 0, (LinkedHashMapNode<K,V> node) -> node.getKey());
	}

	private Object[] linkAndGetFrom(LinkedHashMapNode<K, V> from, Object[] elements, int index, Function<LinkedHashMapNode<K,V>, Object> getFromNode) {
		if(from == null) {
			return elements;
		}
		
		elements[index++] = getFromNode.apply(from);
		return linkAndGetFrom(from.getAfter(), elements, index, getFromNode);
	}

	@Override
	// Time: O(n) - Space: O(n)
	public Object[] values() {
		Object[] keys = new Object[size];
		return linkAndGetFrom(head, keys, 0, (LinkedHashMapNode<K,V> node) -> node.getValue());
	}
}
