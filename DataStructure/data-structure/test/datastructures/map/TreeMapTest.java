package datastructures.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.tree.ColorRedBlackNode;
import datastructures.maps.TreeMap;

@RunWith(JUnit4.class)
public class TreeMapTest {

	@Test
	public void when_construct_must_create_correctly() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		
		RedBlackEntryNode<Integer, String> root = getRootFromMap(tree);
		
		assertNull(root);
	}
	
	@Test
	public void when_insert_with_balanced_elements_must_insert_without_rotate_or_change_colors() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(50, "A"); tree.put(20, "B"); tree.put(80, "C");
		
		RedBlackEntryNode<Integer, String> root = getRootFromMap(tree);
		
		assertRedBlackNode(new Integer(50), "A", ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(20), "B", ColorRedBlackNode.RED, root.getLeft());
		assertRedBlackNode(new Integer(80), "C", ColorRedBlackNode.RED, root.getRight());
	}
	
	@Test
	public void when_insert_with_balanced_elements_must_change_the_colors_of_parents_if_necessary() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(50, "A"); tree.put(20, "B"); tree.put(80, "C"); tree.put(10, "D");
		
		RedBlackEntryNode<Integer, String> root = getRootFromMap(tree);
		
		assertRedBlackNode(new Integer(50), "A", ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(20), "B", ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(80), "C", ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(10), "D", ColorRedBlackNode.RED, root.getLeft().getLeft());
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_left_left_position_must_balance_it() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(50, "A"); tree.put(20, "B"); tree.put(80, "C"); tree.put(10, "D"); tree.put(5, "E");
		
		RedBlackEntryNode<Integer, String> root = getRootFromMap(tree);
		
		assertRedBlackNode(new Integer(50), "A", ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(10), "D", ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(80), "C", ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(5), "E", ColorRedBlackNode.RED, root.getLeft().getLeft());
		assertRedBlackNode(new Integer(20), "B", ColorRedBlackNode.RED, root.getLeft().getRight());
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_left_right_position_must_balance_it() {
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(50, "A"); tree.put(20, "B"); tree.put(80, "C"); tree.put(10, "D"); tree.put(15, "E");
		
		RedBlackEntryNode<Integer, String> root = getRootFromMap(tree);
		
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
		
		RedBlackEntryNode<Integer, String> root = getRootFromMap(tree);
		
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
		
		RedBlackEntryNode<Integer, String> root = getRootFromMap(tree);
		
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

		RedBlackEntryNode<Integer, String> root = getRootFromMap(tree);
		
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
		TreeMap<Integer, String> tree = new TreeMap<>();
		tree.put(93, "O"); tree.put(3, "A"); 
		tree.put(7, "B");  tree.put(39, "C"); 
		tree.put(4, "D");  tree.put(95, "E");
		tree.put(33, "F"); tree.put(18, "G");
		tree.put(70, "H"); tree.put(10, "I");
		tree.put(34, "J"); tree.put(86, "L");
		tree.put(24, "M"); tree.put(51, "N");
		
		RedBlackEntryNode<Integer, String> root = getRootFromMap(tree);
		
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

	private <K, V> void assertRedBlackNode(K expectedKey, V expectedValue, ColorRedBlackNode expectedColor, RedBlackEntryNode<K, V> actual) {
		assertEquals(expectedKey, actual.getKey());
		assertEquals(expectedValue, actual.getValue());
		assertEquals(expectedColor, actual.getColor());
	}

	@SuppressWarnings("unchecked")
	private <K, V> RedBlackEntryNode<K, V> getRootFromMap(TreeMap<K, V> tree) {
		return (RedBlackEntryNode<K, V>) ReflectionTestUtils.getField(tree, "root");
	}
	
}
