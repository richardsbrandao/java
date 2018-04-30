package string;

import java.util.HashSet;
import java.util.Set;

/**
 * Implement a algorithm to determine if a string has all unique characters
 */
public class UniqueString {

	/**
	 * Space: O(n)
	 * Runtime: O(n)
	 */
	public boolean isUnique(String string) {
		Set<Character> set = new HashSet<Character>();
		for(Character character : string.toCharArray()) {
			if(set.contains(character)) {
				return false;
			} else {
				set.add(character);
			}
		}
		
		return true;
	}
	
}
