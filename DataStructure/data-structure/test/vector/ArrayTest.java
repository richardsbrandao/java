package vector;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(JUnit4.class)
public class ArrayTest {

	@Test
	public void initialize_array_with_right_params() {
		Array array = new Array(3);

		String[] elements = (String[]) ReflectionTestUtils.getField(array, "elements");
		Integer size = (Integer) ReflectionTestUtils.getField(array, "size");
		
		assertEquals(3, elements.length);
		assertEquals(Integer.valueOf(0), size);
	}
	
	@Test
	public void when_add_size_changes() {
		Array array = new Array(3);
		assertEquals(0, array.size());
		
		array.add("Richard");
		assertEquals(1, array.size());
	}
	
	@Test
	public void when_add_beyond_capacity_the_capacity_increases() {
		Array array = new Array(3);
		array.add("Richard");
		array.add("Ketherin");
		array.add("Carlos");
		array.add("Cristina");
		
		assertEquals(4, array.size());
		String[] elements = (String[]) ReflectionTestUtils.getField(array, "elements");
		assertEquals(6, elements.length);
	}
	
	@Test
	public void error_on_tring_to_remove_elements_in_wrong_position() {
		Array array = new Array(5);
		
		array.add("A");
		array.add("B");
		
		array.remove(4);
		assertEquals(2, array.size());
		
		array.remove(8);
		assertEquals(2, array.size());
		
		array.remove(-1);
		assertEquals(2, array.size());
	}
	
	@Test
	public void when_remove_should_organize_elements() {
		Array array = new Array(5);
		array.add("A");
		array.add("B");
		array.add("C");
		array.add("1");
		array.add("D");
		
		array.remove(3);
		
		assertEquals(4, array.size());
		String[] elements = (String[]) ReflectionTestUtils.getField(array, "elements");
		assertEquals("A", elements[0]);
		assertEquals("B", elements[1]);
		assertEquals("C", elements[2]);
		assertEquals("D", elements[3]);
	}
	
	@Test
	public void get_valid_element() {
		Array array = new Array(5);
		array.add("A");
		array.add("B");
		
		assertEquals("A", array.get(0));
		assertEquals("B", array.get(1));
	}
	
	@Test
	public void get_invalid_element_returns_null() {
		Array element = new Array(4);
		
		assertNull(element.get(0));		
		assertNull(element.get(8));
	}
	
	@Test
	public void check_if_array_is_empty() {
		Array array = new Array(5);
		assertTrue(array.isEmpty());
		
		array.add("A");
		assertFalse(array.isEmpty());
		
		array.remove(0);
		assertTrue(array.isEmpty());
	}
	
	@Test
	public void clear_array() {
		Array array = new Array(4);
		array.add("A");
		array.clear();
		assertEquals(0, array.size());
		assertNull(array.get(0));
	}
	
	@Test
	public void add_all_merge_two_arrays() {
		Array firstArray = new Array(4);
		firstArray.add("A");
		firstArray.add("B");
		Array secondArray = new Array(7);
		secondArray.add("C");
		secondArray.add("D");
		secondArray.add("E");
		
		firstArray.addAll(secondArray);
		assertEquals(5, firstArray.size());
		String[] elements = (String[]) ReflectionTestUtils.getField(firstArray, "elements");
		assertEquals("A", elements[0]);
		assertEquals("B", elements[1]);
		assertEquals("C", elements[2]);
		assertEquals("D", elements[3]);
		assertEquals("E", elements[4]);
	}
	
	@Test
	public void element_contains_in_array() {
		Array array = new Array(4);
		array.add("A");
		array.add("B");
		
		assertTrue(array.contains("A"));
		assertFalse(array.contains("a"));
		assertFalse(array.contains("V"));
		assertFalse(array.contains(null));
	}
}
