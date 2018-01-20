package datastructures.sets;

import datastructures.base.Set;
import datastructures.maps.LinkedHashMap;

public class LinkedHashSet<T> implements Set<T> {

	private static final Boolean PRESENT = Boolean.TRUE;
	private LinkedHashMap<T, Boolean> map;
	private int buckets;
	private float loadFactor;

	public LinkedHashSet() {
		this(16, .75f);
	}
	
	public LinkedHashSet(int buckets, float loadFactor) {
		this.buckets = buckets;
		this.loadFactor = loadFactor;
		this.map = new LinkedHashMap<T, Boolean>(buckets, loadFactor);
	}
	
	@Override
	public void add(T element) {
		this.map.put(element, PRESENT);
	}

	@Override
	public void remove(T element) {
		this.map.remove(element);
	}

	@Override
	public void clear() {
		this.map = new LinkedHashMap<>(buckets, loadFactor);
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


}
