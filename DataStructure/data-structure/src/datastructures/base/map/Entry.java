package datastructures.base.map;

import datastructures.maps.TreeMap;

public class Entry<K, V> {
	private K key;
	private V value;
	
	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	public V getValue() {
		return value;
	}
	
	public static void main(String[] args) {
		java.util.TreeMap<Integer, String> tree = new java.util.TreeMap<>();
		tree.put(93, "O"); tree.put(3, "A"); 
		tree.put(7, "B");  tree.put(39, "C"); 
		tree.put(4, "D");  tree.put(95, "E");
		tree.put(33, "F"); tree.put(18, "G");
		tree.put(70, "H"); tree.put(10, "I");
		tree.put(34, "J"); tree.put(86, "L");
		tree.put(24, "M"); tree.put(51, "N");
		
		System.out.println(tree.higherKey(1000));
	}
	
}
