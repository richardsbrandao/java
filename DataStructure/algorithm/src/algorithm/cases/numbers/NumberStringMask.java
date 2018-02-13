package algorithm.cases.numbers;

/*
 * Receive: A string with any chars
 * Solution: A string with only numbers masked by 3 and 3 or 3-2 or 2-2 depending from length of string
 * Runtime: O(n)
 * Space: O(n)
 */
public class NumberStringMask {

	private String string;

	public NumberStringMask(String string) {
		this.string = string;
	}

	public String mask() {
		StringBuilder maskedString = new StringBuilder();
		return mask(string.replaceAll("[^\\d.]", ""), 0, maskedString).toString();
	}

	private StringBuilder mask(String string, int index, StringBuilder maskedString) {
		if(string.length() - index == 0) {
			return maskedString;
		}
		
		// when last 5 chars
		if(string.length()%3 == 2 && string.length() - index == 5) {
			if(string.length() > 5) {
				maskedString.append("-");
			}
			maskedString
				.append(string.substring(index, index+3))
				.append("-")
				.append(string.substring(index+3));
			return maskedString;
		}
		
		// when last 4 chars
		if(string.length()%3 == 1 && string.length() - index == 4) {
			if(string.length() > 4) {
				maskedString.append("-");
			}
			maskedString
				.append(string.substring(index, index+2))
				.append("-")
				.append(string.substring(index+2));
			return maskedString;
		}
		
		String letter = string.substring(index, index+1);
		if(index != 0 && index%3 == 0) {
			maskedString.append("-");
		}
		maskedString.append(letter);
		return mask(string, index+1, maskedString);
	}
}
