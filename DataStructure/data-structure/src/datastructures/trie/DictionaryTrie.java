package datastructures.trie;

import datastructures.base.Trie;
import datastructures.base.trie.TrieNode;

public class DictionaryTrie implements Trie {

	private static final int ARRAY_REDUCER = 97;
	private TrieNode root;

	public DictionaryTrie() {
		this.root = new TrieNode();
	}
	
	public void insert(String word) {
		String lowerword = word.toLowerCase();
		if(!lowerword.matches("^[a-z]+$")) {
			throw new IllegalArgumentException();
		}
		insert(root, lowerword, 0);
	}

	private void insert(TrieNode current, String word, int index) {
		if(index == word.length()) {
			current.turnWord();
			return;
		}
		
		TrieNode[] children = current.getChildren();
		char character = word.charAt(index);
		int charIndex = charIndex(character);
		
		if(!current.existsAt(charIndex)) {
			TrieNode trieNode = new TrieNode(character);
			current = children[charIndex] = trieNode; 
		} else {
			current = children[charIndex];
		}
		
		insert(current, word, ++index);
	}
	
	private int charIndex(char character) {
		int asciiCode = (int) character;
		return asciiCode - ARRAY_REDUCER;
	}

	@Override
	public void delete(String word) {
		delete(root, word, 0, 0, new int[word.length()]);
	}

	private void delete(TrieNode current, String word, int index, int lastPrefix, int[] charsMemorization) {
		if(word.length() == index) {
			forceDelete(current, charsMemorization, lastPrefix); // in the end, choose delete strategy
			return;
		}
		
		if(current.isEndOfWord()) { // when there are prefix in the path
			lastPrefix = index;
		}
		
		TrieNode[] children = current.getChildren();
		char character = word.charAt(index);
		int charIndex = charIndex(character);
		charsMemorization[index] = charIndex;
		current = children[charIndex];
		
		if(current == null) { // when word not exists
			return;
		}
		
		delete(current, word, ++index, lastPrefix, charsMemorization);
	}

	private void forceDelete(TrieNode current, int[] charsMemorization, int lastPrefix) {
		if(current.isEmpty()) {
			removeLetters(root, charsMemorization, 0, lastPrefix);
		} else {
			current.turnPath();
		}
	}

	private void removeLetters(TrieNode current, int[] charsMemorization, int index, int from) {
		if(charsMemorization.length == index) {
			return;
		}
		
		TrieNode[] children = current.getChildren();
		current = children[charsMemorization[index]];
		
		if(index >= from) {
			children[charsMemorization[index]] = null;
		}
		
		removeLetters(current, charsMemorization, ++index, from);
	}

	@Override
	public boolean search(String word) {
		return search(root, word, 0);
	}

	private boolean search(TrieNode current, String word, int index) {
		if(current == null) {
			return false;
		}
		if(word.length() == index) {
			return current.isEndOfWord();
		}
		char character = word.charAt(index);
		int charIndex = charIndex(character);
		current = current.getChildrenAt(charIndex);
		return search(current, word, ++index);
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

}
