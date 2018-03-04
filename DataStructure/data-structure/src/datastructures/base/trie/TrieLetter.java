package datastructures.base.trie;

public class TrieLetter {

	private TrieNode[] children;
	private String letter;
	private Boolean endOfWord;
	
	public TrieLetter(String letter) {
		this(letter, false);
	}
	
	public TrieLetter(String letter, Boolean endOfWord) {
		this.letter = letter;
		this.endOfWord = endOfWord;
	}
	
	public void setEndOfWord(Boolean endOfWord) {
		this.endOfWord = endOfWord;
	}
}
