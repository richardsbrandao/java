package string;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("UniqueString")
public class UniqueStringTest {

	private UniqueString uniqueString = new UniqueString();

	@Nested
	@DisplayName("when is unique")
	class WhenIsUnique {
		@Test
		@DisplayName("because string is empty")
		public void case_1() {
			assertTrue(uniqueString.isUnique(""));
		}

		@Test
		@DisplayName("because string has no repeated chars")
		public void case_2() {
			assertTrue(uniqueString.isUnique("asdvcgf"));
		}

		@Test
		@DisplayName("because string has no repeated chars or number")
		public void case_3() {
			assertTrue(uniqueString.isUnique("asdf123"));
		}

		@Test
		@DisplayName("because string has no repeated chars in same mode")
		public void case_4() {
			assertTrue(uniqueString.isUnique("abcDde"));
		}
	}

	@Nested
	@DisplayName("when is not unique")
	class WhenIsNotUnique {
		@Test
		@DisplayName("because there is a repeated char")
		public void case_1() {
			assertFalse(uniqueString.isUnique("abcdea"));
		}

		@Test
		@DisplayName("because there is a repeated number")
		public void case_2() {
			assertFalse(uniqueString.isUnique("abc11"));
		}

		@Test
		@DisplayName("because there is a repeated upper case char")
		public void case_3() {
			assertFalse(uniqueString.isUnique("asAdsA"));
		}

		@Test
		@DisplayName("because there is a repeated special char")
		public void case_4() {
			assertFalse(uniqueString.isUnique("abcEE"));
		}
	}

}
