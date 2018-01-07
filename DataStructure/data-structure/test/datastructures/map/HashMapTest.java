package datastructures.map;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.Map;
import datastructures.base.map.HashNode;
import datastructures.maps.HashMap;

@RunWith(JUnit4.class)
public class HashMapTest {
	
	@Test
	public void construct_hash_map_with_default_values() {
		Map<String, String> hashMap = new HashMap<String, String>();
		
		int buckets = getBuckets(hashMap);
		float loadFactor = getLoadFactor(hashMap);
		
		assertEquals(Integer.valueOf(0), getSize(hashMap));
		assertEquals(16, buckets);
		assertEquals(.75f, loadFactor, 0);
		assertFalse( hashTableHasAnyElement(hashMap) );
	}

	@Test
	public void construct_hash_map_with_capacity_and_load_factor() {
		Map<String, String> hashMap = new HashMap<String, String>(32, 0.6f);
		
		int buckets = getBuckets(hashMap);
		float loadFactor = getLoadFactor(hashMap);
		
		assertEquals(Integer.valueOf(0), getSize(hashMap));
		assertEquals(32, buckets);
		assertEquals(.6f, loadFactor, 0);
		assertFalse( hashTableHasAnyElement(hashMap) );
	}
	
	@Test
	public void put_element_in_buckets() {
		Map<Integer, String> hashMap = new HashMap<Integer, String>();
		hashMap.put(4, "d"); hashMap.put(3, "8"); hashMap.put(32, "p");
		
		assertEquals(Integer.valueOf(3), getSize(hashMap));
		
		HashNode<Integer,String>[] hashTable = getHashTable(hashMap);
		assertEquals(new Integer(4), hashTable[4].getKey());
		assertEquals("d", hashTable[4].getValue());
		assertNull(hashTable[4].getNext());
		
		assertEquals(new Integer(3), hashTable[3].getKey());
		assertEquals("8", hashTable[3].getValue());
		assertNull(hashTable[3].getNext());
		
		assertEquals(new Integer(32), hashTable[0].getKey());
		assertEquals("p", hashTable[0].getValue());
		assertNull(hashTable[0].getNext());
	}

	@Test
	public void put_element_with_different_size_should_put_in_different_buckets() {
		Map<Integer, String> hashMap = new HashMap<Integer, String>();
		hashMap.put(32, "p");
		assertEquals(Integer.valueOf(1), getSize(hashMap));
		
		HashNode<Integer,String>[] hashTable = getHashTable(hashMap);
		assertEquals(new Integer(32), hashTable[0].getKey());
		assertEquals("p", hashTable[0].getValue());
		assertNull(hashTable[0].getNext());
		
		hashMap = new HashMap<Integer, String>(18, 0.75f);
		hashMap.put(32, "p");
		
		assertEquals(Integer.valueOf(1), getSize(hashMap));
		
		hashTable = getHashTable(hashMap);
		assertEquals(new Integer(32), hashTable[14].getKey());
		assertEquals("p", hashTable[14].getValue());
		assertNull(hashTable[14].getNext());
	}
	
	@Test
	public void resize_hash_map_if_hits_load_factor_capicity() {
		Map<Integer, String> hashMap = new HashMap<Integer, String>(5, .75f);
		hashMap.put(0, "A"); hashMap.put(12, "B"); hashMap.put(10, "C"); hashMap.put(6, "D");
		hashMap.put(13, "E");
		
		assertEquals(Integer.valueOf(5), getSize(hashMap));
		HashNode<Integer,String>[] hashTable = getHashTable(hashMap);
		assertEquals(10, hashTable.length);
		
		assertEquals(Integer.valueOf(0), hashTable[0].getKey());
		assertEquals(Integer.valueOf(10), hashTable[0].getNext().getKey());
		assertEquals(Integer.valueOf(12), hashTable[2].getKey());
		assertEquals(Integer.valueOf(13), hashTable[3].getKey());
		assertEquals(Integer.valueOf(6), hashTable[6].getKey());
		assertNull(hashTable[1]);
		assertNull(hashTable[4]);
		assertNull(hashTable[5]);
		assertNull(hashTable[7]);
		assertNull(hashTable[8]);
		assertNull(hashTable[9]);
	}
	
