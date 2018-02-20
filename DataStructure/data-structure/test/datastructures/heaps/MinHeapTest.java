package datastructures.heaps;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.Heap;

public class MinHeapTest {

	@Test
	public void when_add_with_empty_heap_must_insert_in_the_head() {
		Heap<Integer> minHeap = new MinHeap<>(Integer.class);
		minHeap.add(10);
		
		Integer[] table = getMinHeapTable(minHeap);
		int size = getSize(minHeap);
		
		assertEquals(1, size);
		assertEquals(new Integer(10), table[0]);
	}
	
	@Test
	public void when_add_with_element_greather_than_root_in_a_single_element_heap_must_insert_in_the_left() {
		Heap<Integer> minHeap = new MinHeap<>(Integer.class);
		minHeap.add(10);
		minHeap.add(20);
		
		Integer[] table = getMinHeapTable(minHeap);
		int size = getSize(minHeap);
		
		assertEquals(2, size);
		assertEquals(new Integer(10), table[0]);
		assertEquals(new Integer(20), table[1]);
	}
	
	@Test
	public void when_add_with_elements_in_natural_order_must_insert_all_elements_satisfying_all_the_heap_properties() {
		Heap<Integer> minHeap = new MinHeap<>(Integer.class);
		minHeap.add(10); minHeap.add(20); minHeap.add(30); minHeap.add(40); minHeap.add(50); minHeap.add(60); minHeap.add(70);
		
		Integer[] table = getMinHeapTable(minHeap);
		int size = getSize(minHeap);
		
		assertEquals(7, size);
		assertEquals(new Integer(10), table[0]);
		assertEquals(new Integer(20), table[1]);
		assertEquals(new Integer(30), table[2]);
		assertEquals(new Integer(40), table[3]);
		assertEquals(new Integer(50), table[4]);
		assertEquals(new Integer(60), table[5]);
		assertEquals(new Integer(70), table[6]);
	}
	
	@Test
	public void when_add_with_elements_in_scrambled_order_must_insert_satisfying_all_the_heap_properties_example_one() {
		Heap<Integer> minHeap = example();
		
		Integer[] table = getMinHeapTable(minHeap);
		int size = getSize(minHeap); 
		
		assertEquals(7, size);
		assertEquals(new Integer(5), table[0]);
		assertEquals(new Integer(35), table[1]);
		assertEquals(new Integer(10), table[2]);
		assertEquals(new Integer(80), table[3]);
		assertEquals(new Integer(40), table[4]);
		assertEquals(new Integer(30), table[5]);
		assertEquals(new Integer(25), table[6]);
	}

	@Test
	public void when_add_with_elements_in_scrambled_order_must_insert_satisfying_all_the_heap_properties_example_two() {
		Heap<Integer> minHeap = example2();
		
		Integer[] table = getMinHeapTable(minHeap);
		int size = getSize(minHeap); 
		
		assertEquals(14, size);
		assertEquals(new Integer(5), table[0]);
		assertEquals(new Integer(15), table[1]);
		assertEquals(new Integer(10), table[2]);
		assertEquals(new Integer(35), table[3]);
		assertEquals(new Integer(23), table[4]);
		assertEquals(new Integer(30), table[5]);
		assertEquals(new Integer(25), table[6]);
		assertEquals(new Integer(80), table[7]);
		assertEquals(new Integer(45), table[8]);
		assertEquals(new Integer(40), table[9]);
		assertEquals(new Integer(50), table[10]);
		assertEquals(new Integer(55), table[11]);
		assertEquals(new Integer(75), table[12]);
		assertEquals(new Integer(65), table[13]);
	}

	private Heap<Integer> example2() {
		Heap<Integer> minHeap = example();
		minHeap.add(15); minHeap.add(45); minHeap.add(23); minHeap.add(50); minHeap.add(55); minHeap.add(75); minHeap.add(65);
		return minHeap;
	}
	
	@Test
	public void when_size_with_empty_min_heap_must_return_zero() {
		Heap<Integer> minHeap = new MinHeap<>(Integer.class);

		assertEquals(0, minHeap.size());
	}
	
	@Test
	public void when_size_with_n_elements_in_min_heap_must_return_the_right_number() {
		Heap<Integer> minHeap = example();
		
		assertEquals(7, minHeap.size());
	}
	
	@Test(expected=IllegalAccessError.class)
	public void when_remove_with_empty_heap_must_throw_error() {
		Heap<Integer> minHeap = new MinHeap<>(Integer.class);
		
		minHeap.remove();
	}
	
	@Test
	public void when_remove_with_single_element_heap_must_remove_it_and_turn_a_empty_heap() {
		Heap<Integer> minHeap = new MinHeap<>(Integer.class);
		minHeap.add(10);
		Integer removedValue = minHeap.remove();
		
		assertEquals(removedValue, removedValue);
		
		int size = getSize(minHeap);
		Integer[] minHeapTable = getMinHeapTable(minHeap);
		
		assertEquals(0, size);
		assertNull(minHeapTable[0]);
	}
	
	@Test
	public void when_remove_with_root_element_in_example_min_heap_must_remove_it_and_elect_the_smaller_element_as_root() {
		Heap<Integer> minHeap = example();
		
		Integer removedElement = minHeap.remove();
		assertEquals(new Integer(5), removedElement);
		
		int size = getSize(minHeap);
		Integer[] table = getMinHeapTable(minHeap);
		
		assertEquals(6, size);
		assertEquals(new Integer(10), table[0]);
		assertEquals(new Integer(35), table[1]);
		assertEquals(new Integer(25), table[2]);
		assertEquals(new Integer(80), table[3]);
		assertEquals(new Integer(40), table[4]);
		assertEquals(new Integer(30), table[5]);
	}
	
	@Test
	public void when_remove_with_root_element_in_example_2_min_heap_must_remove_it_and_elect_the_smaller_element_as_root() {
		Heap<Integer> minHeap = example2();
		
		Integer removedElement = minHeap.remove();
		assertEquals(new Integer(5), removedElement);
		
		int size = getSize(minHeap);
		Integer[] table = getMinHeapTable(minHeap);
		
		assertEquals(13, size);
		assertEquals(new Integer(10), table[0]);
		assertEquals(new Integer(15), table[1]);
		assertEquals(new Integer(25), table[2]);
		assertEquals(new Integer(35), table[3]);
		assertEquals(new Integer(23), table[4]);
		assertEquals(new Integer(30), table[5]);
		assertEquals(new Integer(65), table[6]);
		assertEquals(new Integer(80), table[7]);
		assertEquals(new Integer(45), table[8]);
		assertEquals(new Integer(40), table[9]);
		assertEquals(new Integer(50), table[10]);
		assertEquals(new Integer(55), table[11]);
		assertEquals(new Integer(75), table[12]);
	}
	
	private Heap<Integer> example() {
		Heap<Integer> minHeap = new MinHeap<>(Integer.class);
		minHeap.add(40); minHeap.add(30); minHeap.add(10); minHeap.add(80); minHeap.add(35); minHeap.add(5); minHeap.add(25);
		return minHeap;
	}
	
	private int getSize(Heap<Integer> minHeap) {
		return (int) ReflectionTestUtils.getField(minHeap, "size");
	}

	private Integer[] getMinHeapTable(Heap<Integer> minHeap) {
		return (Integer[]) ReflectionTestUtils.getField(minHeap, "elements");
	}
}
