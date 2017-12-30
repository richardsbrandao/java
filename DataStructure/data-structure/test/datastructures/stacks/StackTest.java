package datastructures.stacks;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.util.ReflectionTestUtils;

public class StackTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void construct_stack_with_default_values() {
		Stack<Object> stack = new Stack<>();
		
		assertEquals(Integer.valueOf(0), getSize(stack));
		assertEquals(10, getElements(stack).length);
		assertEquals(Integer.valueOf(10), getCapacity(stack));
		assertEquals(Boolean.TRUE, getStrategy(stack));
	}

	@Test
	public void construct_stack_with_custom_capacity() {
		Stack<Object> stack = new Stack<>(30);
		
		assertEquals(Integer.valueOf(0), getSize(stack));
		assertEquals(30, getElements(stack).length);
		assertEquals(Integer.valueOf(30), getCapacity(stack));
		assertEquals(Boolean.TRUE, getStrategy(stack));
	}
	
	@Test
	public void construct_stack_with_custom_capacity_and_dynamic_strategy() {
		Stack<Object> stack = new Stack<>(20, Boolean.FALSE);
		
		assertEquals(Integer.valueOf(0), getSize(stack));
		assertEquals(20, getElements(stack).length);
		assertEquals(Integer.valueOf(20), getCapacity(stack));
		assertEquals(Boolean.FALSE, getStrategy(stack));
	}
	
	@Test
	public void add_element_to_stack() {
		Stack<String> stack = new Stack<>();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		
		Object[] elements = getElements(stack);
		assertEquals("1", elements[0]);
		assertEquals("2", elements[1]);
		assertEquals("3", elements[2]);
		assertEquals(Integer.valueOf(3), getSize(stack));
	}
	
	@Test
	public void add_elemento_to_stack_increasing_capacity() {
		Stack<String> stack = new Stack<>(3);
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		
		assertEquals(Integer.valueOf(4), getSize(stack));
		assertEquals(Integer.valueOf(4), getCapacity(stack));
	}
	
	@Test
	public void peek_element_in_empty_list() {
		Stack<String> stack = new Stack<>(3);
		exception.expect(IllegalAccessError.class);
		stack.peek();
	}
	
	@Test
	public void peek_element_in_list() {
		Stack<String> stack = new Stack<>(3);
		stack.push("1"); stack.push("2");
		
		assertEquals("2", stack.peek());
	}
	
	@Test
	public void peek_element_twice_does_not_change() {
		Stack<String> stack = new Stack<>();
		stack.push("1"); stack.push("2");
		
		assertEquals("2", stack.peek());
		assertEquals("2", stack.peek());
	}
	
	@Test
	public void pop_element_in_empty_list() {
		Stack<String> stack = new Stack<>();
		exception.expect(IllegalAccessError.class);
		stack.pop();
	}
	
	@Test
	public void pop_element_once() {
		Stack<String> stack = new Stack<>();
		stack.push("1"); stack.push("2"); stack.push("3");
		
		assertEquals("3", stack.pop());
		assertEquals(Integer.valueOf(2), getSize(stack));
		Object[] elements = getElements(stack);
		assertEquals("1", elements[0]);
		assertEquals("2", elements[1]);
	}
	
	@Test
	public void pop_element_twice_should_change() {
		Stack<String> stack = new Stack<>();
		stack.push("1"); stack.push("2"); stack.push("3");
		
		assertEquals("3", stack.pop());
		assertEquals("2", stack.pop());
		assertEquals(Integer.valueOf(1), getSize(stack));
		Object[] elements = getElements(stack);
		assertEquals("1", elements[0]);
	}
	
	@Test
	public void size_of_empty_stack() {
		Stack<String> stack = new Stack<>();
		assertEquals(0, stack.size());
	}
	
	@Test
	public void size_of_non_empty_stack() {
		Stack<String> stack = new Stack<>();
		stack.push("1"); 
		assertEquals(1, stack.size());
	}
	
	@Test
	public void is_empty_for_empty_stack() {
		Stack<String> stack = new Stack<>();
		assertTrue(stack.isEmpty());
	}
	
	@Test
	public void is_empty_for_non_empty_stack() {
		Stack<String> stack = new Stack<>();
		stack.push("1");
		assertFalse(stack.isEmpty());
	}
	
	@Test
	public void is_full_for_empty_stack() {
		Stack<String> stack = new Stack<>();
		assertFalse(stack.isFull());
	}
	
	@Test
	public void is_full_for_non_full_stack() {
		Stack<String> stack = new Stack<>();
		stack.push("1"); stack.push("2"); stack.push("3");
		assertFalse(stack.isFull());
	}
	
	@Test
	public void is_full_for_full_stack() {
		Stack<String> stack = new Stack<>(3);
		stack.push("1"); stack.push("2"); stack.push("3");
		assertTrue(stack.isFull());
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Object> T[] getElements(Stack<T> stack) {
		return (T[]) ReflectionTestUtils.getField(stack, "elements");
	}

	private <T extends Object> Integer getSize(Stack<T> stack) {
		return (Integer) ReflectionTestUtils.getField(stack, "size");
	}
	
	private <T extends Object> Integer getCapacity(Stack<T> stack) {
		return (Integer) ReflectionTestUtils.getField(stack, "capacity");
	}
	
	
	private <T extends Object> Boolean getStrategy(Stack<T> stack) {
		return (Boolean) ReflectionTestUtils.getField(stack, "dynamic");
	}

	
}
