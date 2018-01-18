package datastructures.trees;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.tree.ColorRedBlackNode;
import datastructures.base.tree.RedBlackNode;

public class RedBlackTreeTest {

	private RedBlackTree<Integer> tree;

	@Before
	public void setUp() {
		tree = new RedBlackTree<Integer>(50);
	}
	
	@Test
	public void when_insert_with_balanced_elements_must_insert_without_rotate_or_change_colors() {
		tree.insert(20); tree.insert(80);
		
		RedBlackNode<Integer> root = getRootFromTree();
		
		assertRedBlackNode(new Integer(50), ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(20), ColorRedBlackNode.RED, root.getLeft());
		assertRedBlackNode(new Integer(80), ColorRedBlackNode.RED, root.getRight());
	}
	
	@Test
	public void when_insert_with_balanced_elements_must_change_the_colors_of_parents_if_necessary() {
		
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_left_left_position_must_balance_it() {
		
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_left_right_position_must_balance_it() {
		
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_right_left_position_must_balance_it() {
		
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_right_right_position_must_balance_it() {
		
	}

	private <T> void assertRedBlackNode(T expectedElement, ColorRedBlackNode expectedColor, RedBlackNode<Integer> actual) {
		assertEquals(expectedElement, actual.getElement());
		assertEquals(expectedColor, actual.getColor());
	}

	@SuppressWarnings("unchecked")
	private <T extends Comparable<T>> RedBlackNode<T> getRootFromTree() {
		return (RedBlackNode<T>) ReflectionTestUtils.getField(tree, "root");
	}

	
}
