package datastructures.base.map;

public class HashNode<K, V> {

	private K key;
	private V value;
	private HashNode<K, V> next;
	
	public HashNode(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	
	public void setNext(HashNode<K, V> next) {
		this.next = next;
	}
	
	public HashNode<K, V> getNext() {
		return next;
	}
	
	public boolean equalKey(K key) {
		if(this.key == null && key == null) {
			return true;
		}
		if(this.key == null ^ key == null) {
			return false;
		}
		return this.key.equals(key);
	}
	
	public boolean equalValue(V value) {
		return this.value.equals(value);
	}

	@Override
	public String toString() {
		return "HashNode [key=" + key + ", value=" + value + "]";
	}

	public boolean hasNext() {
		return next != null;
	}

	public boolean equalKeyValue(K key, V value) {
		return equalKey(key) && equalValue(value);
	}

	public void update(HashNode<K, V> node) {
		if(equalKey(node.key)) {
			this.value = node.value;
		}
	}
	
}
