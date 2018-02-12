package datastructures.base;

import datastructures.base.map.Entry;

public interface NavigableMap<K, V> extends Map<K, V> {

	Entry<K, V> highest(K key);
	Entry<K, V> lowest(K key);
	
	NavigableMap<K, V> subMap(K from, K to, boolean inclusive);
	NavigableMap<K, V> tailMap(K from, boolean inclusive);
	NavigableMap<K, V> headMap(K from, boolean inclusive);
}
