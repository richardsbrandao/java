package string;

/**
 * There are three types of edits that can be performed on strings: insert a character, remove a char or replace a char
 * Given two strings, write a function to check if they are one edit (or zero edits) away
 */
public class EditableString {

	private String original;

	public EditableString(String original) {
		this.original = original;
	}

	public Boolean isEditable(String newString) {
		if(Math.abs(newString.length() - original.length()) > 1) {
			return false;
		}
		
		if(newString.equals(original)) {
			return false;
		}
		
		if( newString.length() == original.length() ) {
			return isEditable(original, newString, 0, Boolean.FALSE); 
		} else if ( newString.length() > original.length() ) {
			return isInserted(newString, original, 0, 0, Boolean.FALSE);
		} else {
			return isInserted(original, newString, 0, 0, Boolean.FALSE);
		}
	}

	private Boolean isInserted(String original, String newString, int originalIndex, int newStringIndex, Boolean isInserted) {
		if(newString.length() == newStringIndex) {
			return isInserted || original.length()-1 == originalIndex;
		}
		
		if(isDifferent(original, originalIndex, newString, newStringIndex)) {
			if(isInserted == true) {
				return false;
			} else {
				return isInserted(original, newString, ++originalIndex, newStringIndex, Boolean.TRUE);
			}
		}
		
		return isInserted(original, newString, ++originalIndex, ++newStringIndex, isInserted);
	}

	private Boolean isEditable(String original, String newString, int index, Boolean isEditable) {
		if(index >= original.length()) {
			return isEditable;
		}
		
		if(isDifferent(original, index, newString, index)) {
			if(isEditable == true) {
				return false;
			} else {
				return isEditable(original, newString, ++index, Boolean.TRUE);
			}
		}
		
		return isEditable(original, newString, ++index, isEditable);
	}
	
	private Boolean isDifferent(String original, int originalIndex, String newString, int newStringIndex) {
		return original.charAt(originalIndex) != newString.charAt(newStringIndex);
	}

}
