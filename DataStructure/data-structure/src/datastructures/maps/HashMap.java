package datastructures.maps;

import java.util.function.Function;

import datastructures.base.Map;
import datastructures.base.map.HashNode;

public class HashMap<K,V> implements Map<K,V> {
	private int size;
	private int buckets;
	private float loadFactor;
	private HashNode<K, V>[] table;

	public HashMap() {
		this(16, .75f);
	}
	
	@SuppressWarnings("unchecked")
	public HashMap(int buckets, float loadFactor) {
		this.buckets = buckets;
		this.loadFactor = loadFactor;
		this.table = (HashNode<K, V>[]) new HashNode[buckets];
		this.size = 0;
	}
	
	@Override
	public V get(K key) {
		return getOrDefault(key, null);
	}

	private HashNode<K, V> getLinearBucketByKey(HashNode<K, V> bucketTable, K key) {
		if(bucketTable == null) {
			return null;
		}
		if(bucketTable.equalKey(key)) {
			return bucketTable;
		}
		return getLinearBucketByKey(bucketTable.getNext(), key);
	}

	@Override
	public V getOrDefault(K key, V defaultValue) {
		int bucket = findRightBucket(key);
		HashNode<K, V> node = getLinearBucketByKey(this.table[bucket], key);
		return node != null ? node.getValue() : defaultValue;
	}

	@Override
	public boolean containsKey(K key) {
		int bucket = findRightBucket(key);
		return getLinearBucketByKey(this.table[bucket], key) != null;
	}

	@Override
	public boolean containsValue(V value) {
		if(isEmpty()) {
			return false;
		}
		return containsValue(0, value);
	}

	private boolean containsValue(int bucketIndex, V value) {
		if(bucketIndex == size) {
			return false;
		}
		if(this.table[bucketIndex] != null && this.table[bucketIndex].equalValue(value)) {
			return true;
		}
		if(getLinearBucketByValue(this.table[bucketIndex], value) != null) {
			return true;
		}
		return containsValue(++bucketIndex, value);
	}

	private HashNode<K, V> getLinearBucketByValue(HashNode<K, V> hashNode, V value) {
		if(hashNode == null){
			return null;
		}
		if(hashNode.equalValue(value)) {
			return hashNode;
		}
		return getLinearBucketByValue(hashNode.getNext(), value);
	}

	@Override
	public boolean put(K key, V value) {
		HashNode<K, V> newNode = new HashNode<K, V>(key, value);
		
		if(isFull()) {
			resize();
		}
		
		int bucket = findRightBucket(key);
		if( this.table[bucket] == null ) {
			this.table[bucket] = newNode;
		} else {
			HashNode<K, V> linkedNode = lastLinkedNodeOnBucket(this.table[bucket]);
			linkedNode.setNext(newNode);
		}
		size++;
		return true;
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		this.buckets *= 2;
		this.size = 0;
		
		HashNode<K, V>[] oldTable = this.table;
		this.table = (HashNode<K, V>[]) new HashNode[this.buckets];
		for(int i = 0; i < oldTable.length; i++) {
			if(oldTable[i] != null) {
				HashNode<K, V> node = oldTable[i];
				put(node.getKey(), node.getValue());
				while(node.hasNext()) {
					node = node.getNext();
					put(node.getKey(), node.getValue());
				}
			}
		}
	}

	private boolean isFull() {
		return size > buckets * loadFactor;
	}

	private int findRightBucket(K key) {
		return key.hashCode() % this.buckets;
	}

	private HashNode<K, V> lastLinkedNodeOnBucket(HashNode<K, V> bucketTable) {
		if(!bucketTable.hasNext()) {
			return bucketTable;
		}
		return lastLinkedNodeOnBucket(bucketTable.getNext());
	}

	@Override
	public boolean remove(K key) {
		int bucket = findRightBucket(key);
		return removeLinkedBucketByKey(null, this.table[bucket], key, (HashNode<K, V> node) -> node.equalKey(key));
	}

	private boolean removeLinkedBucketByKey(HashNode<K, V> lastNode, HashNode<K, V> currentNode, K key, Function<HashNode<K, V>, Boolean> matchNode) {
		if(currentNode == null) { // no more node in bucket
			return false;
		}
		
		if(! matchNode.apply(currentNode)) { // node not found!
			return removeLinkedBucketByKey(currentNode, currentNode.getNext(), key, matchNode); 
		}
		
		if(lastNode != null) { // not first element bucket
			if (currentNode.hasNext()) { // middle in bucket
				lastNode.setNext(currentNode.getNext());
				currentNode = null;
			} else { // the last one
				lastNode.setNext(null);
				currentNode = null;
			}
			
		} else { 
			int bucket = findRightBucket(key);
			if(currentNode.hasNext()) {
				this.table[bucket] = currentNode.getNext(); 
			} else { // the only one
				this.table[bucket] = null;
			}
		}
		
		size--;
		return true;
	}

	@Override
	public boolean removeIfKeyEqualValue(K key, V value) {
		int bucket = findRightBucket(key);
		return removeLinkedBucketByKey(null, this.table[bucket], key, (HashNode<K, V> node) -> node.equalKeyValue(key, value));
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public K[] keys() {
		K[] keys = (K[]) new Object[size];
		int foundedItems = 0;
		for(int i = 0; i < this.table.length; i++) {
			if(this.table[i] != null) {
				HashNode<K, V> node = this.table[i];
				keys[foundedItems++] = node.getKey();
				while(node.hasNext()) {
					node = node.getNext();
					keys[foundedItems++] = node.getKey();
				}
			}
		}
		return keys;
	}

	@Override
	@SuppressWarnings("unchecked")
	public V[] values() {
		V[] values = (V[]) new Object[size];
		int foundedItems = 0;
		for(int i = 0; i < this.table.length; i++) {
			if(this.table[i] != null) {
				HashNode<K, V> node = this.table[i];
				values[foundedItems++] = node.getValue();
				while(node.hasNext()) {
					node = node.getNext();
					values[foundedItems++] = node.getValue();
				}
			}
		}
		return values;
	}

}
