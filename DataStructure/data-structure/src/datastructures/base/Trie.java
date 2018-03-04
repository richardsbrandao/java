package datastructures.base;

public interface Trie {

	public void insert(String word);
	public void delete(String word);
	public boolean search(String word);
	public boolean isEmpty();
	
}
