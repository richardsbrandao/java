package datastructures.map;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.Map;
import datastructures.base.map.LinkedHashMapNode;
import datastructures.maps.LinkedHashMap;

@RunWith(JUnit4.class)
public class LinkedHashMapTest {
	
	@Test
	public void construct_linked_hash_map_with_default_values() {
		Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();
		
		int buckets = getBuckets(linkedHashMap);
		float loadFactor = getLoadFactor(linkedHashMap);
		
		assertEquals(Integer.valueOf(0), getSize(linkedHashMap));
		assertEquals(16, buckets);
		assertEquals(.75f, loadFactor, 0);
		assertFalse( hashTableHasAnyElement(linkedHashMap) );
		assertNull( getHead(linkedHashMap) );
		assertNull( getTail(linkedHashMap) );
	}

	@Test
	public void construct_hash_map_with_capacity_and_load_factor() {
		Map<String, String> linkedHashMap = new LinkedHashMap<String, String>(32, 0.6f);
		
		int buckets = getBuckets(linkedHashMap);
		float loadFactor = getLoadFactor(linkedHashMap);
		
		assertEquals(Integer.valueOf(0), getSize(linkedHashMap));
		assertEquals(32, buckets);
		assertEquals(.6f, loadFactor, 0);
		assertFalse( hashTableHasAnyElement(linkedHashMap) );
		assertNull( getHead(linkedHashMap) );
		assertNull( getTail(linkedHashMap) );
	}
	
	@Test
	public void put_one_element_in_bucket_check_if_head_and_tail_are_the_same() {
		Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
		linkedHashMap.put(4, "d");
		
		assertEquals(Integer.valueOf(1), getSize(linkedHashMap));
		assertEquals("d", getHashTable(linkedHashMap)[4].getValue());
		assertEquals("d", getHead(linkedHashMap).getValue());
		assertEquals("d", getTail(linkedHashMap).getValue());
	}
	