	@Test
	public void element_in_same_bucket_should_link() {
		Map<Integer, String> hashMap = new HashMap<Integer, String>();
		hashMap.put(0, "d"); hashMap.put(16, "8"); hashMap.put(32, "p");
		
		assertEquals(Integer.valueOf(3), getSize(hashMap));
		
		HashNode<Integer,String>[] hashTable = getHashTable(hashMap);
		assertEquals(new Integer(0), hashTable[0].getKey());
		assertEquals("d", hashTable[0].getValue());
		
		assertEquals(new Integer(16), hashTable[0].getNext().getKey());
		assertEquals("8", hashTable[0].getNext().getValue());
		
		assertEquals(new Integer(32), hashTable[0].getNext().getNext().getKey());
		assertEquals("p", hashTable[0].getNext().getNext().getValue());
	}
	
	@Test
	public void get_element_by_key() {
		Map<Integer, String> hashMap = example();
		
		assertEquals("p", hashMap.get(13));
		assertEquals("KJAS", hashMap.get(34));
		assertEquals("d", hashMap.get(0));
	}
	
	@Test
	public void get_element_by_invalid_key() {
		Map<Integer, String> hashMap = example();
		
		assertNull(hashMap.get(44));
		assertNull(hashMap.get(48));
	}
	
	@Test
	public void get_element_by_key_on_linked_node() {
		Map<Integer, String> hashMap = example();
		
		assertEquals("8", hashMap.get(16));
		assertEquals("K", hashMap.get(32));
	}
	
	@Test
	public void get_element_or_default_by_key_when_it_exists() {
		Map<Integer, String> hashMap = example();
		
		assertEquals("p", hashMap.getOrDefault(13, "RICHARD"));
		assertEquals("KJAS", hashMap.getOrDefault(34, "RICHARD"));
		assertEquals("d", hashMap.getOrDefault(0, "RICHARD"));
	}
	
	@Test
	public void get_element_or_default_by_invalid_key() {
		Map<Integer, String> hashMap = example();
		
		assertEquals("RICHARD", hashMap.getOrDefault(44, "RICHARD"));
		assertEquals("RICHARD", hashMap.getOrDefault(48, "RICHARD"));
	}
	
	@Test
	public void get_element_or_default_by_key_on_linked_node() {
		Map<Integer, String> hashMap = example();
		
		assertEquals("8", hashMap.getOrDefault(16, "RICHARD"));
		assertEquals("K", hashMap.getOrDefault(32, "RICHARD"));
	}
	
	@Test
	public void size_of_hash_map() {
		Map<Integer, String> hashMap = new HashMap<Integer, String>();
		assertEquals(0, hashMap.size());
		
		hashMap.put(0, "d"); hashMap.put(16, "8"); hashMap.put(13, "p"); hashMap.put(32, "K"); hashMap.put(34, "KJAS");
		assertEquals(5, hashMap.size());
	}
	
	@Test
	public void is_empty_map() {
		Map<Integer, String> hashMap = new HashMap<Integer, String>();
		assertTrue(hashMap.isEmpty());
		
		hashMap.put(0, "d");
		assertFalse(hashMap.isEmpty());
	}
	
	@Test
	public void contains_key() {
		Map<Integer, String> hashMap = example();
		
		assertTrue(hashMap.containsKey(0)); // direct
		assertTrue(hashMap.containsKey(16)); // inside bucket
		assertTrue(hashMap.containsKey(13)); // another direct
	}
	
	@Test
	public void contains_invalid_key() {
		Map<Integer, String> hashMap = example();
		
		assertFalse(hashMap.containsKey(11)); // direct
		assertFalse(hashMap.containsKey(48)); // inside bucket
	}
	
	@Test
	public void contains_value() {
		Map<Integer, String> hashMap = example();

		assertTrue(hashMap.containsValue("8"));
		assertTrue(hashMap.containsValue("K"));
		assertTrue(hashMap.containsValue("KJAS"));
	}
	
	@Test
	public void contains_invalid_value() {
		Map<Integer, String> hashMap = example();
		
		assertFalse(hashMap.containsValue("KK"));
		assertFalse(hashMap.containsValue("AA"));
	}

	private Map<Integer, String> example() {
		Map<Integer, String> hashMap = new HashMap<Integer, String>();
		hashMap.put(0, "d"); hashMap.put(16, "8"); hashMap.put(13, "p"); hashMap.put(32, "K"); hashMap.put(34, "KJAS");
		return hashMap;
	}
	
