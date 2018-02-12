package datastructures.map;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.NavigableMap;
import datastructures.base.map.Entry;
import datastructures.base.map.RedBlackKeyValueNode;
import datastructures.base.tree.ColorRedBlackNode;
import datastructures.maps.TreeMap;;

@RunWith(JUnit4.class)
public class TreeMapTest {

	@Test
	public void when_construct_must_create_correctly() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		
		RedBlackKeyValueNode<Integer, String> root = getRootFromMap(tree);
		
		assertNull(root);
	}
	
	@Test
	public void when_insert_one_node_it_must_be_the_root() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(50, "A");
		
		RedBlackKeyValueNode<Integer, String> root = getRootFromMap(tree);
		
		assertRedBlackNode(new Integer(50), "A", ColorRedBlackNode.BLACK, root);
	}
	
	@Test
	public void when_insert_with_balanced_elements_must_insert_without_rotate_or_change_colors() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(50, "A"); tree.put(20, "B"); tree.put(80, "C");
		
		RedBlackKeyValueNode<Integer, String> root = getRootFromMap(tree);
		
		assertRedBlackNode(new Integer(50), "A", ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(20), "B", ColorRedBlackNode.RED, root.getLeft());
		assertRedBlackNode(new Integer(80), "C", ColorRedBlackNode.RED, root.getRight());
	}
	
	@Test
	public void when_insert_with_balanced_elements_must_change_the_colors_of_parents_if_necessary() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(50, "A"); tree.put(20, "B"); tree.put(80, "C"); tree.put(10, "D");
		
		RedBlackKeyValueNode<Integer, String> root = getRootFromMap(tree);
		
		assertRedBlackNode(new Integer(50), "A", ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(20), "B", ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(80), "C", ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(10), "D", ColorRedBlackNode.RED, root.getLeft().getLeft());
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_left_left_position_must_balance_it() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(50, "A"); tree.put(20, "B"); tree.put(80, "C"); tree.put(10, "D"); 
		tree.put(5, "E");
		
		RedBlackKeyValueNode<Integer, String> root = getRootFromMap(tree);
		
		assertRedBlackNode(new Integer(50), "A", ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(10), "D", ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(80), "C", ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(5), "E", ColorRedBlackNode.RED, root.getLeft().getLeft());
		assertRedBlackNode(new Integer(20), "B", ColorRedBlackNode.RED, root.getLeft().getRight());
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_left_right_position_must_balance_it() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(50, "A"); tree.put(20, "B"); tree.put(80, "C"); tree.put(10, "D"); 
		tree.put(15, "E");
		
		RedBlackKeyValueNode<Integer, String> root = getRootFromMap(tree);
		
		assertRedBlackNode(new Integer(50), "A", ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(15), "E", ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(80), "C", ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(10), "D", ColorRedBlackNode.RED, root.getLeft().getLeft());
		assertRedBlackNode(new Integer(20), "B", ColorRedBlackNode.RED, root.getLeft().getRight());
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_right_left_position_must_balance_it() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(50, "A"); tree.put(20, "B"); tree.put(80, "C"); tree.put(90, "D"); tree.put(85, "E");
		
		RedBlackKeyValueNode<Integer, String> root = getRootFromMap(tree);
		
		assertRedBlackNode(new Integer(50), "A", ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(20), "B", ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(85), "E", ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(80), "C", ColorRedBlackNode.RED, root.getRight().getLeft());
		assertRedBlackNode(new Integer(90), "D", ColorRedBlackNode.RED, root.getRight().getRight());
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_right_right_position_must_balance_it() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(50, "A"); tree.put(20, "B"); tree.put(80, "C"); tree.put(90, "D"); tree.put(100, "E");
		
		RedBlackKeyValueNode<Integer, String> root = getRootFromMap(tree);
		
		assertRedBlackNode(new Integer(50), "A", ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(20), "B", ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(90), "D", ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(80), "C", ColorRedBlackNode.RED, root.getRight().getLeft());
		assertRedBlackNode(new Integer(100), "E", ColorRedBlackNode.RED, root.getRight().getRight());
	}
	
	@Test
	public void when_insert_with_random_elements_must_have_not_violations_1() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(35, "A");
		tree.put(25, "B"); tree.put(70, "C"); tree.put(10, "D"); tree.put(100, "E"); 
		tree.put(20, "F"); tree.put(5, "G"); tree.put(85, "H"); tree.put(55, "I"); tree.put(40, "J");

		RedBlackKeyValueNode<Integer, String> root = getRootFromMap(tree);
		
		assertRedBlackNode(new Integer(35), "A", ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(20), "F", ColorRedBlackNode.RED, root.getLeft());
		assertRedBlackNode(new Integer(10), "D", ColorRedBlackNode.BLACK, root.getLeft().getLeft());
		assertRedBlackNode(new Integer(25), "B", ColorRedBlackNode.BLACK, root.getLeft().getRight());
		assertRedBlackNode(new Integer(5), "G", ColorRedBlackNode.RED, root.getLeft().getLeft().getLeft());
		
		assertRedBlackNode(new Integer(85), "H", ColorRedBlackNode.RED, root.getRight());
		assertRedBlackNode(new Integer(55), "I", ColorRedBlackNode.BLACK, root.getRight().getLeft());
		assertRedBlackNode(new Integer(40), "J", ColorRedBlackNode.RED, root.getRight().getLeft().getLeft());
		assertRedBlackNode(new Integer(70), "C", ColorRedBlackNode.RED, root.getRight().getLeft().getRight());
		assertRedBlackNode(new Integer(100), "E", ColorRedBlackNode.BLACK, root.getRight().getRight());
	}
	
	@Test
	public void when_insert_with_random_elements_must_have_not_violations_2() {
		TreeMap<Integer, String> tree = example();
		
		RedBlackKeyValueNode<Integer, String> root = getRootFromMap(tree);
		
		assertRedBlackNode(new Integer(33), "F", ColorRedBlackNode.BLACK, root);
		//left
		assertRedBlackNode(new Integer(7), "B", ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(3), "A", ColorRedBlackNode.BLACK, root.getLeft().getLeft());
		assertRedBlackNode(new Integer(4), "D", ColorRedBlackNode.RED, root.getLeft().getLeft().getRight());
		assertRedBlackNode(new Integer(18), "G", ColorRedBlackNode.BLACK, root.getLeft().getRight());
		assertRedBlackNode(new Integer(10), "I", ColorRedBlackNode.RED, root.getLeft().getRight().getLeft());
		assertRedBlackNode(new Integer(24), "M", ColorRedBlackNode.RED, root.getLeft().getRight().getRight());
		//right
		assertRedBlackNode(new Integer(93), "O", ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(39), "C", ColorRedBlackNode.RED, root.getRight().getLeft());
		assertRedBlackNode(new Integer(34), "J", ColorRedBlackNode.BLACK, root.getRight().getLeft().getLeft());
		assertRedBlackNode(new Integer(70), "H", ColorRedBlackNode.BLACK, root.getRight().getLeft().getRight());
		assertRedBlackNode(new Integer(51), "N", ColorRedBlackNode.RED, root.getRight().getLeft().getRight().getLeft());
		assertRedBlackNode(new Integer(86), "L", ColorRedBlackNode.RED, root.getRight().getLeft().getRight().getRight());
		assertRedBlackNode(new Integer(95), "E", ColorRedBlackNode.BLACK, root.getRight().getRight());
	}
	
	@Test
	public void when_put_with_repeated_key_must_not_increment_size_and_replace_the_value() {
		TreeMap<Integer, String> tree = example();
		tree.put(34, "JJJ");
		RedBlackKeyValueNode<Integer, String> root = getRootFromMap(tree);
		
		assertRedBlackNode(new Integer(34), "JJJ", ColorRedBlackNode.BLACK, root.getRight().getLeft().getLeft());
		assertEquals(14, tree.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void when_put_with_null_key_must_throws_null_pointer_exception() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(null, "A");
	}

	@Test
	public void when_get_with_existing_element_greater_than_root_must_return_it() {
		TreeMap<Integer,String> tree = example();
		
		assertEquals("N", tree.get(51));
		assertEquals("L", tree.get(86));
	}
	
	@Test
	public void when_get_with_existing_element_smaller_than_root_must_return_it() {
		TreeMap<Integer,String> tree = example();
		
		assertEquals("B", tree.get(7));
		assertEquals("M", tree.get(24));
	}
	
	@Test
	public void when_get_with_existing_element_in_root_place_must_return_it() {
		TreeMap<Integer,String> tree = example();
		
		assertEquals("F", tree.get(33));
	}
	
	@Test
	public void when_get_with_non_existing_element_must_return_null() {
		TreeMap<Integer,String> tree = example();
		
		assertNull(tree.get(100));
	}
	
	@Test(expected=NullPointerException.class)
	public void when_get_with_null_key_must_throws_null_pointer_exception() {
		TreeMap<Integer,String> tree = example();
		
		tree.get(null);
	}
	
	@Test
	public void when_get_or_default_with_existing_element_greater_than_root_must_return_it() {
		TreeMap<Integer,String> tree = example();
		
		assertEquals("N", tree.getOrDefault(51, "ABC"));
		assertEquals("L", tree.getOrDefault(86, "ABC"));
	}
	
	@Test
	public void when_get_or_default_with_existing_element_smaller_than_root_must_return_it() {
		TreeMap<Integer,String> tree = example();
		
		assertEquals("B", tree.getOrDefault(7, "ABC"));
		assertEquals("M", tree.getOrDefault(24, "ABC"));
	}
	
	@Test
	public void when_get_or_default_with_existing_element_in_root_place_must_return_it() {
		TreeMap<Integer,String> tree = example();
		
		assertEquals("F", tree.getOrDefault(33, "ABC"));
	}
	
	@Test
	public void when_get_or_default_with_non_existing_element_must_return_default_element() {
		TreeMap<Integer,String> tree = example();
		
		assertEquals("ABC", tree.getOrDefault(100, "ABC"));
	}
	
	@Test(expected=NullPointerException.class)
	public void when_get_or_default_with_null_key_must_throws_null_pointer_exception() {
		TreeMap<Integer,String> tree = example();
		
		tree.getOrDefault(null, "ABC");
	}
	
	@Test
	public void when_contains_key_with_existint_elements_must_return_true() {
		TreeMap<Integer,String> tree = example();
		
		assertTrue(tree.containsKey(93));
		assertTrue(tree.containsKey(39));
		assertTrue(tree.containsKey(18));
		assertTrue(tree.containsKey(33));
		assertTrue(tree.containsKey(24));
	}
	
	@Test
	public void when_contains_key_with_non_existing_element_must_return_false() {
		TreeMap<Integer,String> tree = example();
		
		assertFalse(tree.containsKey(100));
		assertFalse(tree.containsKey(1));
		assertFalse(tree.containsKey(32));
		assertFalse(tree.containsKey(55));
		assertFalse(tree.containsKey(21));
	}
	
	@Test(expected=NullPointerException.class)
	public void when_contains_key_with_null_key_must_throws_null_pointer_exception() {
		TreeMap<Integer,String> tree = example();
		
		tree.containsKey(null);
	}
	
	@Test
	public void when_contains_value_with_existint_elements_must_return_true() {
		TreeMap<Integer,String> tree = example();
		
		assertTrue(tree.containsValue("A"));
		assertTrue(tree.containsValue("C"));
		assertTrue(tree.containsValue("D"));
		assertTrue(tree.containsValue("J"));
		assertTrue(tree.containsValue("L"));
	}
	
	@Test
	public void when_contains_value_with_non_existing_element_must_return_false() {
		TreeMap<Integer,String> tree = example();
		
		assertFalse(tree.containsValue("AA"));
		assertFalse(tree.containsValue("CC"));
		assertFalse(tree.containsValue("DD"));
		assertFalse(tree.containsValue("JJ"));
		assertFalse(tree.containsValue("LL"));
	}
	
	@Test
	public void when_contains_value_with_null_value_and_the_map_does_not_have_must_return_false() {
		TreeMap<Integer,String> tree = example();
		assertFalse(tree.containsValue(null));
	}
	
	@Test
	public void when_contains_value_with_null_value_and_the_map_have_at_least_one_null_value_must_return_true() {
		TreeMap<Integer,String> tree = example();
		tree.put(99, null);
		assertTrue(tree.containsValue(null));
	}
	
	@Test
	public void when_size_with_empty_tree_map_must_return_0() {
		TreeMap<Integer, String> tree = new TreeMap<Integer, String>();
		assertEquals(0, tree.size());
	}
	
	@Test
	public void when_size_with_single_element_tree_map_must_return_1() {
		TreeMap<Integer, String> tree = new TreeMap<Integer, String>();
		tree.put(1, "A");
		assertEquals(1, tree.size());
	}
	
	@Test
	public void when_size_with_several_inserted_elements_must_return_the_right_number() {
		TreeMap<Integer, String> tree = example();
		assertEquals(14, tree.size());
	}
	
	@Test
	public void when_is_empty_with_empty_tree_map_must_return_true() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		assertTrue(tree.isEmpty());
	}
	
	@Test
	public void when_is_empty_with_non_empty_tree_map_must_return_false() {
		TreeMap<Integer, String> tree = example();
		assertFalse(tree.isEmpty());
	}
	
	@Test
	public void when_keys_with_several_elements_must_return_in_ascending_order() {
		TreeMap<Integer, String> tree = example();
		
		Object[] keys = tree.keys();

		assertEquals(new Integer(3), keys[0]);
		assertEquals(new Integer(4), keys[1]);
		assertEquals(new Integer(7), keys[2]);
		assertEquals(new Integer(10), keys[3]);
		assertEquals(new Integer(18), keys[4]);
		assertEquals(new Integer(24), keys[5]);
		assertEquals(new Integer(33), keys[6]);
		assertEquals(new Integer(34), keys[7]);
		assertEquals(new Integer(39), keys[8]);
		assertEquals(new Integer(51), keys[9]);
		assertEquals(new Integer(70), keys[10]);
		assertEquals(new Integer(86), keys[11]);
		assertEquals(new Integer(93), keys[12]);
		assertEquals(new Integer(95), keys[13]);
	}
	
	@Test
	public void when_keys_with_empty_map_must_return_empty_array() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		
		Object[] keys = tree.keys();
		
		assertEquals(0, keys.length);
	}
	
	@Test
	public void when_values_with_several_elements_must_return_in_keys_ascending_order() {
		TreeMap<Integer, String> tree = example();
		
		Object[] keys = tree.values();

		assertEquals("A", keys[0]);
		assertEquals("D", keys[1]);
		assertEquals("B", keys[2]);
		assertEquals("I", keys[3]);
		assertEquals("G", keys[4]);
		assertEquals("M", keys[5]);
		assertEquals("F", keys[6]);
		assertEquals("J", keys[7]);
		assertEquals("C", keys[8]);
		assertEquals("N", keys[9]);
		assertEquals("H", keys[10]);
		assertEquals("L", keys[11]);
		assertEquals("O", keys[12]);
		assertEquals("E", keys[13]);
	}
	
	@Test
	public void when_value_with_empty_map_must_return_empty_array() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		
		Object[] keys = tree.values();
		
		assertEquals(0, keys.length);
	}
	
	@Test(expected=NullPointerException.class)
	public void when_highest_with_null_key_must_return_null_pointer_exception() {
		TreeMap<Integer, String> tree = new TreeMap<Integer, String>();
		
		assertNull(tree.highest(null));
	}
	
	@Test
	public void when_highest_with_empty_map_must_return_null() {
		TreeMap<Integer, String> tree = new TreeMap<Integer, String>();
		
		assertNull(tree.highest(100));
	}
	
	@Test
	public void when_highest_with_single_element_map_must_return_root_element() {
		TreeMap<Integer, String> tree = new TreeMap<Integer, String>();
		tree.put(100, "AS");
		
		Entry<Integer, String> highest = tree.highest(80);
		
		assertEquals(new Integer(100), highest.getKey());
		assertEquals("AS", highest.getValue());
	}
	
	@Test
	public void when_highest_with_key_greater_than_all_keys_in_map_must_return_null() {
		TreeMap<Integer, String> tree = example();
		
		assertNull(tree.highest(10000));
	}
	
	@Test
	public void when_highest_with_key_lesser_than_smaller_element_in_map_must_return_the_smaller_entry() {
		TreeMap<Integer, String> tree = example();
		
		Entry<Integer, String> highest = tree.highest(0);
		assertEquals(new Integer(3), highest.getKey());
		assertEquals("A", highest.getValue());
	}
	
	@Test
	public void when_highest_with_key_successor_is_a_leaf_in_the_left_must_return_it() {
		TreeMap<Integer, String> tree = example();
		
		Entry<Integer, String> highest = tree.highest(40);
		assertEquals(new Integer(51), highest.getKey());
		assertEquals("N", highest.getValue());
		
		highest = tree.highest(94);
		assertEquals(new Integer(95), highest.getKey());
		assertEquals("E", highest.getValue());
		
		highest = tree.highest(9);
		assertEquals(new Integer(10), highest.getKey());
		assertEquals("I", highest.getValue());
	}
	
	@Test
	public void when_highest_with_key_is_greater_than_last_valid_node_must_come_back_and_find_successor_while_right_child_parent_s_is_not_the_head() {
		TreeMap<Integer, String> tree = example();
		
		Entry<Integer, String> highest = tree.highest(92);
		assertEquals(new Integer(93), highest.getKey());
		assertEquals("O", highest.getValue());
		
		highest = tree.highest(17);
		assertEquals(new Integer(18), highest.getKey());
		assertEquals("G", highest.getValue());
	}
	
	@Test(expected=NullPointerException.class)
	public void when_lowest_with_null_key_must_return_null_pointer_exception() {
		TreeMap<Integer, String> tree = new TreeMap<Integer, String>();
		
		assertNull(tree.lowest(null));
	}
	
	@Test
	public void when_lowest_with_empty_map_must_return_null() {
		TreeMap<Integer, String> tree = new TreeMap<Integer, String>();
		
		assertNull(tree.lowest(100));
	}
	
	@Test
	public void when_lesser_with_key_lesser_than_all_keys_in_map_must_return_null() {
		TreeMap<Integer, String> tree = example();
		
		assertNull(tree.lowest(1));
	}
	
	@Test
	public void when_lowest_with_key_greater_than_greatest_element_in_map_must_return_the_greatest_entry() {
		TreeMap<Integer, String> tree = example();
		
		Entry<Integer, String> lowest = tree.lowest(1000);
		assertEquals(new Integer(95), lowest.getKey());
		assertEquals("E", lowest.getValue());
	}
	
	@Test
	public void when_lowest_with_key_successor_is_a_leaf_in_the_left_must_return_it() {
		TreeMap<Integer, String> tree = example();
		
		Entry<Integer, String> lowest = tree.lowest(55);
		assertEquals(new Integer(51), lowest.getKey());
		assertEquals("N", lowest.getValue());
		
		lowest = tree.lowest(97);
		assertEquals(new Integer(95), lowest.getKey());
		assertEquals("E", lowest.getValue());
		
		lowest = tree.lowest(11);
		assertEquals(new Integer(10), lowest.getKey());
		assertEquals("I", lowest.getValue());
	}
	
	@Test
	public void when_lowest_with_key_is_lesser_than_last_valid_node_must_come_back_and_find_successor_while_left_child_parent_s_is_not_the_head() {
		TreeMap<Integer, String> tree = example();
		
		Entry<Integer, String> lowest = tree.lowest(94);
		assertEquals(new Integer(93), lowest.getKey());
		assertEquals("O", lowest.getValue());
		
		lowest = tree.lowest(19);
		assertEquals(new Integer(18), lowest.getKey());
		assertEquals("G", lowest.getValue());
	}
	
	@Test
	public void when_lowest_with_single_element_map_must_return_root_element() {
		TreeMap<Integer, String> tree = new TreeMap<Integer, String>();
		tree.put(100, "AS");
		
		Entry<Integer, String> lowest = tree.lowest(180);
		
		assertEquals(new Integer(100), lowest.getKey());
		assertEquals("AS", lowest.getValue());
	}
	
	@Test
	public void when_sub_map_with_empty_tree_map_must_return_empty_map() {
		TreeMap<Integer, String> tree = new TreeMap<Integer, String>();
		
		assertTrue( tree.subMap(0, 100, false).isEmpty() );
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void when_sub_map_with_wront_values_from_and_to_must_return_error() {
		TreeMap<Integer, String> tree = example();
		
		tree.subMap(10, 5, false);
	}
	
	@Test
	public void when_sub_map_with_from_and_to_values_greater_than_root_must_return_sub_map_with_only_right_children_elements() {
		TreeMap<Integer, String> tree = example();
		
		NavigableMap<Integer, String> subMap = tree.subMap(35, 80, false);
		
		assertEquals(3, subMap.size());
		assertNotNull(subMap.get(51));
		assertNotNull(subMap.get(39));
		assertNotNull(subMap.get(70));
	}
	
	@Test
	public void when_sub_map_with_from_and_to_values_lesser_than_root_must_return_sub_map_with_only_left_children_elements() {
		TreeMap<Integer, String> tree = example();
		
		NavigableMap<Integer, String> subMap = tree.subMap(6, 17, false);
		
		assertEquals(2, subMap.size());
		assertNotNull(subMap.get(7));
		assertNotNull(subMap.get(10));
	}
	
	@Test
	public void when_sub_map_with_from_lesser_than_root_and_to_greater_than_root_must_return_sub_map_with_mix_sides() {
		TreeMap<Integer, String> tree = example();
		
		NavigableMap<Integer, String> subMap = tree.subMap(20, 55, false);
		
		assertEquals(5, subMap.size());
		assertNotNull(subMap.get(39));
		assertNotNull(subMap.get(33));
		assertNotNull(subMap.get(24));
		assertNotNull(subMap.get(34));
		assertNotNull(subMap.get(51));
	}
	
	@Test
	public void when_sub_map_with_from_and_to_lesser_than_any_element_in_map_must_return_empty_map() {
		TreeMap<Integer, String> tree = example();
		
		NavigableMap<Integer, String> subMap = tree.subMap(0, 2, false);
		
		assertEquals(0, subMap.size());
	}
	
	@Test
	public void when_sub_map_with_from_and_to_higher_than_any_element_in_map_must_return_empty_map() {
		TreeMap<Integer, String> tree = example();
	
		NavigableMap<Integer, String> subMap = tree.subMap(100, 150, false);

		assertEquals(0, subMap.size());
	}
	
	@Test
	public void when_sub_map_with_inclusive_flag_must_return_map_with_from_and_to_elements_if_exists() {
		TreeMap<Integer, String> tree = example();
		NavigableMap<Integer, String> subMap = tree.subMap(33, 70, true);
		
		assertEquals(5, subMap.size());
		
		assertNotNull(subMap.get(33));
		assertNotNull(subMap.get(70));
		assertNotNull(subMap.get(39));
		assertNotNull(subMap.get(34));
		assertNotNull(subMap.get(51));
	}
	
	@Test
	public void when_sub_map_with_inclusive_flag_false_must_return_map_excluding_from_and_to_elements_if_exists() {
		TreeMap<Integer, String> tree = example();
		
		NavigableMap<Integer, String> subMap = tree.subMap(33, 70, false);
		
		assertEquals(3, subMap.size());

		assertNotNull(subMap.get(39));
		assertNotNull(subMap.get(34));
		assertNotNull(subMap.get(51));
	}
	
	@Test
	public void when_head_with_empty_map_must_return_empty_map() {
		TreeMap<Integer, String> tree = new TreeMap<Integer, String>();
		
		assertTrue(tree.headMap(50, true).isEmpty());
	}
	
	@Test
	public void when_head_with_inclusive_flag_and_populated_map_must_return_a_map_with_all_values_lesser_or_equal_key() {
		TreeMap<Integer, String> tree = example();
		
		NavigableMap<Integer,String> headMap = tree.headMap(39, true);
		assertEquals(9, headMap.size());
		
		assertNotNull(headMap.get(39));
		assertNotNull(headMap.get(7));
		assertNotNull(headMap.get(4));
		assertNotNull(headMap.get(33));
		assertNotNull(headMap.get(34));
		assertNotNull(headMap.get(24));
		assertNotNull(headMap.get(3));
		assertNotNull(headMap.get(18));
		assertNotNull(headMap.get(10));
	}
	
	@Test
	public void when_head_with_inclusive_flag_false_and_populated_map_must_return_a_map_with_all_values_lesser_than_key() {
		TreeMap<Integer, String> tree = example();
		
		NavigableMap<Integer,String> headMap = tree.headMap(39, false);
		assertEquals(8, headMap.size());
		
		assertNotNull(headMap.get(7));
		assertNotNull(headMap.get(4));
		assertNotNull(headMap.get(33));
		assertNotNull(headMap.get(34));
		assertNotNull(headMap.get(24));
		assertNotNull(headMap.get(3));
		assertNotNull(headMap.get(18));
		assertNotNull(headMap.get(10));
	}
	
	@Test
	public void when_tail_with_empty_map_must_return_empty_map() {
		TreeMap<Integer, String> tree = new TreeMap<Integer, String>();
		
		assertTrue(tree.tailMap(50, true).isEmpty());
	}
	
	@Test
	public void when_tail_with_inclusive_flag_and_populated_map_must_return_a_map_with_all_values_greater_or_equal_key() {
		NavigableMap<Integer,String> tree = example();
				
		NavigableMap<Integer, String> tailMap = tree.tailMap(51, true);
		assertEquals(5, tailMap.size());
		
		assertNotNull(tailMap.get(51));
		assertNotNull(tailMap.get(93));
		assertNotNull(tailMap.get(70));
		assertNotNull(tailMap.get(95));
		assertNotNull(tailMap.get(86));
	}
	
	@Test
	public void when_tail_with_inclusive_flag_false_and_populated_map_must_return_a_map_with_all_values_greater_than_key() {
		NavigableMap<Integer,String> tree = example();
		
		NavigableMap<Integer, String> tailMap = tree.tailMap(51, false);
		assertEquals(4, tailMap.size());
		
		assertNotNull(tailMap.get(93));
		assertNotNull(tailMap.get(70));
		assertNotNull(tailMap.get(95));
		assertNotNull(tailMap.get(86));
	}
	
	private TreeMap<Integer, String> example() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(93, "O"); tree.put(3, "A"); 
		tree.put(7, "B");  tree.put(39, "C"); 
		tree.put(4, "D");  tree.put(95, "E");
		tree.put(33, "F"); tree.put(18, "G");
		tree.put(70, "H"); tree.put(10, "I");
		tree.put(34, "J"); tree.put(86, "L");
		tree.put(24, "M"); tree.put(51, "N");
		return tree;
	}
	
	private <K extends Comparable<K>, V> void assertRedBlackNode(K expectedKey, V expectedValue, ColorRedBlackNode expectedColor, RedBlackKeyValueNode<K, V> actual) {
		assertEquals(expectedKey, actual.getKey());
		assertEquals(expectedValue, actual.getValue());
		assertEquals(expectedColor, actual.getColor());
	}

	@SuppressWarnings("unchecked")
	private <K extends Comparable<K>, V> RedBlackKeyValueNode<K, V> getRootFromMap(TreeMap<K, V> tree) {
		return (RedBlackKeyValueNode<K, V>) ReflectionTestUtils.getField(tree, "root");
	}
	
}
