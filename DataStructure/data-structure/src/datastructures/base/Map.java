package datastructures.base;

public interface Map<K, V> {
	V get(K key);
	V getOrDefault(K key, V defaultValue);
	
	boolean containsKey(K key);
	boolean containsValue(V value);
	
	boolean put(K key, V value);
	boolean remove(K key);
	boolean removeIfKeyEqualValue(K key, V value);
	
	int size();
	boolean isEmpty();
	
	Object[] keys();
	Object[] values();
}
