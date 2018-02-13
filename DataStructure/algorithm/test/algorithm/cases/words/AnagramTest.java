package algorithm.cases.words;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import algorithm.cases.words.Word;

public class AnagramTest {

	@Test
	public void is_anagram() {
		assertTrue( new Word("roma").isAnagram("amor") );
		assertTrue( new Word("abc").isAnagram("cab") );
	}
	
	@Test
	public void is_not_anagram() {
		assertFalse( new Word("roma").isAnagram("milan") );
		assertFalse( new Word("abc").isAnagram("dac") );
	}
	
}
