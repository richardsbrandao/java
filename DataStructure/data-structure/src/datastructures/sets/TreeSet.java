package datastructures.sets;

import datastructures.base.NavigableMap;
import datastructures.base.NavigableSet;
import datastructures.base.map.Entry;
import datastructures.maps.TreeMap;

public class TreeSet<T extends Comparable<T>> implements NavigableSet<T> {

	private NavigableMap<T, Boolean> map;
	private static final Boolean PRESENT = Boolean.TRUE;
	
	public TreeSet() {
		this.map = new TreeMap<T, Boolean>();
	}
	
	@SuppressWarnings("unchecked")
	public TreeSet(NavigableMap<T, Boolean> subMap) {
		this();
		if(!subMap.isEmpty()) {
			for(Object key : subMap.keys()) {
				add((T) key);
			}
		}
	}

	@Override
	public void add(T element) {
		this.map.put(element, PRESENT);
	}

	@Override
	public void remove(T element) {
		
	}

	@Override
	public void clear() {
		this.map = new TreeMap<T, Boolean>();
	}

	@Override
	public int size() {
		return this.map.size();
	}

	@Override
	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	@Override
	public boolean contains(T element) {
		return this.map.containsKey(element);
	}

	@Override
	public Object[] elements() {
		return this.map.keys();
	}

	@Override
	public T highest(T key) {
		Entry<T, Boolean> highest = this.map.highest(key);
		return highest != null ? highest.getKey() : null; 
	}

	@Override
	public T lowest(T key) {
		Entry<T, Boolean> lowest = this.map.lowest(key);
		return lowest != null ? lowest.getKey() : null;
	}

	@Override
	public NavigableSet<T> subSet(T from, T to, boolean inclusive) {
		NavigableMap<T, Boolean> subMap = this.map.subMap(from, to, inclusive);
		return new TreeSet<T>(subMap);
	}

	@Override
	public NavigableSet<T> tailSet(T from, boolean inclusive) {
		NavigableMap<T,Boolean> tailMap = this.map.tailMap(from, inclusive);
		return new TreeSet<T>(tailMap);
	}

	@Override
	public NavigableSet<T> headSet(T from, boolean inclusive) {
		NavigableMap<T,Boolean> headMap = this.map.headMap(from, inclusive);
		return new TreeSet<T>(headMap);
	}

}
