package datastructures.trie;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.Trie;
import datastructures.base.trie.TrieNode;

public class TrieTest {

	@Test
	public void when_insert_with_one_letter_must_create_a_end_of_letter_marked_child() {
		Trie trie = new DictionaryTrie();
		trie.insert("a");
		
		TrieNode trieNode = getTrieNode(trie);
		
		assertEquals( 'a', trieNode.getChildren()[0].getLetter());
		assertTrue( trieNode.getChildren()[0].isEndOfWord());
	}

	private TrieNode getTrieNode(Trie trie) {
		return (TrieNode) ReflectionTestUtils.getField(trie, "root");
	}
	
	@Test
	public void when_insert_with_more_then_one_letter_must_create_word_and_mark_last_letter_as_end_of_word() {
		Trie trie = new DictionaryTrie();
		trie.insert("java");
		
		TrieNode trieNode = getTrieNode(trie);
		
		assertEquals( 'j', trieNode.getChildren()[9].getLetter());
		assertFalse( trieNode.getChildren()[9].isEndOfWord());
		
		TrieNode secondLetter = trieNode.getChildren()[9];
		assertEquals( 'a', secondLetter.getChildren()[0].getLetter());
		assertFalse( secondLetter.getChildren()[0].isEndOfWord());
		
		TrieNode thirdLetter = secondLetter.getChildren()[0];
		assertEquals( 'v', thirdLetter.getChildren()[21].getLetter());
		assertFalse( thirdLetter.getChildren()[21].isEndOfWord());
		
		TrieNode fourthLetter = thirdLetter.getChildren()[21];
		assertEquals( 'a', fourthLetter.getChildren()[0].getLetter());
		assertTrue( fourthLetter.getChildren()[0].isEndOfWord());
	}
	
	@Test
	public void when_insert_lower_case_with_more_then_one_letter_must_create_word_and_mark_last_letter_as_end_of_word() {
		Trie trie = new DictionaryTrie();
		trie.insert("JAVA");
		
		TrieNode trieNode = getTrieNode(trie);
		
		assertEquals( 'j', trieNode.getChildren()[9].getLetter());
		assertFalse( trieNode.getChildren()[9].isEndOfWord());
		
		TrieNode secondLetter = trieNode.getChildren()[9];
		assertEquals( 'a', secondLetter.getChildren()[0].getLetter());
		assertFalse( secondLetter.getChildren()[0].isEndOfWord());
		
		TrieNode thirdLetter = secondLetter.getChildren()[0];
		assertEquals( 'v', thirdLetter.getChildren()[21].getLetter());
		assertFalse( thirdLetter.getChildren()[21].isEndOfWord());
		
		TrieNode fourthLetter = thirdLetter.getChildren()[21];
		assertEquals( 'a', fourthLetter.getChildren()[0].getLetter());
		assertTrue( fourthLetter.getChildren()[0].isEndOfWord());
	}
	
	@Test
	public void when_insert_with_word_smaller_than_new_one_must_turn_the_last_letter_as_word() {
		Trie trie = new DictionaryTrie();
		trie.insert("javascript");
		trie.insert("java");
		
		TrieNode trieNode = getTrieNode(trie);
		
		assertEquals( 'j', trieNode.getChildren()[9].getLetter());
		assertFalse( trieNode.getChildren()[9].isEndOfWord());
		
		TrieNode secondLetter = trieNode.getChildren()[9];
		assertEquals( 'a', secondLetter.getChildren()[0].getLetter());
		assertFalse( secondLetter.getChildren()[0].isEndOfWord());
		
		TrieNode thirdLetter = secondLetter.getChildren()[0];
		assertEquals( 'v', thirdLetter.getChildren()[21].getLetter());
		assertFalse( thirdLetter.getChildren()[21].isEndOfWord());
		
		TrieNode fourthLetter = thirdLetter.getChildren()[21];
		assertEquals( 'a', fourthLetter.getChildren()[0].getLetter());
		assertTrue( fourthLetter.getChildren()[0].isEndOfWord());
		
		TrieNode fifthLetter = fourthLetter.getChildren()[0];
		assertEquals( 's', fifthLetter.getChildren()[18].getLetter());
		assertFalse( fifthLetter.getChildren()[18].isEndOfWord());
		
		TrieNode sixthLetter = fifthLetter.getChildren()[18];
		assertEquals( 'c', sixthLetter.getChildren()[2].getLetter());
		assertFalse( sixthLetter.getChildren()[2].isEndOfWord());
		
		TrieNode seventhLetter = sixthLetter.getChildren()[2];
		assertEquals( 'r', seventhLetter.getChildren()[17].getLetter());
		assertFalse( seventhLetter.getChildren()[17].isEndOfWord());
		
		TrieNode eighthLetter = seventhLetter.getChildren()[17];
		assertEquals( 'i', eighthLetter.getChildren()[8].getLetter());
		assertFalse( eighthLetter.getChildren()[8].isEndOfWord());
		
		TrieNode ninthLetter = eighthLetter.getChildren()[8];
		assertEquals( 'p', ninthLetter.getChildren()[15].getLetter());
		assertFalse( ninthLetter.getChildren()[15].isEndOfWord());
		
		TrieNode tenthLetter = ninthLetter.getChildren()[15];
		assertEquals( 't', tenthLetter.getChildren()[19].getLetter());
		assertTrue( tenthLetter.getChildren()[19].isEndOfWord());
	}
	
