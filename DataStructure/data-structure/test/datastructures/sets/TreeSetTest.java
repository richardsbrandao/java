package datastructures.sets;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.NavigableSet;
import datastructures.maps.TreeMap;

public class TreeSetTest {
	
	@Test
	public void when_construct_must_create_empty_set() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		
		TreeMap<Integer, Boolean> map = getMap(set);
		
		assertTrue(map.isEmpty());
	}
	
	@Test(expected=NullPointerException.class)
	public void when_add_with_null_element_must_return_error() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(null);
	}
	
	@Test
	public void when_add_with_valid_element_must_add_it() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(5); set.add(8); set.add(11);
		
		TreeMap<Integer, Boolean> map = getMap(set);
		
		assertEquals(3, map.size());
		assertTrue(map.containsKey(5));
		assertTrue(map.containsKey(8));
		assertTrue(map.containsKey(11));
	}
	
	@Test
	public void when_clear_with_populated_set_must_return_instance_with_no_elements_in_map() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(5); set.add(8); set.add(11);
		set.clear();
		
		TreeMap<Integer, Boolean> map = getMap(set);
		assertEquals(0, map.size());
	}

	@Test
	public void when_is_empty_with_empty_set_must_return_true() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		
		assertTrue(set.isEmpty());
	}
	
	@Test
	public void when_is_empty_with_non_empty_set_must_return_false() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(4);
		
		assertFalse(set.isEmpty());
	}
	
	@Test
	public void when_size_with_empty_set_must_return_0() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		
		assertEquals(0, set.size());
	}
	
	@Test
	public void when_size_with_3_non_repeated_elements_must_return_3() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(4); set.add(6); set.add(5);
		
		assertEquals(3, set.size());
	}
	
	@Test
	public void when_size_with_3_non_repeated_elements_and_one_repeated_must_return_3() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(4); set.add(6); set.add(5); set.add(5);
		
		assertEquals(3, set.size());
	}
	
	@Test
	public void when_contains_with_non_existing_element_in_set_must_return_false() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(4); set.add(6); set.add(5);
		
		assertFalse(set.contains(10));
	}
	
	@Test
	public void when_contains_with_existing_element_in_set_must_return_true() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(4); set.add(6); set.add(5);
		
		assertTrue(set.contains(5));
	}
	
	@Test(expected=NullPointerException.class)
	public void when_contains_with_null_element_must_return_error() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		
		set.contains(null);
	}
	
	@Test
	public void when_elements_with_populated_set_must_return_in_ascending_order() {
		TreeSet<Integer> set = example();
		Object[] elements = set.elements();
		
		assertEquals(new Integer(1), elements[0]);
		assertEquals(new Integer(4), elements[1]);
		assertEquals(new Integer(5), elements[2]);
		assertEquals(new Integer(6), elements[3]);
		assertEquals(new Integer(11), elements[4]);
		assertEquals(new Integer(12), elements[5]);
	}
	
	@Test
	public void when_highest_with_greater_than_all_elements_in_set_must_return_null() {
		TreeSet<Integer> set = example();
		
		assertNull(set.highest(15));
	}
	
	@Test
	public void when_highest_with_element_smaller_than_all_elements_in_set_must_return_the_smaller_element() {
		TreeSet<Integer> set = example();
		
		assertEquals(new Integer(1), set.highest(0));
	}
	
	@Test
	public void when_highest_with_some_element_must_return_the_very_first_element_after_the_key() {
		TreeSet<Integer> set = example();
		
		assertEquals(new Integer(6), set.highest(5));
	}
	
	@Test
	public void when_lowest_with_smaller_than_all_elements_in_set_must_return_null() {
		TreeSet<Integer> set = example();
		
		assertNull(set.lowest(0));
	}
	
	@Test
	public void when_lowest_with_element_greatest_than_all_elements_in_set_must_return_the_greatest_element() {
		TreeSet<Integer> set = example();
		
		assertEquals(new Integer(12), set.lowest(15));
	}
	
	@Test
	public void when_lowest_with_some_element_must_return_the_very_first_element_before_the_key() {
		TreeSet<Integer> set = example();
		
		assertEquals(new Integer(5), set.lowest(6));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void when_sub_set_with_from_greater_than_to_must_return_error() {
		TreeSet<Integer> set = example();
		
		set.subSet(10, 5, false);
	}
	
	@Test(expected=NullPointerException.class)
	public void when_sub_set_with_from_null_must_return_error() {
		TreeSet<Integer> set = example();

		set.subSet(null, 5, false);
	}
	
	@Test(expected=NullPointerException.class)
	public void when_sub_set_with_to_null_must_return_error() {
		TreeSet<Integer> set = example();
		
		set.subSet(10, null, false);
	}
	
	@Test
	public void when_sub_set_with_from_to_out_of_range_must_return_empty_set() {
		TreeSet<Integer> set = example();
		NavigableSet<Integer> subSet = set.subSet(100, 150, false);
		assertTrue(subSet.isEmpty());
	}
	
	@Test
	public void when_sub_set_with_from_to_in_range_must_return_set_with_all_elements_in_range() {
		TreeSet<Integer> set = example();
		NavigableSet<Integer> subSet = set.subSet(10, 15, false);
		
		TreeMap<Integer, Boolean> subSetMap = getMap(subSet);
		
		assertEquals(2, subSetMap.size());
		assertTrue(subSetMap.containsKey(11));
		assertTrue(subSetMap.containsKey(12));
	}

	@Test
	public void when_sub_set_with_from_to_in_range_and_inclusive_flag_must_return_all_elements_in_range_inclusive_from_and_to_if_exists() {
		TreeSet<Integer> set = example();
		NavigableSet<Integer> subSet = set.subSet(6, 12, true);
		
		TreeMap<Integer, Boolean> subSetMap = getMap(subSet);
		
		assertEquals(3, subSetMap.size());
		
		assertTrue(subSetMap.containsKey(6));
		assertTrue(subSetMap.containsKey(11));
		assertTrue(subSetMap.containsKey(12));
	}
	
	@Test(expected=NullPointerException.class)
	public void when_tail_with_from_null_must_return_error() {
		TreeSet<Integer> set = example();
		set.tailSet(null, false);
	}
	
	@Test
	public void when_tail_with_from_greater_than_all_elements_must_return_empty_set() {
		TreeSet<Integer> set = example();
		NavigableSet<Integer> tailSet = set.tailSet(20, false);
		assertTrue(tailSet.isEmpty());
	}
	
	@Test
	public void when_tail_with_valid_from_must_return_a_set_with_all_elements_in_greater_than_it() {
		TreeSet<Integer> set = example();
		NavigableSet<Integer> tailSet = set.tailSet(8, false);
		
		TreeMap<Integer, Boolean> subSetMap = getMap(tailSet);
		assertEquals(2, subSetMap.size());
		assertTrue(subSetMap.containsKey(11));
		assertTrue(subSetMap.containsKey(12));
	}
	
	@Test
	public void when_tail_with_inclusive_flag_must_return_tail_set_with_from_if_exists() {
		TreeSet<Integer> set = example();
		NavigableSet<Integer> headSet = set.tailSet(11, true);
		
		TreeMap<Integer, Boolean> subSetMap = getMap(headSet);
		assertEquals(2, subSetMap.size());
		assertTrue(subSetMap.containsKey(11));
		assertTrue(subSetMap.containsKey(12));
	}
	
	@Test
	public void when_tail_with_inclusive_flag_must_not_return_tail_set_with_from_if_not_exists() {
		TreeSet<Integer> set = example();
		NavigableSet<Integer> headSet = set.tailSet(10, true);
		
		TreeMap<Integer, Boolean> subSetMap = getMap(headSet);
		assertEquals(2, subSetMap.size());
		assertTrue(subSetMap.containsKey(11));
		assertTrue(subSetMap.containsKey(12));
	}
	
	@Test(expected=NullPointerException.class)
	public void when_head_with_from_null_must_return_error() {
		TreeSet<Integer> set = example();
		set.headSet(null, false);
	}
	
	@Test
	public void when_head_with_from_smaller_than_all_elements_must_return_empty_set() {
		TreeSet<Integer> set = example();
		NavigableSet<Integer> headSet = set.headSet(0, false);
		assertTrue(headSet.isEmpty());
	}
	
	@Test
	public void when_head_with_valid_from_must_return_a_set_with_all_elements_in_smaller_than_it() {
		TreeSet<Integer> set = example();
		NavigableSet<Integer> headSet = set.headSet(8, false);
		
		TreeMap<Integer, Boolean> subSetMap = getMap(headSet);
		assertEquals(4, subSetMap.size());
		assertTrue(subSetMap.containsKey(4));
		assertTrue(subSetMap.containsKey(5));
		assertTrue(subSetMap.containsKey(6));
		assertTrue(subSetMap.containsKey(1));
	}
	
	@Test
	public void when_head_with_inclusive_flag_must_return_head_set_with_from_if_exists() {
		TreeSet<Integer> set = example();
		NavigableSet<Integer> headSet = set.headSet(4, true);
		
		TreeMap<Integer, Boolean> subSetMap = getMap(headSet);
		assertEquals(2, subSetMap.size());
		assertTrue(subSetMap.containsKey(4));
		assertTrue(subSetMap.containsKey(1));
	}
	
	@Test
	public void when_head_with_inclusive_flag_must_not_return_head_set_with_from_if_not_exists() {
		TreeSet<Integer> set = example();
		NavigableSet<Integer> headSet = set.headSet(2, true);
		
		TreeMap<Integer, Boolean> subSetMap = getMap(headSet);
		assertEquals(1, subSetMap.size());
		assertTrue(subSetMap.containsKey(1));
	}
	
	private TreeSet<Integer> example() {
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(4); set.add(6); set.add(5); set.add(1); set.add(12); set.add(11);
		return set;
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Comparable<T>> TreeMap<T, Boolean> getMap(NavigableSet<T> subSet) {
		return (TreeMap<T, Boolean>) ReflectionTestUtils.getField(subSet, "map");
	}

}
