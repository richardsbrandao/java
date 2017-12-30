package datastructures.lists;


import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.lists.Array;

@RunWith(JUnit4.class)
public class ArrayTest {

	@Rule
	public ExpectedException expection = ExpectedException.none();
	
	@Test
	public void initialize_array_with_right_params() {
		Array<String> array = new Array<String>(3);

		Object[] elements = (Object[]) ReflectionTestUtils.getField(array, "elements");
		Integer size = (Integer) ReflectionTestUtils.getField(array, "size");
		
		assertEquals(3, elements.length);
		assertEquals(Integer.valueOf(0), size);
	}
	
	@Test
	public void when_add_size_changes() {
		Array<String> array = new Array<String>(3);
		assertEquals(0, array.size());
		
		array.add("Richard");
		assertEquals(1, array.size());
	}
	
	@Test
	public void when_add_beyond_capacity_the_capacity_increases() {
		Array<String> array = new Array<String>(3);
		array.add("Richard");
		array.add("Ketherin");
		array.add("Carlos");
		array.add("Cristina");
		
		assertEquals(4, array.size());
		Object[] elements = (Object[]) ReflectionTestUtils.getField(array, "elements");
		assertEquals(6, elements.length);
	}
	
	@Test
	public void error_on_tring_to_remove_elements_in_wrong_position() {
		Array<String> array = new Array<String>(5);
		
		array.add("A");
		array.add("B");
		
		try {
			array.remove(4);
			fail("Must throw ArrayIndexOutOfBoundsException");
		} catch(ArrayIndexOutOfBoundsException e) {
			assertEquals(2, array.size());
		}


		try {
			array.remove(8);
			fail("Must throw ArrayIndexOutOfBoundsException");
		} catch(ArrayIndexOutOfBoundsException e) {
			assertEquals(2, array.size());
		}

		try {
			array.remove(-1);
			fail("Must throw ArrayIndexOutOfBoundsException");
		} catch(ArrayIndexOutOfBoundsException e) {
			assertEquals(2, array.size());
		}
	}
	
	@Test
	public void when_remove_should_organize_elements() {
		Array<String> array = new Array<String>(5);
		array.add("A");
		array.add("B");
		array.add("C");
		array.add("1");
		array.add("D");
		
		array.remove(3);
		
		assertEquals(4, array.size());
		Object[] elements = (Object[]) ReflectionTestUtils.getField(array, "elements");
		assertEquals("A", elements[0]);
		assertEquals("B", elements[1]);
		assertEquals("C", elements[2]);
		assertEquals("D", elements[3]);
	}
	
	@Test
	public void get_valid_element() {
		Array<String> array = new Array<String>(5);
		array.add("A");
		array.add("B");
		
		assertEquals("A", array.get(0));
		assertEquals("B", array.get(1));
	}
	
	@Test
	public void get_invalid_element_returns_null() {
		Array<String> element = new Array<String>(4);
		
		expection.expect(ArrayIndexOutOfBoundsException.class);
		assertNull(element.get(-1));

		expection.expect(ArrayIndexOutOfBoundsException.class);
		assertNull(element.get(0));		

		expection.expect(ArrayIndexOutOfBoundsException.class);
		assertNull(element.get(8));
	}
	
	@Test
	public void check_if_array_is_empty() {
		Array<String> array = new Array<String>(5);
		assertTrue(array.isEmpty());
		
		array.add("A");
		assertFalse(array.isEmpty());
		
		array.remove(0);
		assertTrue(array.isEmpty());
	}
	
	@Test
	public void clear_array() {
		Array<String> array = new Array<String>(4);
		array.add("A");
		array.clear();
		assertEquals(0, array.size());
		assertNull(array.get(0));
	}
	
	@Test
	public void add_all_merge_two_arrays() {
		Array<String> firstArray = new Array<String>(4);
		firstArray.add("A");
		firstArray.add("B");
		Array<String> secondArray = new Array<String>(7);
		secondArray.add("C");
		secondArray.add("D");
		secondArray.add("E");
		
		firstArray.addAll(secondArray);
		assertEquals(5, firstArray.size());
		Object[] elements = (Object[]) ReflectionTestUtils.getField(firstArray, "elements");
		assertEquals("A", elements[0]);
		assertEquals("B", elements[1]);
		assertEquals("C", elements[2]);
		assertEquals("D", elements[3]);
		assertEquals("E", elements[4]);
	}
	
	@Test
	public void element_contains_in_array() {
		Array<String> array = new Array<String>(4);
		array.add("A");
		array.add("B");
		
		assertTrue(array.contains("A"));
		assertFalse(array.contains("a"));
		assertFalse(array.contains("V"));
		assertFalse(array.contains(null));
	}
	
	@Test
	public void add_element_at() {
		Array<String> array = new Array<String>(4);
		array.add(0, "A");
		array.add(1, "B");
		array.add(0, "C");
		
		assertEquals("C", array.get(0));
		assertEquals("A", array.get(1));
		assertEquals("B", array.get(2));
	}
	
	@Test
	public void add_element_at_invalid_position() {
		Array<String> array = new Array<String>(4);
		array.add(0, "A");
		array.add(1, "B");
		
		expection.expect(ArrayIndexOutOfBoundsException.class);
		array.add(3, "C");
	}
	
	@Test
	public void add_element_at_full_array() {
		Array<String> array = new Array<String>(4);
		array.add(0, "A");
		array.add(1, "B");
		array.add(2, "C");
		array.add(3, "D");
		array.add(4, "E");
		
		assertEquals("A", array.get(0));
		assertEquals("B", array.get(1));
		assertEquals("C", array.get(2));
		assertEquals("D", array.get(3));
		assertEquals("E", array.get(4));
	}
}