	@Test
	public void remove_key_alone_in_bucket() {
		Map<Integer, String> hashMap = example();
		boolean result = hashMap.remove(34);
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(hashMap));
		
		HashNode<Integer, String>[] hashTable = getHashTable(hashMap);
		
		assertEquals(new Integer(0), hashTable[0].getKey());
		assertEquals("d", hashTable[0].getValue());
		
		assertEquals(new Integer(16), hashTable[0].getNext().getKey());
		assertEquals("8", hashTable[0].getNext().getValue());
		
		assertEquals(new Integer(32), hashTable[0].getNext().getNext().getKey());
		assertEquals("K", hashTable[0].getNext().getNext().getValue());
		assertNull(hashTable[0].getNext().getNext().getNext());
		
		assertEquals(new Integer(13), hashTable[13].getKey());
		assertEquals("p", hashTable[13].getValue());
		assertNull(hashTable[13].getNext());
	}

	@Test
	public void remove_key_in_the_middle_of_the_bucket() {
		Map<Integer, String> hashMap = example();
		boolean result = hashMap.remove(16);
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(hashMap));
		
		HashNode<Integer, String>[] hashTable = getHashTable(hashMap);
		
		assertEquals(new Integer(0), hashTable[0].getKey());
		assertEquals("d", hashTable[0].getValue());
		
		assertEquals(new Integer(32), hashTable[0].getNext().getKey());
		assertEquals("K", hashTable[0].getNext().getValue());
		assertNull(hashTable[0].getNext().getNext());
		
		assertEquals(new Integer(13), hashTable[13].getKey());
		assertEquals("p", hashTable[13].getValue());
		assertNull(hashTable[13].getNext());
		
		assertEquals(new Integer(34), hashTable[2].getKey());
		assertEquals("KJAS", hashTable[2].getValue());
		assertNull(hashTable[2].getNext());	
	}

	@Test
	public void remove_key_in_the_end_of_the_bucket() {
		Map<Integer, String> hashMap = example();
		boolean result = hashMap.remove(32);
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(hashMap));
		
		HashNode<Integer, String>[] hashTable = getHashTable(hashMap);
		
		assertEquals(new Integer(0), hashTable[0].getKey());
		assertEquals("d", hashTable[0].getValue());
		
		assertEquals(new Integer(16), hashTable[0].getNext().getKey());
		assertEquals("8", hashTable[0].getNext().getValue());
		assertNull(hashTable[0].getNext().getNext());
		
		assertEquals(new Integer(13), hashTable[13].getKey());
		assertEquals("p", hashTable[13].getValue());
		assertNull(hashTable[13].getNext());
		
		assertEquals(new Integer(34), hashTable[2].getKey());
		assertEquals("KJAS", hashTable[2].getValue());
		assertNull(hashTable[2].getNext());
	}

	@Test
	public void remove_early_key_in_the_bucket() {
		Map<Integer, String> hashMap = example();
		boolean result = hashMap.remove(0);
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(hashMap));
		
		HashNode<Integer, String>[] hashTable = getHashTable(hashMap);
		
		assertEquals(new Integer(16), hashTable[0].getKey());
		assertEquals("8", hashTable[0].getValue());
		
		assertEquals(new Integer(32), hashTable[0].getNext().getKey());
		assertEquals("K", hashTable[0].getNext().getValue());
		assertNull(hashTable[0].getNext().getNext());
		
		assertEquals(new Integer(13), hashTable[13].getKey());
		assertEquals("p", hashTable[13].getValue());
		assertNull(hashTable[13].getNext());
		
		assertEquals(new Integer(34), hashTable[2].getKey());
		assertEquals("KJAS", hashTable[2].getValue());
		assertNull(hashTable[2].getNext());
	}
	
	@Test
	public void remove_invalid_key() {
		Map<Integer, String> hashMap = example();
		boolean result = hashMap.remove(33);
		assertFalse(result);
		assertEquals(Integer.valueOf(5), getSize(hashMap));
		
		HashNode<Integer, String>[] hashTable = getHashTable(hashMap);
		
		assertEquals(new Integer(0), hashTable[0].getKey());
		assertEquals("d", hashTable[0].getValue());
		
		assertEquals(new Integer(16), hashTable[0].getNext().getKey());
		assertEquals("8", hashTable[0].getNext().getValue());
		
		assertEquals(new Integer(32), hashTable[0].getNext().getNext().getKey());
		assertEquals("K", hashTable[0].getNext().getNext().getValue());
		assertNull(hashTable[0].getNext().getNext().getNext());
		
		assertEquals(new Integer(13), hashTable[13].getKey());
		assertEquals("p", hashTable[13].getValue());
		assertNull(hashTable[13].getNext());
		
		assertEquals(new Integer(34), hashTable[2].getKey());
		assertEquals("KJAS", hashTable[2].getValue());
		assertNull(hashTable[2].getNext());
	}
	
	
	
	@Test
	public void remove_by_key_value_alone_in_bucket() {
		Map<Integer, String> hashMap = example();
		boolean result = hashMap.removeIfKeyEqualValue(34, "KJAS");
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(hashMap));
		
		HashNode<Integer, String>[] hashTable = getHashTable(hashMap);
		
		assertEquals(new Integer(0), hashTable[0].getKey());
		assertEquals("d", hashTable[0].getValue());
		
		assertEquals(new Integer(16), hashTable[0].getNext().getKey());
		assertEquals("8", hashTable[0].getNext().getValue());
		
		assertEquals(new Integer(32), hashTable[0].getNext().getNext().getKey());
		assertEquals("K", hashTable[0].getNext().getNext().getValue());
		assertNull(hashTable[0].getNext().getNext().getNext());
		
		assertEquals(new Integer(13), hashTable[13].getKey());
		assertEquals("p", hashTable[13].getValue());
		assertNull(hashTable[13].getNext());
	}

	@Test
	public void remove_by_key_value_in_the_middle_of_the_bucket() {
		Map<Integer, String> hashMap = example();
		boolean result = hashMap.removeIfKeyEqualValue(16, "8");
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(hashMap));
		
		HashNode<Integer, String>[] hashTable = getHashTable(hashMap);
		
		assertEquals(new Integer(0), hashTable[0].getKey());
		assertEquals("d", hashTable[0].getValue());
		
		assertEquals(new Integer(32), hashTable[0].getNext().getKey());
		assertEquals("K", hashTable[0].getNext().getValue());
		assertNull(hashTable[0].getNext().getNext());
		
		assertEquals(new Integer(13), hashTable[13].getKey());
		assertEquals("p", hashTable[13].getValue());
		assertNull(hashTable[13].getNext());
		
		assertEquals(new Integer(34), hashTable[2].getKey());
		assertEquals("KJAS", hashTable[2].getValue());
		assertNull(hashTable[2].getNext());	
	}

	@Test
	public void remove_by_key_value_in_the_end_of_the_bucket() {
		Map<Integer, String> hashMap = example();
		boolean result = hashMap.removeIfKeyEqualValue(32, "K");
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(hashMap));
		
		HashNode<Integer, String>[] hashTable = getHashTable(hashMap);
		
		assertEquals(new Integer(0), hashTable[0].getKey());
		assertEquals("d", hashTable[0].getValue());
		
		assertEquals(new Integer(16), hashTable[0].getNext().getKey());
		assertEquals("8", hashTable[0].getNext().getValue());
		assertNull(hashTable[0].getNext().getNext());
		
		assertEquals(new Integer(13), hashTable[13].getKey());
		assertEquals("p", hashTable[13].getValue());
		assertNull(hashTable[13].getNext());
		
		assertEquals(new Integer(34), hashTable[2].getKey());
		assertEquals("KJAS", hashTable[2].getValue());
		assertNull(hashTable[2].getNext());
	}

	@Test
	public void remove_early_by_key_value_in_the_bucket() {
		Map<Integer, String> hashMap = example();
		boolean result = hashMap.removeIfKeyEqualValue(0, "d");
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(hashMap));
		
		HashNode<Integer, String>[] hashTable = getHashTable(hashMap);
		
		assertEquals(new Integer(16), hashTable[0].getKey());
		assertEquals("8", hashTable[0].getValue());
		
		assertEquals(new Integer(32), hashTable[0].getNext().getKey());
		assertEquals("K", hashTable[0].getNext().getValue());
		assertNull(hashTable[0].getNext().getNext());
		
		assertEquals(new Integer(13), hashTable[13].getKey());
		assertEquals("p", hashTable[13].getValue());
		assertNull(hashTable[13].getNext());
		
		assertEquals(new Integer(34), hashTable[2].getKey());
		assertEquals("KJAS", hashTable[2].getValue());
		assertNull(hashTable[2].getNext());
	}
	
	@Test
	public void remove_invalid_key_value_with_existent_value() {
		Map<Integer, String> hashMap = example();
		boolean result = hashMap.removeIfKeyEqualValue(33, "KJAS");
		assertFalse(result);
		assertEquals(Integer.valueOf(5), getSize(hashMap));
		
		HashNode<Integer, String>[] hashTable = getHashTable(hashMap);
		
		assertEquals(new Integer(0), hashTable[0].getKey());
		assertEquals("d", hashTable[0].getValue());
		
		assertEquals(new Integer(16), hashTable[0].getNext().getKey());
		assertEquals("8", hashTable[0].getNext().getValue());
		
		assertEquals(new Integer(32), hashTable[0].getNext().getNext().getKey());
		assertEquals("K", hashTable[0].getNext().getNext().getValue());
		assertNull(hashTable[0].getNext().getNext().getNext());
		
		assertEquals(new Integer(13), hashTable[13].getKey());
		assertEquals("p", hashTable[13].getValue());
		assertNull(hashTable[13].getNext());
		
		assertEquals(new Integer(34), hashTable[2].getKey());
		assertEquals("KJAS", hashTable[2].getValue());
		assertNull(hashTable[2].getNext());
	}
	
	@Test
	public void remove_invalid_key_value_with_existent_key() {
		Map<Integer, String> hashMap = example();
		boolean result = hashMap.removeIfKeyEqualValue(34, "ASKSJA");
		assertFalse(result);
		assertEquals(Integer.valueOf(5), getSize(hashMap));
		
		HashNode<Integer, String>[] hashTable = getHashTable(hashMap);
		
		assertEquals(new Integer(0), hashTable[0].getKey());
		assertEquals("d", hashTable[0].getValue());
		
		assertEquals(new Integer(16), hashTable[0].getNext().getKey());
		assertEquals("8", hashTable[0].getNext().getValue());
		
		assertEquals(new Integer(32), hashTable[0].getNext().getNext().getKey());
		assertEquals("K", hashTable[0].getNext().getNext().getValue());
		assertNull(hashTable[0].getNext().getNext().getNext());
		
		assertEquals(new Integer(13), hashTable[13].getKey());
		assertEquals("p", hashTable[13].getValue());
		assertNull(hashTable[13].getNext());
		
		assertEquals(new Integer(34), hashTable[2].getKey());
		assertEquals("KJAS", hashTable[2].getValue());
		assertNull(hashTable[2].getNext());
	}
	
	@Test
	public void list_all_keys() {
		Map<Integer, String> hashMap = example();
		Object[] keys = hashMap.keys();
		assertEquals(Integer.valueOf(0), (Integer) keys[0]);
		assertEquals(Integer.valueOf(16), (Integer) keys[1]);
		assertEquals(Integer.valueOf(32), (Integer) keys[2]);
		assertEquals(Integer.valueOf(34), (Integer) keys[3]);
		assertEquals(Integer.valueOf(13), (Integer) keys[4]);
	}
	
	@Test
	public void list_all_values() {
		Map<Integer, String> hashMap = example();
		Object[] values = hashMap.values();
		assertEquals("d", (String) values[0]);
		assertEquals("8", (String) values[1]);
		assertEquals("K", (String) values[2]);
		assertEquals("KJAS", (String) values[3]);
		assertEquals("p", (String) values[4]);
	}
	
	private boolean hashTableHasAnyElement(Map<String, String> hashMap) {
		HashNode<String, String>[] hashTable = getHashTable(hashMap);
		List<HashNode<String, String>> asList = Arrays.asList(hashTable);
		return asList.stream().anyMatch((o) -> o != null);
	}
	
	@SuppressWarnings("unchecked")
	private <K, V> HashNode<K, V>[] getHashTable(Map<K, V> hashMap) {
		return (HashNode<K, V>[]) ReflectionTestUtils.getField(hashMap, "table");
	}

	private float getLoadFactor(Map<String, String> hashMap) {
		return (float) ReflectionTestUtils.getField(hashMap, "loadFactor");
	}

	private int getBuckets(Map<String, String> hashMap) {
		return (int) ReflectionTestUtils.getField(hashMap, "buckets");
	}
	
	private <K, V> Integer getSize(Map<K, V> hashMap) {
		return (Integer) ReflectionTestUtils.getField(hashMap, "size");
	}

}
