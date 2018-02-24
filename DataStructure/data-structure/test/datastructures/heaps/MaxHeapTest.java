package datastructures.heaps;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.Heap;

public class MaxHeapTest {
	
	@Test
	public void when_add_with_empty_heap_must_insert_in_the_head() {
		Heap<Integer> maxHeap = new MaxHeap<>(Integer.class);
		maxHeap.add(10);
		
		Integer[] table = getMaxHeapTable(maxHeap);
		int size = getSize(maxHeap);
		
		assertEquals(1, size);
		assertEquals(new Integer(10), table[0]);
	}
	
	@Test
	public void when_add_with_element_greather_than_root_in_a_single_element_heap_must_insert_in_the_left() {
		Heap<Integer> maxHeap = new MaxHeap<>(Integer.class);
		maxHeap.add(20);
		maxHeap.add(10);
		
		Integer[] table = getMaxHeapTable(maxHeap);
		int size = getSize(maxHeap);
		
		assertEquals(2, size);
		assertEquals(new Integer(20), table[0]);
		assertEquals(new Integer(10), table[1]);
	}
	
	@Test
	public void when_add_with_elements_in_desc_order_must_insert_all_elements_satisfying_all_the_heap_properties() {
		Heap<Integer> maxHeap = new MaxHeap<>(Integer.class);
		maxHeap.add(70); maxHeap.add(60); maxHeap.add(50); maxHeap.add(40); maxHeap.add(30); maxHeap.add(20); maxHeap.add(10);
		
		Integer[] table = getMaxHeapTable(maxHeap);
		int size = getSize(maxHeap);
		
		assertEquals(7, size);
		assertEquals(new Integer(70), table[0]);
		assertEquals(new Integer(60), table[1]);
		assertEquals(new Integer(50), table[2]);
		assertEquals(new Integer(40), table[3]);
		assertEquals(new Integer(30), table[4]);
		assertEquals(new Integer(20), table[5]);
		assertEquals(new Integer(10), table[6]);
	}
	
	@Test
	public void when_add_with_elements_in_scrambled_order_must_insert_satisfying_all_the_heap_properties_example_one() {
		Heap<Integer> maxHeap = example();
		
		Integer[] table = getMaxHeapTable(maxHeap);
		int size = getSize(maxHeap); 
		
		assertEquals(7, size);
		assertEquals(new Integer(80), table[0]);
		assertEquals(new Integer(40), table[1]);
		assertEquals(new Integer(25), table[2]);
		assertEquals(new Integer(30), table[3]);
		assertEquals(new Integer(35), table[4]);
		assertEquals(new Integer(5), table[5]);
		assertEquals(new Integer(10), table[6]);
	}

	@Test
	public void when_add_with_elements_in_scrambled_order_must_insert_satisfying_all_the_heap_properties_example_two() {
		Heap<Integer> maxHeap = example2();
		
		Integer[] table = getMaxHeapTable(maxHeap);
		int size = getSize(maxHeap); 

		assertEquals(14, size);
		assertEquals(new Integer(80), table[0]);
		assertEquals(new Integer(50), table[1]);
		assertEquals(new Integer(75), table[2]);
		assertEquals(new Integer(40), table[3]);
		assertEquals(new Integer(45), table[4]);
		assertEquals(new Integer(55), table[5]);
		assertEquals(new Integer(65), table[6]);
		assertEquals(new Integer(15), table[7]);
		assertEquals(new Integer(30), table[8]);
		assertEquals(new Integer(23), table[9]);
		assertEquals(new Integer(35), table[10]);
		assertEquals(new Integer(5), table[11]);
		assertEquals(new Integer(25), table[12]);
		assertEquals(new Integer(10), table[13]);
	}
	
	@Test
	public void when_add_elements_greater_than_capacity_must_resize_the_capacity() {
		Heap<Integer> maxHeap = new MaxHeap<>(Integer.class);
		for(int i = 999; i >= 0; i--) {
			maxHeap.add(i);
		}
		
		Integer[] maxHeapTable = getMaxHeapTable(maxHeap);
		int size = getSize(maxHeap);
		
		assertEquals(1600, maxHeapTable.length);
		assertEquals(1000, size);
		
		for(int i = 999, j = 0; i >= 0; i--, j++) {
			assertEquals(new Integer(i), maxHeapTable[j]);
		}
		
		for(int i = 1000; i < 1600; i++) {
			assertNull(maxHeapTable[i]);
		}
	}

