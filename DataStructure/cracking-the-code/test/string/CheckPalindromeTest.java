package string;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("CheckPalindrome")
public class CheckPalindromeTest {

	@Nested
	@DisplayName("is palindrome")
	class IsPalindrome {
		private CheckPalindrome checkPalindrome = new CheckPalindrome();
		
		@Test
		@DisplayName("with single palindrome word must return true")
		public void case_1() {
			assertTrue(checkPalindrome.isPalindrome("racecar"));
		}

		@Test
		@DisplayName("with two palindrome words with different space separation must return true")
		public void case_2() {
			assertTrue(checkPalindrome.isPalindrome("too hot to hoot"));
		}

		@Test
		@DisplayName("with two palindrome words with no space separation must return true")
		public void case_3() {
			assertTrue(checkPalindrome.isPalindrome("5001 1005"));
		}

		@Test
		@DisplayName("with not palindrome words must return false")
		public void case_4() {
			assertFalse(checkPalindrome.isPalindrome("dog"));
		}
	}
	
}