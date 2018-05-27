package linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import linkedlist.helper.LinkedNode;

public class KthElementTest {

	@Test
	@DisplayName("when find k last element with empty linked list must return null")
	public void case_one() {
		LinkedNode<String> baseLinkedList = new LinkedNode<String>();
		String result = new KthElement().get(baseLinkedList, 3);
		assertNull(result);
	}
	
	@Test
	@DisplayName("when find 3rd last element with linked list with 3 elements must return first")
	public void case_two() {
		LinkedNode<String> baseLinkedList = getBaseLinkedList();
		String result = new KthElement().get(baseLinkedList, 3);
		assertEquals("100", result);
	}
	
	@Test
	@DisplayName("when find 1st last element with linked list with 3 elements must return last")
	public void case_four() {
		LinkedNode<String> baseLinkedList = getBaseLinkedList();
		String result = new KthElement().get(baseLinkedList, 1);
		assertEquals("false", result);
	}
	
	@Test
	@DisplayName("when find 2nd last element with linked list with 3 elements must return middle element")
	public void case_five() {
		LinkedNode<String> baseLinkedList = getBaseLinkedList();
		String result = new KthElement().get(baseLinkedList, 2);
		assertEquals("Duck", result);
	}
	
	@Test
	@DisplayName("when find 4th last element with linked list with 3 elements must return null")
	public void case_three() {
		LinkedNode<String> baseLinkedList = getBaseLinkedList();
		String result = new KthElement().get(baseLinkedList, 4);
		assertNull(result);
	}
	
	private LinkedNode<String> getBaseLinkedList() {
		return new LinkedNode<String>("100", new LinkedNode<String>("Duck", new LinkedNode<String>("false", null)));
	}
	
}
