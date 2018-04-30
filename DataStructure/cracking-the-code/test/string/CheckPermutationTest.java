package string;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import string.CheckPermutation.PermutationMode;

@DisplayName("CheckPermutation")
public class CheckPermutationTest {

	@Nested
	@DisplayName("when case sensitive mode")
	class WhenCaseSensitiveMode {
		private CheckPermutation checkPermutation = new CheckPermutation(PermutationMode.CASE_SENSITIVE);
		
		@Test
		@DisplayName("must return false if strings arent permutations")
		public void case_1() {
			assertFalse(checkPermutation.arePermutation("dog", "cat"));	
		}
		
		@Test
		@DisplayName("must return true if lower case strings are permutations in same case")
		public void case_2() {
			assertTrue(checkPermutation.arePermutation("dog", "god"));
		}
		
		@Test
		@DisplayName("must return true if upper case strings are permutations in same case")
		public void case_3() {
			assertTrue(checkPermutation.arePermutation("DOG", "GOD"));
		}
		
		@Test
		@DisplayName("must return false if strings are permutations but not in same case")
		public void case_4() {
			assertFalse(checkPermutation.arePermutation("DOG", "god"));
		}
		
		@Test
		@DisplayName("must return false if strings has different lengths even if is permutations")
		public void case_5() {
			assertFalse(checkPermutation.arePermutation("richard", "richards"));
		}
		
	}
	
	@Nested
	@DisplayName("when case insensitive")
	class WhenCaseInsensitiveMode {
		private CheckPermutation checkPermutation = new CheckPermutation(PermutationMode.CASE_INSENSITIVE);
		
		@Test
		@DisplayName("must return false if strings arent permutations")
		public void case_1() {
			assertFalse(checkPermutation.arePermutation("dog", "cat"));	
		}
		
		@Test
		@DisplayName("must return true if lower case strings are permutations in same case")
		public void case_2() {
			assertTrue(checkPermutation.arePermutation("dog", "god"));
		}
		
		@Test
		@DisplayName("must return true if upper case strings are permutations in same case")
		public void case_3() {
			assertTrue(checkPermutation.arePermutation("DOG", "GOD"));
		}
		
		@Test
		@DisplayName("must return true if strings are permutations but not in same case")
		public void case_4() {
			assertTrue(checkPermutation.arePermutation("DOG", "god"));
		}
		
		@Test
		@DisplayName("must return false if strings has different lengths even if is permutations")
		public void case_5() {
			assertFalse(checkPermutation.arePermutation("richard", "richards"));
		}
	}
	
}
