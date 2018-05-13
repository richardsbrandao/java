package string;

import java.util.LinkedList;

/**
 * Implement a method to perform basic string comprpession using the counts of repeated chars. 
 * For example the string aabcccccaaa would become a2b1c5a3. 
 * If the compressed string would not become smaller than original, your method should return the original string. 
 * You can assume the string has only upper case and lowercase letters (a-z)
 */
public class StringCompression {

	class StringRepetition {
		private Character character;
		private int repetition;

		public StringRepetition(Character character) {
			this.character = character;
			this.repetition = 1;
		}

		public boolean is(Character currentChar) {
			return character.equals(currentChar);
		}

		public void addOneRepetition() {
			this.repetition++;
		}
		
		@Override
		public String toString() {
			StringBuilder string = new StringBuilder(character.toString());
			if(repetition > 1) {
				return string.append(repetition).toString();
			} else {
				return string.toString();
			}
		}
	}
	
	/**
	 * Runtime - O(n)
	 * Space - O(n)
	 */
	public String compress(String string) {
		if(string.isEmpty()) {
			return string;
		}
		
		StringBuilder compressedString = new StringBuilder();
		LinkedList<StringRepetition> repetitionManager = new LinkedList<StringRepetition>();
		for(int i = 0; i < string.length(); i ++) {
			Character currentChar = string.charAt(i);
			if(i == 0) {
				repetitionManager.addFirst(new StringRepetition(currentChar));
				continue;
			}
			
			if(lastCharIsRepetead(repetitionManager, currentChar)) {
				repetitionManager.getLast().addOneRepetition();
			} else {
				StringRepetition lastRepetition = repetitionManager.removeLast();
				compressedString.append(lastRepetition.toString());
				repetitionManager.addFirst(new StringRepetition(currentChar));
			}
		}
		return compressedString.append(repetitionManager.getLast().toString()).toString();
	}
	
	public String compressRecursive(String string) {
		LinkedList<StringRepetition> repetitionManager = new LinkedList<StringRepetition>();
		return compressRecursive(string.toCharArray(), 0, new StringBuilder(), repetitionManager).toString();
	}

	/*
	 * Runtime - O(n)
	 * Space - O(n)
	 */
	private StringBuilder compressRecursive(char[] stringArray, int index, StringBuilder stringBuilder, LinkedList<StringRepetition> repetitionManager) {
		if(stringArray.length == 0) {
			return new StringBuilder();
		}
		
		if(stringArray.length == index) {
			return stringBuilder.append(repetitionManager.getLast().toString());
		}
		
		if(index == 0) {
			repetitionManager.addFirst(new StringRepetition(stringArray[index]));
			return compressRecursive(stringArray, ++index, stringBuilder, repetitionManager);
		}
		
		if(lastCharIsRepetead(repetitionManager, stringArray[index])) {
			repetitionManager.getLast().addOneRepetition();
			return compressRecursive(stringArray, ++index, stringBuilder, repetitionManager);
		} else {
			StringRepetition lastRepetition = repetitionManager.removeLast();
			stringBuilder.append(lastRepetition.toString());
			repetitionManager.addFirst(new StringRepetition(stringArray[index]));
			return compressRecursive(stringArray, ++index, stringBuilder, repetitionManager);
		}
	}

	private boolean lastCharIsRepetead(LinkedList<StringRepetition> repetitionManager, Character currentChar) {
		if(repetitionManager == null) {
			return false;
		}
		return repetitionManager.peekLast().is(currentChar);
	}

}
