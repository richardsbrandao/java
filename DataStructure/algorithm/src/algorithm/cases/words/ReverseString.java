package algorithm.cases.words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReverseString {

	private List<String> notReversedCharacters;

	@SuppressWarnings("serial")
	public ReverseString() {
		this.notReversedCharacters = new ArrayList<String>() { {
			add("!");
			add("@");
			add("#");
			add("$");
			add("%");
			add("&");
			add("*");
			add("(");
			add(")");
			add("-");
			add("+");
			add(" ");
		} };
	}
	
	public String reverseOnlyWords(String phrase) {
		Map<String, List<String>> splitter = splitter(phrase);
		return reverseOnlyWords(splitter);
	}

	private String reverseOnlyWords(Map<String, List<String>> splitter) {
		StringBuilder phrase = new StringBuilder();
		List<String> words = splitter.get("WORDS");
		List<String> specialChars = splitter.get("SPECIAL_CHARS");
		for(int i = words.size()-1, j = 0; i >= 0; i--, j++) {
			phrase.append(words.get(i));
			if(j < specialChars.size()) {
				phrase.append(specialChars.get(j));
			}	
		}
		return phrase.toString();
	}

	private Map<String, List<String>> splitter(String phrase) {
		Map<String, List<String>> splitted = new HashMap<String, List<String>>();
		
		char[] charArray = phrase.toCharArray();
		int startWord = 0;
		for(int i = 0; i < charArray.length; i++) {
			if( notReversedCharacters.contains(String.valueOf(charArray[i])) ) {
				addSpecialChar(splitted, phrase.substring(i, i+1));
				addWord(splitted, phrase.substring(startWord, i));
				startWord = i+1;
			} 
		}
		addWord(splitted, phrase.substring(startWord, charArray.length));
		return splitted;
	}
	
	public void addSpecialChar(Map<String, List<String>> splitted, String specialChars) {
		List<String> list = splitted.getOrDefault("SPECIAL_CHARS", new ArrayList<String>());
		list.add(specialChars);
		splitted.put("SPECIAL_CHARS", list);
	}
	
	public void addWord(Map<String, List<String>> splitted, String word) {
		List<String> list = splitted.getOrDefault("WORDS", new ArrayList<String>());
		list.add(word);
		splitted.put("WORDS", list);
	}
	
}
