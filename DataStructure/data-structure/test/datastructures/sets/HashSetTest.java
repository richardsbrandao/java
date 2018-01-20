package datastructures.sets;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.maps.HashMap;

@RunWith(JUnit4.class)
public class HashSetTest {

	@Test
	public void when_construct_with_map_properties_must_change_internal_map() {
		HashSet<Object> set = new HashSet<>(10, 0.55f);
		
		HashMap<Object, Boolean> map = getMap(set);

		assertTrue(map.isEmpty());
		assertEquals(0.55f, getLoadFactorFrom(map), 0);
		assertEquals(10, getBucketsFrom(map));
	}

	@Test
	public void when_construct_without_properties_must_start_with_initial_hash_map_internal_properties() {
		HashSet<Object> set = new HashSet<>();
		
		HashMap<Object, Boolean> map = getMap(set);

		assertTrue(map.isEmpty());
		assertEquals(0.75f, getLoadFactorFrom(map), 0);
		assertEquals(16, getBucketsFrom(map));
	}
	
	@Test
	public void when_add_elements_must_add_in_hash_map() {
		HashSet<String> set = new HashSet<>();
		set.add("Richard");
		
		HashMap<String,Boolean> map = getMap(set);
		assertTrue(map.containsKey("Richard"));
		assertTrue(map.get("Richard"));
	}
	
	@Test
	public void when_add_elements_with_duplicate_entries_must_add_only_one() {
		HashSet<String> set = new HashSet<>();
		set.add("Richard"); set.add("Richard") ;set.add("Richard");
		
		HashMap<String,Boolean> map = getMap(set);
		assertTrue(map.containsKey("Richard"));
		assertEquals(1, map.size());
	}
	
	@Test
	public void when_add_null_element_must_garantee_that_exists() {
		HashSet<String> set = new HashSet<>();
		set.add(null); 
		
		HashMap<String,Boolean> map = getMap(set);
		assertTrue(map.containsKey(null));
		assertEquals(1, map.size());
	}
	
	@Test
	public void when_add_null_elements_with_duplicate_entries_must_add_only_one() {
		HashSet<String> set = new HashSet<>();
		set.add(null); set.add(null); set.add(null); 
		
		HashMap<String,Boolean> map = getMap(set);
		assertTrue(map.containsKey(null));
		assertEquals(1, map.size());
	}
	
	@Test
	public void when_add_elements_in_the_same_bucket_must_add_and_contains_the_element() {
		HashSet<Integer> set = new HashSet<>();
		set.add(0); set.add(16);
		
		HashMap<Integer,Boolean> map = getMap(set);
		assertTrue(map.containsKey(16));
		assertTrue(map.get(16));
	}
	
	@Test
	public void when_remove_valid_element_must_remove_it() {
		HashSet<Integer> set = new HashSet<>();
		set.add(16);
		set.remove(16);
		
		HashMap<Integer,Boolean> map = getMap(set);
		assertFalse(map.containsKey(16));
		assertNull(map.get(16));
	}
	
	@Test
	public void when_remove_invalid_element_must_not_change_anything() {
		HashSet<Integer> set = new HashSet<>();
		set.remove(16);
		
		HashMap<Integer,Boolean> map = getMap(set);
		assertFalse(map.containsKey(16));
		assertNull(map.get(16));
	}
	
	@Test
	public void when_clear_hash_set_with_elements_must_remove_all_elements() {
		HashSet<Integer> set = new HashSet<>();
		set.add(16);
		set.clear();
		
		HashMap<Integer,Boolean> map = getMap(set);
		assertTrue(map.isEmpty());
	}
	
	@Test
	public void when_clear_hash_set_with_default_properties_must_restart_with_default_properties() {
		HashSet<Integer> set = new HashSet<>();
		set.clear();
		
		HashMap<Integer,Boolean> map = getMap(set);
		assertTrue(map.isEmpty());
		assertEquals(16, getBucketsFrom(map));
		assertEquals(.75f, getLoadFactorFrom(map), 0);
	}
	
	@Test
	public void when_clear_hash_set_with_custom_properties_must_restart_with_custom_properties() {
		HashSet<Integer> set = new HashSet<>(10, .55f);
		set.add(16);
		set.clear();
		
		HashMap<Integer,Boolean> map = getMap(set);
		assertTrue(map.isEmpty());
		assertEquals(10, getBucketsFrom(map));
		assertEquals(.55f, getLoadFactorFrom(map), 0);
	}
	
	@Test
	public void when_clear_resized_hash_set_must_restart_with_initial_properties() {
		HashSet<Integer> set = new HashSet<>(3, .75f);
		set.add(16); set.add(15); set.add(14); set.add(13);
		set.clear();
		
		HashMap<Integer,Boolean> map = getMap(set);
		assertTrue(map.isEmpty());
		assertEquals(3, getBucketsFrom(map));
		assertEquals(.75f, getLoadFactorFrom(map), 0);
	}
	
	@Test
	public void when_size_with_empty_set_must_return_0() {
		HashSet<Integer> set = new HashSet<>();
		
		assertEquals(0, set.size());
	}
	
	@Test
	public void when_size_with_populated_hash_set_must_return_the_number_of_elements() {
		HashSet<Integer> set = new HashSet<>();
		set.add(16); set.add(15); set.add(14); set.add(13);
		
		assertEquals(4, set.size());
	}
	
	@Test
	public void when_size_with_duplicated_elements_in_hash_set_must_exclude_the_duplicated() {
		HashSet<Integer> set = new HashSet<>();
		set.add(null); set.add(15); set.add(null); set.add(15);
		
		assertEquals(2, set.size());
	}
	
	@Test
	public void when_empty_with_empty_set_must_return_true() {
		HashSet<Integer> set = new HashSet<>();
		assertTrue(set.isEmpty());
	}
	
	@Test
	public void when_empty_with_non_empty_set_must_return_false() {
		HashSet<Integer> set = new HashSet<>();
		set.add(1);
		assertFalse(set.isEmpty());
	}
	
	@Test
	public void when_contains_with_non_existing_element_must_return_false() {
		HashSet<Integer> set = new HashSet<>();
		set.add(1); set.add(2); set.add(3);
		
		assertFalse(set.contains(4));
		assertFalse(set.contains(-1));
		assertFalse(set.contains(null));
	}

	@Test
	public void when_contains_with_existing_element_must_return_true() {
		HashSet<Integer> set = new HashSet<>();
		set.add(1); set.add(2); set.add(3);
		
		assertTrue(set.contains(1));
		assertTrue(set.contains(2));
		assertTrue(set.contains(3));
	}

	@Test
	public void when_contains_with_existing_null_element_must_return_true() {
		HashSet<Integer> set = new HashSet<>();
		set.add(null); set.add(2); set.add(3);
		
		assertTrue(set.contains(null));
	}

	@SuppressWarnings("unchecked")
	private <T> HashMap<T, Boolean> getMap(HashSet<T> set) {
		return (HashMap<T, Boolean>) ReflectionTestUtils.getField(set, "map");
	}
	
	private <T> int getBucketsFrom(HashMap<T, Boolean> map) {
		return (int) ReflectionTestUtils.getField(map, "buckets");
	}

	private <T> float getLoadFactorFrom(HashMap<T, Boolean> map) {
		return (float) ReflectionTestUtils.getField(map, "loadFactor");
	}
	
}
