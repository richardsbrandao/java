package datastructures.queues;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.errors.QueueOverflowError;

public class QueueArrayTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void construct_queue_with_default_values() {
		QueueArray<Object> queue = new QueueArray<>();
		
		assertEquals(Integer.valueOf(0), getSize(queue));
		assertEquals(10, getElements(queue).length);
		assertEquals(Integer.valueOf(10), getCapacity(queue));
		assertEquals(Boolean.TRUE, getStrategy(queue));
	}

	@Test
	public void construct_queue_with_custom_capacity() {
		QueueArray<Object> queue = new QueueArray<>(30);
		
		assertEquals(Integer.valueOf(0), getSize(queue));
		assertEquals(30, getElements(queue).length);
		assertEquals(Integer.valueOf(30), getCapacity(queue));
		assertEquals(Boolean.TRUE, getStrategy(queue));
	}
	
	@Test
	public void construct_queue_with_custom_capacity_and_dynamic_strategy() {
		QueueArray<Object> queue = new QueueArray<>(20, Boolean.FALSE);
		
		assertEquals(Integer.valueOf(0), getSize(queue));
		assertEquals(20, getElements(queue).length);
		assertEquals(Integer.valueOf(20), getCapacity(queue));
		assertEquals(Boolean.FALSE, getStrategy(queue));
	}
	
	@Test
	public void add_element_to_queue() {
		QueueArray<String> queue = new QueueArray<>();
		queue.push("1");
		queue.push("2");
		queue.push("3");
		
		Object[] elements = getElements(queue);
		assertEquals("1", elements[0]);
		assertEquals("2", elements[1]);
		assertEquals("3", elements[2]);
		assertEquals(Integer.valueOf(3), getSize(queue));
	}
	
	@Test
	public void add_elemento_to_queue_increasing_capacity() {
		QueueArray<String> queue = new QueueArray<>(3);
		queue.push("1");
		queue.push("2");
		queue.push("3");
		queue.push("4");
		
		assertEquals(Integer.valueOf(4), getSize(queue));
		assertEquals(Integer.valueOf(4), getCapacity(queue));
	}
	
	@Test
	public void add_element_to_queue_throws_queue_overflow() {
		QueueArray<String> queue = new QueueArray<>(3, false);
		exception.expect(QueueOverflowError.class);
		queue.push("1"); queue.push("2"); queue.push("3"); queue.push("4");
	}
	
	@Test
	public void peek_element_in_empty_list() {
		QueueArray<String> queue = new QueueArray<>(3);
		exception.expect(IllegalAccessError.class);
		queue.peek();
	}
	
	@Test
	public void peek_element_in_list() {
		QueueArray<String> queue = new QueueArray<>(3);
		queue.push("1"); queue.push("2");
		
		assertEquals("1", queue.peek());
	}
	
	@Test
	public void peek_element_twice_does_not_change() {
		QueueArray<String> queue = new QueueArray<>();
		queue.push("1"); queue.push("2");
		
		assertEquals("1", queue.peek());
		assertEquals("1", queue.peek());
	}
	
	@Test
	public void pop_element_in_empty_list() {
		QueueArray<String> queue = new QueueArray<>();
		exception.expect(IllegalAccessError.class);
		queue.pop();
	}
	
	@Test
	public void pop_element_once() {
		QueueArray<String> queue = new QueueArray<>(3);
		queue.push("1"); queue.push("2"); queue.push("3");
		
		assertEquals("1", queue.pop());
		assertEquals(Integer.valueOf(2), getSize(queue));
		Object[] elements = getElements(queue);
		assertEquals("2", elements[0]);
		assertEquals("3", elements[1]);
	}
	
	@Test
	public void pop_element_twice_should_change() {
		QueueArray<String> queue = new QueueArray<>();
		queue.push("1"); queue.push("2"); queue.push("3");
		
		assertEquals("1", queue.pop());
		assertEquals("2", queue.pop());
		assertEquals(Integer.valueOf(1), getSize(queue));
		Object[] elements = getElements(queue);
		assertEquals("3", elements[0]);
	}
	
	@Test
	public void size_of_empty_queue() {
		QueueArray<String> queue = new QueueArray<>();
		assertEquals(0, queue.size());
	}
	
	@Test
	public void size_of_non_empty_queue() {
		QueueArray<String> queue = new QueueArray<>();
		queue.push("1"); 
		assertEquals(1, queue.size());
	}
	
	@Test
	public void is_empty_for_empty_queue() {
		QueueArray<String> queue = new QueueArray<>();
		assertTrue(queue.isEmpty());
	}
	
	@Test
	public void is_empty_for_non_empty_queue() {
		QueueArray<String> queue = new QueueArray<>();
		queue.push("1");
		assertFalse(queue.isEmpty());
	}
	
	@Test
	public void is_full_for_empty_queue() {
		QueueArray<String> queue = new QueueArray<>();
		assertFalse(queue.isFull());
	}
	
	@Test
	public void is_full_for_non_full_queue() {
		QueueArray<String> queue = new QueueArray<>();
		queue.push("1"); queue.push("2"); queue.push("3");
		assertFalse(queue.isFull());
	}
	
	@Test
	public void is_full_for_full_queue() {
		QueueArray<String> queue = new QueueArray<>(3);
		queue.push("1"); queue.push("2"); queue.push("3");
		assertTrue(queue.isFull());
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Object> T[] getElements(QueueArray<T> queue) {
		return (T[]) ReflectionTestUtils.getField(queue, "elements");
	}

	private <T extends Object> Integer getSize(QueueArray<T> queue) {
		return (Integer) ReflectionTestUtils.getField(queue, "size");
	}
	
	private <T extends Object> Integer getCapacity(QueueArray<T> queue) {
		return (Integer) ReflectionTestUtils.getField(queue, "capacity");
	}
	
	
	private <T extends Object> Boolean getStrategy(QueueArray<T> queue) {
		return (Boolean) ReflectionTestUtils.getField(queue, "dynamic");
	}

	
}
