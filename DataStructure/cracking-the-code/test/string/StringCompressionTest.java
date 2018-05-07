package string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;

@DisplayName("StringCompression")
public class StringCompressionTest {

	private StringCompression stringCompression = new StringCompression();
	
	@DisplayName("when compress empty string with must return it")
	public void case_1() {
		assertEquals("", stringCompression.compress(""));
	}

	@DisplayName("when compress string with no repeated elements must return it")
	public void case_2() {

	}
	
	@DisplayName("when compress string with less repeated words than non repeated must return it")
	public void case_3() {

	}
	
	@DisplayName("when compress string with all doubles letters must return a compress string correctly")
	public void case_4() {

	}
	
	@DisplayName("when compress string with some repeated letters must return a compress string correctly")
	public void case_5() {

	}
	
	@DisplayName("when compress string with some repeated letters in different positions must return a compress string correctly")
	public void case_6() {

	}
}