	private Heap<Integer> example2() {
		Heap<Integer> maxHeap = example();
		maxHeap.add(15); maxHeap.add(45); maxHeap.add(23); maxHeap.add(50); maxHeap.add(55); maxHeap.add(75); maxHeap.add(65);
		return maxHeap;
	}
	
	@Test
	public void when_size_with_empty_max_heap_must_return_zero() {
		Heap<Integer> maxHeap = new MaxHeap<>(Integer.class);

		assertEquals(0, maxHeap.size());
	}
	
	@Test
	public void when_size_with_n_elements_in_max_heap_must_return_the_right_number() {
		Heap<Integer> maxHeap = example();
		
		assertEquals(7, maxHeap.size());
	}
	
	@Test(expected=IllegalAccessError.class)
	public void when_remove_with_empty_heap_must_throw_error() {
		Heap<Integer> maxHeap = new MaxHeap<>(Integer.class);
		
		maxHeap.remove();
	}
	
	@Test
	public void when_remove_with_single_element_heap_must_remove_it_and_turn_a_empty_heap() {
		Heap<Integer> maxHeap = new MaxHeap<>(Integer.class);
		maxHeap.add(10);
		Integer removedValue = maxHeap.remove();
		
		assertEquals(removedValue, removedValue);
		
		int size = getSize(maxHeap);
		Integer[] maxHeapTable = getMaxHeapTable(maxHeap);
		
		assertEquals(0, size);
		assertNull(maxHeapTable[0]);
	}
	
	@Test
	public void when_remove_with_root_element_in_example_max_heap_must_remove_it_and_elect_the_smaller_element_as_root() {
		Heap<Integer> maxHeap = example();
		
		Integer removedElement = maxHeap.remove();
		assertEquals(new Integer(80), removedElement);
		
		int size = getSize(maxHeap);
		Integer[] table = getMaxHeapTable(maxHeap);
		
		assertEquals(6, size);
		assertEquals(new Integer(40), table[0]);
		assertEquals(new Integer(35), table[1]);
		assertEquals(new Integer(25), table[2]);
		assertEquals(new Integer(30), table[3]);
		assertEquals(new Integer(10), table[4]);
		assertEquals(new Integer(5), table[5]);
		assertNull(table[6]);
	}
	
	@Test
	public void when_remove_with_root_element_in_example_2_max_heap_must_remove_it_and_elect_the_smaller_element_as_root() {
		Heap<Integer> maxHeap = example2();
		
		Integer removedElement = maxHeap.remove();
		assertEquals(new Integer(80), removedElement);
		
		int size = getSize(maxHeap);
		Integer[] table = getMaxHeapTable(maxHeap);
		
		assertEquals(13, size);

		assertEquals(new Integer(75), table[0]);
		assertEquals(new Integer(50), table[1]);
		assertEquals(new Integer(65), table[2]);
		assertEquals(new Integer(40), table[3]);
		assertEquals(new Integer(45), table[4]);
		assertEquals(new Integer(55), table[5]);
		assertEquals(new Integer(10), table[6]);
		assertEquals(new Integer(15), table[7]);
		assertEquals(new Integer(30), table[8]);
		assertEquals(new Integer(23), table[9]);
		assertEquals(new Integer(35), table[10]);
		assertEquals(new Integer(5), table[11]);
		assertEquals(new Integer(25), table[12]);
		assertNull(table[13]);
	}
	
	@Test(expected=IllegalAccessError.class)
	public void when_get_with_empty_heap_must_throw_error() {
		Heap<Integer> maxHeap = new MaxHeap<>(Integer.class);
		maxHeap.get();
	}
	
	@Test
	public void when_get_with_populated_max_heap_must_get_smaller_element() {
		Heap<Integer> maxHeap = example();
		
		assertEquals(new Integer(80), maxHeap.get());
	}
	
	private Heap<Integer> example() {
		Heap<Integer> maxHeap = new MaxHeap<>(Integer.class);
		maxHeap.add(40); maxHeap.add(30); maxHeap.add(10); maxHeap.add(80); maxHeap.add(35); maxHeap.add(5); maxHeap.add(25);
		return maxHeap;
	}
	
	private int getSize(Heap<Integer> maxHeap) {
		return (int) ReflectionTestUtils.getField(maxHeap, "size");
	}

	private Integer[] getMaxHeapTable(Heap<Integer> maxHeap) {
		return (Integer[]) ReflectionTestUtils.getField(maxHeap, "elements");
	}
}
