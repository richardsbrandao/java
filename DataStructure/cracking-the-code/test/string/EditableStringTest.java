package string;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("EditableString")
public class EditableStringTest {

	
	@DisplayName("when editing char")
	@Nested
	class WhenEditingString {
		private EditableString editableString = new EditableString("pocket");

		@DisplayName("with one change must return true")
		@Test
		public void case_1() {
			assertTrue(editableString.isEditable("poctet"));
		}
		
		@DisplayName("with two changes must return false")
		@Test
		public void case_2() {
			assertFalse(editableString.isEditable("poctek"));
		}
		
		@DisplayName("with changes in different place must return false")
		@Test
		public void case_3() {
			assertFalse(editableString.isEditable("pokect"));
		}
	}
	
	@DisplayName("when delete char")
	@Nested
	class WhenDeleteString {
		private EditableString editableString = new EditableString("pocket");
		
		@DisplayName("with one char removed must return true")
		@Test
		public void case_1() {
			assertTrue(editableString.isEditable("ocket"));
		}
		
		@DisplayName("with two char removed must return false")
		@Test
		public void case_2() {
			assertFalse(editableString.isEditable("ocet"));
		}
		
		@DisplayName("with one char removed and one edited char must return false")
		@Test
		public void case_3() {
			assertFalse(editableString.isEditable("pecet"));
		}
	}
	
	@DisplayName("when insert char")
	@Nested
	class WhenInsertString {
		private EditableString editableString = new EditableString("pocket");
		
		@DisplayName("with one char inserted must return true")
		@Test
		public void case_1() {
			assertTrue(editableString.isEditable("pockets"));
		}
		
		@DisplayName("with one char inserted in the middle must return true")
		@Test
		public void case_2() {
			assertTrue(editableString.isEditable("pockket"));
		}
		
		@DisplayName("with two char inserted must return false")
		@Test
		public void case_3() {
			assertFalse(editableString.isEditable("popocket"));
		}
		
		@DisplayName("with one edit and one inserted char must return false")
		@Test
		public void case_4() {
			assertFalse(editableString.isEditable("poocter"));
		}
	}
	
	@DisplayName("when compare with two empty strings must return false")
	@Test
	public void case_1() {
		assertFalse(new EditableString("").isEditable(""));
	}

	@DisplayName("when compare with two equal strings must return false")
	@Test
	public void case_2() {
		assertFalse(new EditableString("pocket").isEditable("pocket"));
	}
	
}
