package string;

public class CheckPalindrome {

	/**
	 * Runtime: O(n)
	 * Space: O(1)
	 */
	public boolean isPalindrome(String string) {
		int i = 0;
		int j = string.length()-1;
		
		
		while(i < j) {
			if(string.charAt(i) == ' ') {
				i++;
				continue;
			}
			if(string.charAt(j) == ' ') {
				j--;
				continue;
			}
			if(string.charAt(i++) != string.charAt(j--)) {
				return false;
			}
		}
		
		return true;
	}

}
