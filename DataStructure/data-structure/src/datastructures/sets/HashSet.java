package datastructures.sets;

import datastructures.base.Set;
import datastructures.maps.HashMap;

public class HashSet<T> implements Set<T> {

	private static final Boolean PRESENT = Boolean.TRUE;
	private HashMap<T, Boolean> map;
	private int buckets;
	private float loadFactor;

	public HashSet(int buckets, float loadFactor) {
		this.buckets = buckets;
		this.loadFactor = loadFactor;
		this.map = new HashMap<T, Boolean>(buckets, loadFactor);
	}
	
	public HashSet() {
		this.buckets = 16;
		this.loadFactor = .75f;
		this.map = new HashMap<T, Boolean>();
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
		this.map = new HashMap<>(this.buckets, this.loadFactor);
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
