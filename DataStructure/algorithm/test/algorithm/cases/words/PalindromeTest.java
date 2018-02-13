package algorithm.cases.words;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithm.cases.words.Word;


public class PalindromeTest {

	@Test
	public void palindrome_with_1_letter() {
		assertTrue(new Word("a").isPalindrome());
	}

	@Test
	public void palindrome_with_10_letters() {
		assertTrue(new Word("abcdeedcba").isPalindrome());
	}

	@Test
	public void palindrome_with_9_letter() {
		assertTrue(new Word("abcdedcba").isPalindrome());
	}

	@Test
	public void not_palindrome_cases() {
		assertFalse(new Word("ab").isPalindrome());
		assertFalse(new Word("abcbe").isPalindrome());
		assertFalse(new Word("abceba").isPalindrome());
	}
	
}
