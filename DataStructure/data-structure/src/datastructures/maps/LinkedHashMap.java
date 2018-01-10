package datastructures.maps;

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
	public V get(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getOrDefault(K key, V defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsKey(K key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean put(K key, V value) {
		LinkedHashMapNode<K, V> newNode = new LinkedHashMapNode<K, V>(key, value, this.tail, null);

		if(isFull()) {
			resize();
		}

		int bucket = findRightBucket(key);
		if( this.table[bucket] != null ) {
			LinkedHashMapNode<K, V> lastNode = lastLinkedNodeOnBucket(this.table[bucket]);
			lastNode.setNext(newNode);
		} else {
			this.table[bucket] = newNode;
		}

		LinkedHashMapNode<K, V> oldTail = this.tail;

		if(oldTail != null) {
			oldTail.setAfter(newNode);
		} else {
			head = newNode;
		}

		this.tail = newNode;
		size++;
		return true;
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		this.buckets *= 2;
		this.size = 0;

		LinkedHashMapNode<K, V>[] oldTable = this.table;
		this.table = (LinkedHashMapNode<K, V>[]) new LinkedHashMapNode[this.buckets];
		for(int i = 0; i < oldTable.length; i++) {
			if(oldTable[i] != null) {
				LinkedHashMapNode<K, V> node = oldTable[i];
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

	private LinkedHashMapNode<K, V> lastLinkedNodeOnBucket(LinkedHashMapNode<K, V> linkedHashMapNode) {
		if(!linkedHashMapNode.hasNext()) {
			return linkedHashMapNode;
		}

		return lastLinkedNodeOnBucket(linkedHashMapNode.getNext());
	}

	private int findRightBucket(K key) {
		return key.hashCode()%this.buckets;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] values() {
		// TODO Auto-generated method stub
		return null;
	}



}
