package datastructures.base.trie;

public class TrieNode {
	
	private static final int ALPHABET_SIZE = 26;
	private char letter;
	private Boolean endOfWord;
	private TrieNode[] children;
	
	public TrieNode() {
		this.endOfWord = Boolean.FALSE;
		this.children = new TrieNode[ALPHABET_SIZE];
	}

	public TrieNode(char character) {
		this();
		this.letter = character;
	}

	public char getLetter() {
		return letter;
	}
	
	public Boolean isEndOfWord() {
		return endOfWord;
	}

	public TrieNode[] getChildren() {
		return this.children;
	}

	public void turnWord() {
		this.endOfWord = Boolean.TRUE;
	}

	public boolean existsAt(int charIndex) {
		return this.children[charIndex] != null;
	}

	public TrieNode getChildrenAt(int charIndex) {
		return this.children[charIndex];
	}

	public boolean isEmpty() {
		for(TrieNode node : children) {
			if(node != null) {
				return false;
			}
		}
		return true;
	}

	public void turnPath() {
		this.endOfWord = Boolean.FALSE;
	}

}
