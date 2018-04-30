package string;

import java.util.Arrays;

/**
 * Given two strings, write a method to decide if one is a permutation of other
 */
public class CheckPermutation {

	public enum PermutationMode {
		CASE_SENSITIVE, CASE_INSENSITIVE
	}

	private PermutationMode mode;
	
	public CheckPermutation(PermutationMode mode) {
		this.mode = mode;
	}
	
	private String sort(String string) {
		char[] chars = string.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}

	/**
	 * Runtime(O(strA log strA + strB log strB))
	 * Space(O(1))
	 */
	public boolean arePermutation(String stringOne, String stringTwo) {
		if(stringOne.length() != stringTwo.length()) {
			return false;
		}
		
		if(mode == PermutationMode.CASE_INSENSITIVE) {
			stringOne = stringOne.toLowerCase();
			stringTwo = stringTwo.toLowerCase();
		}
		
		stringOne = sort(stringOne);
		stringTwo = sort(stringTwo);
		
		return stringOne.equals(stringTwo);
	}
	
}
