package algorithm.cases;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithm.cases.words.ReverseString;

public class ReverseOnlyStrings {

	@Test
	public void reverse() {
		String string = "abc*kkk jsa$ooo";
		String reversedString = new ReverseString().reverseOnlyWords(string);
		assertEquals("ooo*jsa kkk$abc", reversedString);
	}
	
}
