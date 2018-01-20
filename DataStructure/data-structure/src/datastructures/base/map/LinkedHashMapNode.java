package datastructures.base.map;

public class LinkedHashMapNode<K, V> {

	private K key;
	private V value;
	private LinkedHashMapNode<K, V> next;
	
	private LinkedHashMapNode<K, V> after;
	private LinkedHashMapNode<K, V> before;
	
	public LinkedHashMapNode(K key, V value, LinkedHashMapNode<K, V> before, LinkedHashMapNode<K, V> after) {
		this.key = key;
		this.value = value;
		this.before = before;
		this.after = after;
	}
	
	public K getKey() {
		return key;
	}
	public V getValue() {
		return value;
	}
	public LinkedHashMapNode<K, V> getNext() {
		return next;
	}
	public LinkedHashMapNode<K, V> getAfter() {
		return after;
	}
	public LinkedHashMapNode<K, V> getBefore() {
		return before;
	}

	public void setNext(LinkedHashMapNode<K, V> next) {
		this.next = next;
	}

	public void setAfter(LinkedHashMapNode<K, V> after) {
		this.after = after;
	}

	public void setBefore(LinkedHashMapNode<K, V> before) {
		this.before = before;
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
	
	public boolean hasAfter() {
		return after != null;
	}

	public boolean equalKeyValue(K key, V value) {
		return equalKey(key) && equalValue(value);
	}

	public void update(LinkedHashMapNode<K, V> newNode) {
		if(equalKey(newNode.key)) {
			this.value = newNode.value;
		}
	}
}
