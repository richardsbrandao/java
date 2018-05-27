package linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import linkedlist.helper.LinkedNode;

public class RemoveDuplicatesTest {

	@Test
	@DisplayName("when remove duplicate with empty linked list must return it")
	public void case_one() {
		LinkedNode<String> result = new RemoveDuplicates().removeDuplicates(new LinkedNode<>());
		
		assertNull(result.getValue());
		assertNull(result.getNext());
	}
	
	@Test
	@DisplayName("when remove duplicates with linked list without repeated elements must return it")
	public void case_two() {
		LinkedNode<String> result = new RemoveDuplicates().removeDuplicates(withoutRepetition());
		
		assertEquals("A", result.getValue());
		assertEquals("2", result.getNext().getValue());
		assertEquals("F0", result.getNext().getNext().getValue());
		assertNull(result.getNext().getNext().getNext());
	}
	
	@Test
	@DisplayName("when remove duplicates with linked list with single repetition elements must return list without repetitions")
	public void case_three() {
		LinkedNode<String> result = new RemoveDuplicates().removeDuplicates(withoutSingleRepetition());
		
		assertEquals("A", result.getValue());
		assertEquals("F0", result.getNext().getValue());
		assertEquals("6", result.getNext().getNext().getValue());
		assertEquals("9", result.getNext().getNext().getNext().getValue());
		assertEquals("0", result.getNext().getNext().getNext().getNext().getValue());
		assertNull(result.getNext().getNext().getNext().getNext().getNext());
	}
	
	@Test
	@DisplayName("when remove duplicates with linked list with more than once repetition elements must return list without repetition")
	public void case_four() {
		LinkedNode<String> result = new RemoveDuplicates().removeDuplicates(withoutMultiplesRepetition());
		
		assertEquals("A", result.getValue());
		assertEquals("2", result.getNext().getValue());
		assertEquals("F0", result.getNext().getNext().getValue());
		assertEquals("6", result.getNext().getNext().getNext().getValue());
		assertNull(result.getNext().getNext().getNext().getNext());
	}
	
	private LinkedNode<String> withoutRepetition() {
		return new LinkedNode<>("A", new LinkedNode<>("2", new LinkedNode<>("F0", null)));
	}
	
	private LinkedNode<String> withoutSingleRepetition() {
		return new LinkedNode<>("A", 
				new LinkedNode<>("A", 
						new LinkedNode<>("F0", 
								new LinkedNode<>("6", 
										new LinkedNode<>("F0", 
												new LinkedNode<>("9", 
														new LinkedNode<>("0", null)
					))))));
	}
	
	private LinkedNode<String> withoutMultiplesRepetition() {
		return new LinkedNode<>("A", 
					new LinkedNode<>("2", 
							new LinkedNode<>("F0", 
									new LinkedNode<>("6", 
											new LinkedNode<>("F0", 
													new LinkedNode<>("F0", 
															new LinkedNode<>("A", null)
						))))));
	}
}
