package datastructures.maps;

import datastructures.base.NavigableMap;
import datastructures.base.map.Entry;
import datastructures.map.RedBlackEntryNode;

public class TreeMap<K, V> implements NavigableMap<K, V> {

	private RedBlackEntryNode<K, V> root;
	
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
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public Entry<K, V> highest(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> lowest(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> afterEntry(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entry<K, V> beforeEntry(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableMap<K, V> subMap(K from, K to, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableMap<K, V> tailMap(K from, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableMap<K, V> headMap(K from, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableMap<K, V> reverse() {
		// TODO Auto-generated method stub
		return null;
	}

}
