package algorithm.cases.words;

import java.util.Arrays;

public class Word {

	private String string;
	
	public Word(String string) {
		this.string = string;
	}
	
	/*
	 * Runtime: O(n)
	 * Space: O(1)
	 */
	public boolean isPalindrome() {
		int start = 0;
		int end = string.length()-1;
		while(end > start) {
			if(string.charAt(start++) != string.charAt(end--)) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Runtime: O(n log n)
	 * Space: O(n)
	 */
	public boolean isAnagram(String anotherWord) {
		char[] originalChars = string.toCharArray();
		char[] anotherWorldChars = anotherWord.toCharArray();
		
		Arrays.sort(originalChars);
		Arrays.sort(anotherWorldChars);
		
		return Arrays.equals(originalChars, anotherWorldChars);
	}
}