	@Test
	public void when_insert_with_word_greater_than_new_one_must_continue_from_the_one_with_leftover_words() {
		Trie trie = new DictionaryTrie();
		trie.insert("java");
		trie.insert("javascript");
		
		TrieNode trieNode = getTrieNode(trie);
		
		assertEquals( 'j', trieNode.getChildren()[9].getLetter());
		assertFalse( trieNode.getChildren()[9].isEndOfWord());
		
		TrieNode secondLetter = trieNode.getChildren()[9];
		assertEquals( 'a', secondLetter.getChildren()[0].getLetter());
		assertFalse( secondLetter.getChildren()[0].isEndOfWord());
		
		TrieNode thirdLetter = secondLetter.getChildren()[0];
		assertEquals( 'v', thirdLetter.getChildren()[21].getLetter());
		assertFalse( thirdLetter.getChildren()[21].isEndOfWord());
		
		TrieNode fourthLetter = thirdLetter.getChildren()[21];
		assertEquals( 'a', fourthLetter.getChildren()[0].getLetter());
		assertTrue( fourthLetter.getChildren()[0].isEndOfWord());
		
		TrieNode fifthLetter = fourthLetter.getChildren()[0];
		assertEquals( 's', fifthLetter.getChildren()[18].getLetter());
		assertFalse( fifthLetter.getChildren()[18].isEndOfWord());
		
		TrieNode sixthLetter = fifthLetter.getChildren()[18];
		assertEquals( 'c', sixthLetter.getChildren()[2].getLetter());
		assertFalse( sixthLetter.getChildren()[2].isEndOfWord());
		
		TrieNode seventhLetter = sixthLetter.getChildren()[2];
		assertEquals( 'r', seventhLetter.getChildren()[17].getLetter());
		assertFalse( seventhLetter.getChildren()[17].isEndOfWord());
		
		TrieNode eighthLetter = seventhLetter.getChildren()[17];
		assertEquals( 'i', eighthLetter.getChildren()[8].getLetter());
		assertFalse( eighthLetter.getChildren()[8].isEndOfWord());
		
		TrieNode ninthLetter = eighthLetter.getChildren()[8];
		assertEquals( 'p', ninthLetter.getChildren()[15].getLetter());
		assertFalse( ninthLetter.getChildren()[15].isEndOfWord());
		
		TrieNode tenthLetter = ninthLetter.getChildren()[15];
		assertEquals( 't', tenthLetter.getChildren()[19].getLetter());
		assertTrue( tenthLetter.getChildren()[19].isEndOfWord());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void when_insert_with_invalid_char_must_throw_error() {
		Trie trie = new DictionaryTrie();
		trie.insert("aço");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void when_insert_with_number_must_throw_error() {
		Trie trie = new DictionaryTrie();
		trie.insert("12");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void when_insert_with_special_char_must_throw_error() {
		Trie trie = new DictionaryTrie();
		trie.insert("go!");
	}
	
	@Test
	public void when_search_with_valid_word_must_return_true() {
		Trie trie = new DictionaryTrie();
		trie.insert("javascript");
		
		assertTrue(trie.search("javascript"));
	}
	
	@Test
	public void when_search_with_valid_word_that_is_also_prefix_must_return_true() {
		Trie trie = new DictionaryTrie();
		trie.insert("java");
		trie.insert("javascript");
		
		assertTrue(trie.search("java"));
	}
	
	@Test
	public void when_search_with_a_word_that_is_not_a_prefix_must_return_false() {
		Trie trie = new DictionaryTrie();
		trie.insert("javascript");

		assertFalse(trie.search("ruby"));
	}
	
	@Test
	public void when_search_with_prefix_word_but_is_not_a_valid_word_must_return_false() {
		Trie trie = new DictionaryTrie();
		trie.insert("javascript");
		
		assertFalse(trie.search("java"));
	}
	
	@Test
	public void when_empty_with_empty_trie_must_return_true() {
		Trie trie = new DictionaryTrie();
		assertTrue(trie.isEmpty());
	}
	
	@Test
	public void when_empty_with_non_empty_trie_must_return_false() {
		Trie trie = new DictionaryTrie();
		trie.insert("node");
		assertFalse(trie.isEmpty());
	}
	
	@Test
	public void when_delete_with_prefix_word_not_existent_must_do_nothing() {
		Trie trie = new DictionaryTrie();
		trie.insert("r");
		trie.delete("ruby");
		
		TrieNode trieNode = getTrieNode(trie);
		
		assertEquals( 'r', trieNode.getChildren()[17].getLetter());
		assertTrue( trieNode.getChildren()[17].isEndOfWord());
	}
	
	@Test
	public void when_delete_with_prefix_word_must_turn_invalid_word_and_keep_the_path_for_other_words() {
		Trie trie = new DictionaryTrie();
		trie.insert("java");
		trie.insert("javascript");
		trie.delete("java");
		
		TrieNode trieNode = getTrieNode(trie);
		
		assertEquals( 'j', trieNode.getChildren()[9].getLetter());
		assertFalse( trieNode.getChildren()[9].isEndOfWord());
		
		TrieNode secondLetter = trieNode.getChildren()[9];
		assertEquals( 'a', secondLetter.getChildren()[0].getLetter());
		assertFalse( secondLetter.getChildren()[0].isEndOfWord());
		
		TrieNode thirdLetter = secondLetter.getChildren()[0];
		assertEquals( 'v', thirdLetter.getChildren()[21].getLetter());
		assertFalse( thirdLetter.getChildren()[21].isEndOfWord());
		
		TrieNode fourthLetter = thirdLetter.getChildren()[21];
		assertEquals( 'a', fourthLetter.getChildren()[0].getLetter());
		assertFalse( fourthLetter.getChildren()[0].isEndOfWord());
		
		TrieNode fifthLetter = fourthLetter.getChildren()[0];
		assertEquals( 's', fifthLetter.getChildren()[18].getLetter());
		assertFalse( fifthLetter.getChildren()[18].isEndOfWord());
		
		TrieNode sixthLetter = fifthLetter.getChildren()[18];
		assertEquals( 'c', sixthLetter.getChildren()[2].getLetter());
		assertFalse( sixthLetter.getChildren()[2].isEndOfWord());
		
		TrieNode seventhLetter = sixthLetter.getChildren()[2];
		assertEquals( 'r', seventhLetter.getChildren()[17].getLetter());
		assertFalse( seventhLetter.getChildren()[17].isEndOfWord());
		
		TrieNode eighthLetter = seventhLetter.getChildren()[17];
		assertEquals( 'i', eighthLetter.getChildren()[8].getLetter());
		assertFalse( eighthLetter.getChildren()[8].isEndOfWord());
		
		TrieNode ninthLetter = eighthLetter.getChildren()[8];
		assertEquals( 'p', ninthLetter.getChildren()[15].getLetter());
		assertFalse( ninthLetter.getChildren()[15].isEndOfWord());
		
		TrieNode tenthLetter = ninthLetter.getChildren()[15];
		assertEquals( 't', tenthLetter.getChildren()[19].getLetter());
		assertTrue( tenthLetter.getChildren()[19].isEndOfWord());
	}
	
	@Test
	public void when_delete_with_sufix_word_must_remove_path_until_first_valid_prefix() {
		Trie trie = new DictionaryTrie();
		trie.insert("java");
		trie.insert("javascript");
		trie.delete("javascript");
		
		TrieNode trieNode = getTrieNode(trie);
		
		assertEquals( 'j', trieNode.getChildren()[9].getLetter());
		assertFalse( trieNode.getChildren()[9].isEndOfWord());
		
		TrieNode secondLetter = trieNode.getChildren()[9];
		assertEquals( 'a', secondLetter.getChildren()[0].getLetter());
		assertFalse( secondLetter.getChildren()[0].isEndOfWord());
		
		TrieNode thirdLetter = secondLetter.getChildren()[0];
		assertEquals( 'v', thirdLetter.getChildren()[21].getLetter());
		assertFalse( thirdLetter.getChildren()[21].isEndOfWord());
		
		TrieNode fourthLetter = thirdLetter.getChildren()[21];
		assertEquals( 'a', fourthLetter.getChildren()[0].getLetter());
		assertTrue( fourthLetter.getChildren()[0].isEndOfWord());
		
		TrieNode fifthLetter = fourthLetter.getChildren()[0];
		assertTrue( fifthLetter.isEmpty() );
	}
	
	@Test
	public void when_delete_with_non_prefix_or_sufix_word_must_remove_all_path() {
		Trie trie = new DictionaryTrie();
		trie.insert("ruby");
		trie.insert("javascript");
		
		trie.delete("javascript");
		
		TrieNode trieNode = getTrieNode(trie);
		
		assertNull( trieNode.getChildren()[9]);
		assertNotNull( trieNode.getChildren()[17]);
	}
	
}
