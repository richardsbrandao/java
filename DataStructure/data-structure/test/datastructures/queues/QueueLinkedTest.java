package datastructures.queues;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.Queue;
import datastructures.base.linked.Node;
import datastructures.errors.QueueOverflowError;

public class QueueLinkedTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void construct_queue_with_default_values() {
		Queue<Object> queue = new QueueLinked<>();
		
		assertEquals(Integer.valueOf(0), getSize(queue));
		assertNull(getElements(queue));
		assertEquals(Integer.valueOf(10), getCapacity(queue));
		assertEquals(Boolean.TRUE, getStrategy(queue));
	}

	@Test
	public void construct_queue_with_custom_capacity() {
		Queue<Object> queue = new QueueLinked<>(30);
		
		assertEquals(Integer.valueOf(0), getSize(queue));
		assertNull(getElements(queue));
		assertEquals(Integer.valueOf(30), getCapacity(queue));
		assertEquals(Boolean.TRUE, getStrategy(queue));
	}
	
	@Test
	public void construct_queue_with_custom_capacity_and_dynamic_strategy() {
		Queue<Object> queue = new QueueLinked<>(20, Boolean.FALSE);
		
		assertEquals(Integer.valueOf(0), getSize(queue));
		assertNull(getElements(queue));
		assertEquals(Integer.valueOf(20), getCapacity(queue));
		assertEquals(Boolean.FALSE, getStrategy(queue));
	}
	
	@Test
	public void add_element_to_queue() {
		Queue<String> queue = new QueueLinked<>();
		queue.push("1");
		queue.push("2");
		queue.push("3");
		
		Node<String> elements = getElements(queue);
		assertEquals("1", elements.value());
		assertEquals("2", elements.next().value());
		assertEquals("3", elements.next().next().value());
		assertEquals(Integer.valueOf(3), getSize(queue));
	}
	
	@Test
	public void add_elemento_to_queue_increasing_capacity() {
		Queue<String> queue = new QueueLinked<>(3);
		queue.push("1");
		queue.push("2");
		queue.push("3");
		queue.push("4");
		
		assertEquals(Integer.valueOf(4), getSize(queue));
		assertEquals(Integer.valueOf(4), getCapacity(queue));
	}
	
	@Test
	public void add_element_to_queue_throws_queue_overflow() {
		Queue<String> queue = new QueueLinked<>(3, false);
		exception.expect(QueueOverflowError.class);
		queue.push("1"); queue.push("2"); queue.push("3"); queue.push("4");
	}
	
	@Test
	public void peek_element_in_empty_list() {
		Queue<String> queue = new QueueLinked<>(3);
		exception.expect(IllegalAccessError.class);
		queue.peek();
	}
	
	@Test
	public void peek_element_in_list() {
		Queue<String> queue = new QueueLinked<>(3);
		queue.push("1"); queue.push("2");
		
		assertEquals("1", queue.peek());
	}
	
	@Test
	public void peek_element_twice_does_not_change() {
		Queue<String> queue = new QueueLinked<>();
		queue.push("1"); queue.push("2");
		
		assertEquals("1", queue.peek());
		assertEquals("1", queue.peek());
	}
	
	@Test
	public void pop_element_in_empty_list() {
		Queue<String> queue = new QueueLinked<>();
		exception.expect(IllegalAccessError.class);
		queue.pop();
	}
	
	@Test
	public void pop_element_once() {
		Queue<String> queue = new QueueLinked<>(3);
		queue.push("1"); queue.push("2"); queue.push("3");
		
		assertEquals("1", queue.pop());
		assertEquals(Integer.valueOf(2), getSize(queue));
		Node<String> elements = getElements(queue);
		assertEquals("2", elements.value());
		assertEquals("3", elements.next().value());
	}
	
	@Test
	public void pop_element_twice_should_change() {
		Queue<String> queue = new QueueLinked<>();
		queue.push("1"); queue.push("2"); queue.push("3");
		
		assertEquals("1", queue.pop());
		assertEquals("2", queue.pop());
		assertEquals(Integer.valueOf(1), getSize(queue));
		Node<String> elements = getElements(queue);
		assertEquals("3", elements.value());
	}
	
	@Test
	public void size_of_empty_queue() {
		Queue<String> queue = new QueueLinked<>();
		assertEquals(0, queue.size());
	}
	
	@Test
	public void size_of_non_empty_queue() {
		Queue<String> queue = new QueueLinked<>();
		queue.push("1"); 
		assertEquals(1, queue.size());
	}
	
	@Test
	public void is_empty_for_empty_queue() {
		Queue<String> queue = new QueueLinked<>();
		assertTrue(queue.isEmpty());
	}
	
	@Test
	public void is_empty_for_non_empty_queue() {
		Queue<String> queue = new QueueLinked<>();
		queue.push("1");
		assertFalse(queue.isEmpty());
	}
	
	@Test
	public void is_full_for_empty_queue() {
		Queue<String> queue = new QueueLinked<>();
		assertFalse(queue.isFull());
	}
	
	@Test
	public void is_full_for_non_full_queue() {
		Queue<String> queue = new QueueLinked<>();
		queue.push("1"); queue.push("2"); queue.push("3");
		assertFalse(queue.isFull());
	}
	
	@Test
	public void is_full_for_full_queue() {
		Queue<String> queue = new QueueLinked<>(3);
		queue.push("1"); queue.push("2"); queue.push("3");
		assertTrue(queue.isFull());
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Object> Node<T> getElements(Queue<T> queue) {
		return (Node<T>) ReflectionTestUtils.getField(queue, "head");
	}

	private <T extends Object> Integer getSize(Queue<T> queue) {
		return (Integer) ReflectionTestUtils.getField(queue, "size");
	}
	
	private <T extends Object> Integer getCapacity(Queue<T> queue) {
		return (Integer) ReflectionTestUtils.getField(queue, "capacity");
	}
	
	
	private <T extends Object> Boolean getStrategy(Queue<T> queue) {
		return (Boolean) ReflectionTestUtils.getField(queue, "dynamic");
	}

	
}