	@Test
	public void put_element_in_buckets() {
		Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
		linkedHashMap.put(4, "d"); linkedHashMap.put(3, "8"); linkedHashMap.put(32, "p");
		
		assertEquals(Integer.valueOf(3), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer,String>[] hashTable = getHashTable(linkedHashMap);
		assertEquals(new Integer(4), hashTable[4].getKey());
		assertEquals("d", hashTable[4].getValue());
		assertNull(hashTable[4].getNext());
		
		assertEquals(new Integer(3), hashTable[3].getKey());
		assertEquals("8", hashTable[3].getValue());
		assertNull(hashTable[3].getNext());
		
		assertEquals(new Integer(32), hashTable[0].getKey());
		assertEquals("p", hashTable[0].getValue());
		assertNull(hashTable[0].getNext());
		
		LinkedHashMapNode<Integer,String> head = getHead(linkedHashMap);
		assertEquals("d", head.getValue());
		assertEquals("8", head.getAfter().getValue());
		assertEquals("p", head.getAfter().getAfter().getValue());
		assertNull(head.getBefore());
		
		LinkedHashMapNode<Integer,String> tail = getTail(linkedHashMap);
		assertEquals("p", tail.getValue());
		assertEquals("8", tail.getBefore().getValue());
		assertEquals("d", tail.getBefore().getBefore().getValue());
		assertNull(tail.getAfter());
	}
	
	@Test
	public void put_one_element_should_head_and_tail_be_the_same() {
		Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
		linkedHashMap.put(12, "4");
		assertEquals(Integer.valueOf(1), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer,String> head = getHead(linkedHashMap);
		assertEquals("4", head.getValue());
		assertNull(head.getAfter());
		assertNull(head.getBefore());
		assertNull(head.getNext());
		
		LinkedHashMapNode<Integer,String> tail = getTail(linkedHashMap);
		assertEquals("4", tail.getValue());
		assertNull(tail.getAfter());
		assertNull(tail.getBefore());
		assertNull(tail.getNext());
	}

	@Test
	public void put_element_with_different_size_should_put_in_different_buckets() {
		Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
		linkedHashMap.put(32, "p");
		assertEquals(Integer.valueOf(1), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer,String>[] hashTable = getHashTable(linkedHashMap);
		assertEquals(new Integer(32), hashTable[0].getKey());
		assertEquals("p", hashTable[0].getValue());
		assertNull(hashTable[0].getNext());
		
		linkedHashMap = new LinkedHashMap<Integer, String>(18, 0.75f);
		linkedHashMap.put(32, "p");
		
		assertEquals(Integer.valueOf(1), getSize(linkedHashMap));
		
		hashTable = getHashTable(linkedHashMap);
		assertEquals(new Integer(32), hashTable[14].getKey());
		assertEquals("p", hashTable[14].getValue());
		assertNull(hashTable[14].getNext());
	}
	
	@Test
	public void resize_hash_map_if_hits_load_factor_capicity() {
		Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>(5, .75f);
		linkedHashMap.put(0, "A"); linkedHashMap.put(12, "B"); linkedHashMap.put(10, "C"); linkedHashMap.put(6, "D");
		linkedHashMap.put(13, "E");
		
		assertEquals(Integer.valueOf(5), getSize(linkedHashMap));
		LinkedHashMapNode<Integer,String>[] hashTable = getHashTable(linkedHashMap);
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
		Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
		linkedHashMap.put(0, "d"); linkedHashMap.put(16, "8"); linkedHashMap.put(32, "p");
		
		assertEquals(Integer.valueOf(3), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer,String>[] hashTable = getHashTable(linkedHashMap);
		assertEquals(new Integer(0), hashTable[0].getKey());
		assertEquals("d", hashTable[0].getValue());
		
		assertEquals(new Integer(16), hashTable[0].getNext().getKey());
		assertEquals("8", hashTable[0].getNext().getValue());
		
		assertEquals(new Integer(32), hashTable[0].getNext().getNext().getKey());
		assertEquals("p", hashTable[0].getNext().getNext().getValue());
	}
	
	@Test
	public void get_element_by_key() {
		Map<Integer, String> linkedHashMap = example();
		
		assertEquals("p", linkedHashMap.get(13));
		assertEquals("KJAS", linkedHashMap.get(34));
		assertEquals("d", linkedHashMap.get(0));
	}
	
	@Test
	public void get_element_by_invalid_key() {
		Map<Integer, String> linkedHashMap = example();
		
		assertNull(linkedHashMap.get(44));
		assertNull(linkedHashMap.get(48));
	}
	
	@Test
	public void get_element_by_key_on_linked_node() {
		Map<Integer, String> linkedHashMap = example();
		
		assertEquals("8", linkedHashMap.get(16));
		assertEquals("K", linkedHashMap.get(32));
	}
	
	@Test
	public void get_element_or_default_by_key_when_it_exists() {
		Map<Integer, String> linkedHashMap = example();
		
		assertEquals("p", linkedHashMap.getOrDefault(13, "RICHARD"));
		assertEquals("KJAS", linkedHashMap.getOrDefault(34, "RICHARD"));
		assertEquals("d", linkedHashMap.getOrDefault(0, "RICHARD"));
	}
	
	@Test
	public void get_element_or_default_by_invalid_key() {
		Map<Integer, String> linkedHashMap = example();
		
		assertEquals("RICHARD", linkedHashMap.getOrDefault(44, "RICHARD"));
		assertEquals("RICHARD", linkedHashMap.getOrDefault(48, "RICHARD"));
	}
	
	@Test
	public void get_element_or_default_by_key_on_linked_node() {
		Map<Integer, String> linkedHashMap = example();
		
		assertEquals("8", linkedHashMap.getOrDefault(16, "RICHARD"));
		assertEquals("K", linkedHashMap.getOrDefault(32, "RICHARD"));
	}
	
	@Test
	public void size_of_hash_map() {
		Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
		assertEquals(0, linkedHashMap.size());
		
		linkedHashMap.put(0, "d"); linkedHashMap.put(16, "8"); linkedHashMap.put(13, "p"); linkedHashMap.put(32, "K"); linkedHashMap.put(34, "KJAS");
		assertEquals(5, linkedHashMap.size());
	}
	
	@Test
	public void is_empty_map() {
		Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
		assertTrue(linkedHashMap.isEmpty());
		
		linkedHashMap.put(0, "d");
		assertFalse(linkedHashMap.isEmpty());
	}
	
	@Test
	public void contains_key() {
		Map<Integer, String> linkedHashMap = example();
		
		assertTrue(linkedHashMap.containsKey(0)); // direct
		assertTrue(linkedHashMap.containsKey(16)); // inside bucket
		assertTrue(linkedHashMap.containsKey(13)); // another direct
	}
	
	@Test
	public void contains_invalid_key() {
		Map<Integer, String> linkedHashMap = example();
		
		assertFalse(linkedHashMap.containsKey(11)); // direct
		assertFalse(linkedHashMap.containsKey(48)); // inside bucket
	}
	
	@Test
	public void contains_value() {
		Map<Integer, String> linkedHashMap = example();

		assertTrue(linkedHashMap.containsValue("8"));
		assertTrue(linkedHashMap.containsValue("K"));
		assertTrue(linkedHashMap.containsValue("KJAS"));
	}
	
	@Test
	public void contains_invalid_value() {
		Map<Integer, String> linkedHashMap = example();
		
		assertFalse(linkedHashMap.containsValue("KK"));
		assertFalse(linkedHashMap.containsValue("AA"));
	}

	private Map<Integer, String> example() {
		Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
		linkedHashMap.put(0, "d"); linkedHashMap.put(16, "8"); linkedHashMap.put(13, "p"); linkedHashMap.put(32, "K"); linkedHashMap.put(34, "KJAS");
		return linkedHashMap;
		
	}
	@Test
	public void remove_key_alone_in_bucket() {
		Map<Integer, String> linkedHashMap = example();
		boolean result = linkedHashMap.remove(34);
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer, String>[] hashTable = getHashTable(linkedHashMap);
		
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
		
		LinkedHashMapNode<Integer, String> head = getHead(linkedHashMap);
		assertEquals("d", head.getValue());
		assertEquals("8", head.getAfter().getValue());
		assertEquals("p", head.getAfter().getAfter().getValue());
		assertEquals("K", head.getAfter().getAfter().getAfter().getValue());
		assertNull(head.getAfter().getAfter().getAfter().getAfter());
		
		LinkedHashMapNode<Integer, String> tail = getTail(linkedHashMap);
		assertEquals("K", tail.getValue());
		assertEquals("p", tail.getBefore().getValue());
		assertEquals("8", tail.getBefore().getBefore().getValue());
		assertEquals("d", tail.getBefore().getBefore().getBefore().getValue());
		assertNull(tail.getBefore().getBefore().getBefore().getBefore());
	}

	@Test
	public void remove_key_in_the_middle_of_the_bucket() {
		Map<Integer, String> linkedHashMap = example();
		boolean result = linkedHashMap.remove(16);
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer, String>[] hashTable = getHashTable(linkedHashMap);
		
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
		
		LinkedHashMapNode<Integer, String> head = getHead(linkedHashMap);
		assertEquals("d", head.getValue());
		assertEquals("p", head.getAfter().getValue());
		assertEquals("K", head.getAfter().getAfter().getValue());
		assertEquals("KJAS", head.getAfter().getAfter().getAfter().getValue());
		assertNull(head.getAfter().getAfter().getAfter().getAfter());
		
		LinkedHashMapNode<Integer, String> tail = getTail(linkedHashMap);
		assertEquals("KJAS", tail.getValue());
		assertEquals("K", tail.getBefore().getValue());
		assertEquals("p", tail.getBefore().getBefore().getValue());
		assertEquals("d", tail.getBefore().getBefore().getBefore().getValue());
		assertNull(tail.getBefore().getBefore().getBefore().getBefore());
	}

	@Test
	public void remove_key_in_the_end_of_the_bucket() {
		Map<Integer, String> linkedHashMap = example();
		boolean result = linkedHashMap.remove(32);
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer, String>[] hashTable = getHashTable(linkedHashMap);
		
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
		
		LinkedHashMapNode<Integer, String> head = getHead(linkedHashMap);
		assertEquals("d", head.getValue());
		assertEquals("8", head.getAfter().getValue());
		assertEquals("p", head.getAfter().getAfter().getValue());
		assertEquals("KJAS", head.getAfter().getAfter().getAfter().getValue());
		assertNull(head.getAfter().getAfter().getAfter().getAfter());
		
		LinkedHashMapNode<Integer, String> tail = getTail(linkedHashMap);
		assertEquals("KJAS", tail.getValue());
		assertEquals("p", tail.getBefore().getValue());
		assertEquals("8", tail.getBefore().getBefore().getValue());
		assertEquals("d", tail.getBefore().getBefore().getBefore().getValue());
		assertNull(tail.getBefore().getBefore().getBefore().getBefore());
	}

	@Test
	public void remove_early_key_in_the_bucket() {
		Map<Integer, String> linkedHashMap = example();
		boolean result = linkedHashMap.remove(0);
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer, String>[] hashTable = getHashTable(linkedHashMap);
		
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
		
		LinkedHashMapNode<Integer, String> head = getHead(linkedHashMap);
		assertEquals("8", head.getValue());
		assertEquals("p", head.getAfter().getValue());
		assertEquals("K", head.getAfter().getAfter().getValue());
		assertEquals("KJAS", head.getAfter().getAfter().getAfter().getValue());
		assertNull(head.getAfter().getAfter().getAfter().getAfter());
		
		LinkedHashMapNode<Integer, String> tail = getTail(linkedHashMap);
		assertEquals("KJAS", tail.getValue());
		assertEquals("K", tail.getBefore().getValue());
		assertEquals("p", tail.getBefore().getBefore().getValue());
		assertEquals("8", tail.getBefore().getBefore().getBefore().getValue());
		assertNull(tail.getBefore().getBefore().getBefore().getBefore());
	}
	
	@Test
	public void remove_invalid_key() {
		Map<Integer, String> linkedHashMap = example();
		boolean result = linkedHashMap.remove(33);
		assertFalse(result);
		assertEquals(Integer.valueOf(5), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer, String>[] hashTable = getHashTable(linkedHashMap);
		
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
		Map<Integer, String> linkedHashMap = example();
		boolean result = linkedHashMap.removeIfKeyEqualValue(34, "KJAS");
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer, String>[] hashTable = getHashTable(linkedHashMap);
		
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
		
		LinkedHashMapNode<Integer, String> head = getHead(linkedHashMap);
		assertEquals("d", head.getValue());
		assertEquals("8", head.getAfter().getValue());
		assertEquals("p", head.getAfter().getAfter().getValue());
		assertEquals("K", head.getAfter().getAfter().getAfter().getValue());
		assertNull(head.getAfter().getAfter().getAfter().getAfter());
		
		LinkedHashMapNode<Integer, String> tail = getTail(linkedHashMap);
		assertEquals("K", tail.getValue());
		assertEquals("p", tail.getBefore().getValue());
		assertEquals("8", tail.getBefore().getBefore().getValue());
		assertEquals("d", tail.getBefore().getBefore().getBefore().getValue());
		assertNull(tail.getBefore().getBefore().getBefore().getBefore());
	}

	@Test
	public void remove_by_key_value_in_the_middle_of_the_bucket() {
		Map<Integer, String> linkedHashMap = example();
		boolean result = linkedHashMap.removeIfKeyEqualValue(16, "8");
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer, String>[] hashTable = getHashTable(linkedHashMap);
		
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
		
		LinkedHashMapNode<Integer, String> head = getHead(linkedHashMap);
		assertEquals("d", head.getValue());
		assertEquals("p", head.getAfter().getValue());
		assertEquals("K", head.getAfter().getAfter().getValue());
		assertEquals("KJAS", head.getAfter().getAfter().getAfter().getValue());
		assertNull(head.getAfter().getAfter().getAfter().getAfter());
		
		LinkedHashMapNode<Integer, String> tail = getTail(linkedHashMap);
		assertEquals("KJAS", tail.getValue());
		assertEquals("K", tail.getBefore().getValue());
		assertEquals("p", tail.getBefore().getBefore().getValue());
		assertEquals("d", tail.getBefore().getBefore().getBefore().getValue());
		assertNull(tail.getBefore().getBefore().getBefore().getBefore());
	}

	@Test
	public void remove_by_key_value_in_the_end_of_the_bucket() {
		Map<Integer, String> linkedHashMap = example();
		boolean result = linkedHashMap.removeIfKeyEqualValue(32, "K");
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer, String>[] hashTable = getHashTable(linkedHashMap);
		
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
		
		LinkedHashMapNode<Integer, String> head = getHead(linkedHashMap);
		assertEquals("d", head.getValue());
		assertEquals("8", head.getAfter().getValue());
		assertEquals("p", head.getAfter().getAfter().getValue());
		assertEquals("KJAS", head.getAfter().getAfter().getAfter().getValue());
		assertNull(head.getAfter().getAfter().getAfter().getAfter());
		
		LinkedHashMapNode<Integer, String> tail = getTail(linkedHashMap);
		assertEquals("KJAS", tail.getValue());
		assertEquals("p", tail.getBefore().getValue());
		assertEquals("8", tail.getBefore().getBefore().getValue());
		assertEquals("d", tail.getBefore().getBefore().getBefore().getValue());
		assertNull(tail.getBefore().getBefore().getBefore().getBefore());
	}

	@Test
	public void remove_early_by_key_value_in_the_bucket() {
		Map<Integer, String> linkedHashMap = example();
		boolean result = linkedHashMap.removeIfKeyEqualValue(0, "d");
		assertTrue(result);
		assertEquals(Integer.valueOf(4), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer, String>[] hashTable = getHashTable(linkedHashMap);
		
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
		
		LinkedHashMapNode<Integer, String> head = getHead(linkedHashMap);
		assertEquals("8", head.getValue());
		assertEquals("p", head.getAfter().getValue());
		assertEquals("K", head.getAfter().getAfter().getValue());
		assertEquals("KJAS", head.getAfter().getAfter().getAfter().getValue());
		assertNull(head.getAfter().getAfter().getAfter().getAfter());
		
		LinkedHashMapNode<Integer, String> tail = getTail(linkedHashMap);
		assertEquals("KJAS", tail.getValue());
		assertEquals("K", tail.getBefore().getValue());
		assertEquals("p", tail.getBefore().getBefore().getValue());
		assertEquals("8", tail.getBefore().getBefore().getBefore().getValue());
		assertNull(tail.getBefore().getBefore().getBefore().getBefore());
	}
	
	@Test
	public void remove_invalid_key_value_with_existent_value() {
		Map<Integer, String> linkedHashMap = example();
		boolean result = linkedHashMap.removeIfKeyEqualValue(33, "KJAS");
		assertFalse(result);
		assertEquals(Integer.valueOf(5), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer, String>[] hashTable = getHashTable(linkedHashMap);
		
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
		Map<Integer, String> linkedHashMap = example();
		boolean result = linkedHashMap.removeIfKeyEqualValue(34, "ASKSJA");
		assertFalse(result);
		assertEquals(Integer.valueOf(5), getSize(linkedHashMap));
		
		LinkedHashMapNode<Integer, String>[] hashTable = getHashTable(linkedHashMap);
		
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
		Map<Integer, String> linkedHashMap = example();
		Object[] keys = linkedHashMap.keys();
		assertEquals(Integer.valueOf(0), (Integer) keys[0]);
		assertEquals(Integer.valueOf(16), (Integer) keys[1]);
		assertEquals(Integer.valueOf(13), (Integer) keys[2]);
		assertEquals(Integer.valueOf(32), (Integer) keys[3]);
		assertEquals(Integer.valueOf(34), (Integer) keys[4]);
	}
	
	@Test
	public void list_all_values() {
		Map<Integer, String> linkedHashMap = example();
		Object[] values = linkedHashMap.values();
		assertEquals("d", (String) values[0]);
		assertEquals("8", (String) values[1]);
		assertEquals("p", (String) values[2]);
		assertEquals("K", (String) values[3]);
		assertEquals("KJAS", (String) values[4]);
	}
	
	private boolean hashTableHasAnyElement(Map<String, String> linkedHashMap) {
		LinkedHashMapNode<String, String>[] hashTable = getHashTable(linkedHashMap);
		List<LinkedHashMapNode<String, String>> asList = Arrays.asList(hashTable);
		return asList.stream().anyMatch((o) -> o != null);
	}
	
	@SuppressWarnings("unchecked")
	private <K, V> LinkedHashMapNode<K, V>[] getHashTable(Map<K, V> linkedHashMap) {
		return (LinkedHashMapNode<K, V>[]) ReflectionTestUtils.getField(linkedHashMap, "table");
	}

	private float getLoadFactor(Map<String, String> linkedHashMap) {
		return (float) ReflectionTestUtils.getField(linkedHashMap, "loadFactor");
	}

	private int getBuckets(Map<String, String> linkedHashMap) {
		return (int) ReflectionTestUtils.getField(linkedHashMap, "buckets");
	}
	
	private <K, V> Integer getSize(Map<K, V> linkedHashMap) {
		return (Integer) ReflectionTestUtils.getField(linkedHashMap, "size");
	}

	@SuppressWarnings("unchecked")
	private <K, V> LinkedHashMapNode<K, V> getTail(Map<K, V> linkedHashMap) {
		return (LinkedHashMapNode<K, V>) ReflectionTestUtils.getField(linkedHashMap, "tail");
	}

	@SuppressWarnings("unchecked")
	private <K, V> LinkedHashMapNode<K, V> getHead(Map<K, V> linkedHashMap) {
		return (LinkedHashMapNode<K, V>) ReflectionTestUtils.getField(linkedHashMap, "head");
	}
	
}
