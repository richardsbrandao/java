package string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("StringCompression")
public class StringCompressionTest {

	private StringCompression stringCompression = new StringCompression();
	
	@Nested
	@DisplayName("ForImplementation: ")
	class ForImplementation {
		@DisplayName("when compress empty string with must return it")
		@Test
		public void case_1() {
			assertEquals("", stringCompression.compress(""));
		}
		
		@DisplayName("when compress string with one character must return it") 
		@Test
		public void case_2() {
			assertEquals("a", stringCompression.compress("a"));
		}
	
		@DisplayName("when compress string with no repeated elements must return it")
		@Test
		public void case_3() {
			assertEquals("abcdef", stringCompression.compress("abcdef"));
		}
		
		@DisplayName("when compress string with less repeated words than non repeated must return it")
		@Test
		public void case_4() {
			assertEquals("ab2de2", stringCompression.compress("abbdee"));
		}
		
		@DisplayName("when compress string with all doubles letters must return a compress string correctly")
		@Test
		public void case_5() {
			assertEquals("ab4ce3d2", stringCompression.compress("abbbbceeedd"));
		}
		
		@DisplayName("when compress string with some repeated letters must return a compress string correctly")
		@Test
		public void case_6() {
			assertEquals("a3b2ce3d2", stringCompression.compress("aaabbceeedd"));
		}
		
		@DisplayName("when compress string with some repeated letters in different positions must return a compress string correctly")
		@Test
		public void case_7() {
			assertEquals("a3b2ce2f3e2d2a2", stringCompression.compress("aaabbceefffeeddaa"));
		}
	}
	
	@Nested
	@DisplayName("RecursiveImplementation:") 
	class RecursiveImplementation {
		@DisplayName("when compress empty string with must return it")
		@Test
		public void case_1() {
			assertEquals("", stringCompression.compressRecursive(""));
		}
		
		@DisplayName("when compress string with one character must return it") 
		@Test
		public void case_2() {
			assertEquals("a", stringCompression.compressRecursive("a"));
		}
	
		@DisplayName("when compress string with no repeated elements must return it")
		@Test
		public void case_3() {
			assertEquals("abcdef", stringCompression.compressRecursive("abcdef"));
		}
		
		@DisplayName("when compress string with less repeated words than non repeated must return it")
		@Test
		public void case_4() {
			assertEquals("ab2de2", stringCompression.compressRecursive("abbdee"));
		}
		
		@DisplayName("when compress string with all doubles letters must return a compress string correctly")
		@Test
		public void case_5() {
			assertEquals("ab4ce3d2", stringCompression.compressRecursive("abbbbceeedd"));
		}
		
		@DisplayName("when compress string with some repeated letters must return a compress string correctly")
		@Test
		public void case_6() {
			assertEquals("a3b2ce3d2", stringCompression.compressRecursive("aaabbceeedd"));
		}
		
		@DisplayName("when compress string with some repeated letters in different positions must return a compress string correctly")
		@Test
		public void case_7() {
			assertEquals("a3b2ce2f3e2d2a2", stringCompression.compressRecursive("aaabbceefffeeddaa"));
		}
	}
}
