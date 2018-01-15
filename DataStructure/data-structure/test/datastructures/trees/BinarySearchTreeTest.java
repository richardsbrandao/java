package datastructures.trees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.tree.TreeNode;
import datastructures.lists.Array;
import datastructures.trees.BinarySearchTree;

@RunWith(JUnit4.class)
public class BinarySearchTreeTest {

	private BinarySearchTree<Integer> tree;
	
	@Before
	public void setUp() {
		this.tree = new BinarySearchTree<Integer>(50);
	}
	
	@Test
	public void when_initialize_with_correct_element_it_must_be_the_root() {
		TreeNode<Integer> root = getRootFromTree();
		Integer size = getSizeFromTree();
		
		assertEquals(new Integer(50), root.getElement());
		assertNull(root.getLeft());
		assertNull(root.getRight());
		assertEquals(new Integer(1), size);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void when_initialize_with_null_element_must_throw_error() {
		new BinarySearchTree<Integer>(null);
	}
	
	@Test
	public void when_add_with_element_less_than_root_must_be_on_left_side() {
		tree.add(20);
		
		TreeNode<Integer> root = getRootFromTree();
		Integer size = getSizeFromTree();
		
		assertEquals(new Integer(50), root.getElement());
		assertEquals(new Integer(20), root.getLeft().getElement());
		assertNull(root.getRight());
		assertEquals(new Integer(2), size);
	}

	@Test
	public void when_add_with_element_greather_than_root_must_be_on_right_side() {
		tree.add(80);
		
		TreeNode<Integer> root = getRootFromTree();
		Integer size = getSizeFromTree();
		
		assertEquals(new Integer(50), root.getElement());
		assertNull(root.getLeft());
		assertEquals(new Integer(80), root.getRight().getElement());
		assertEquals(new Integer(2), size);
		
	}
	
	@Test
	public void when_add_with_several_element_it_must_compose_a_binnary_search_tree() {
		example();
		
		TreeNode<Integer> root = getRootFromTree();
		Integer size = getSizeFromTree();
		
		assertEquals(new Integer(13), size);

		assertEquals(new Integer(50), root.getElement());
		assertEquals(new Integer(20), root.getLeft().getElement());
		assertEquals(new Integer(14), root.getLeft().getLeft().getElement());
		assertTrue(root.getLeft().getLeft().isLeaf());
		
		assertEquals(new Integer(33), root.getLeft().getRight().getElement());
		assertEquals(new Integer(22), root.getLeft().getRight().getLeft().getElement());
		assertEquals(new Integer(41), root.getLeft().getRight().getRight().getElement());
		assertTrue(root.getLeft().getRight().getRight().isLeaf());
		
		assertEquals(new Integer(25), root.getLeft().getRight().getLeft().getRight().getElement());
		assertTrue(root.getLeft().getRight().getLeft().getRight().isLeaf());
		
		assertEquals(new Integer(80), root.getRight().getElement());
		assertEquals(new Integer(67), root.getRight().getLeft().getElement());
		assertEquals(new Integer(52), root.getRight().getLeft().getLeft().getElement());
		assertTrue(root.getRight().getLeft().getLeft().isLeaf());
		assertEquals(new Integer(73), root.getRight().getLeft().getRight().getElement());
		assertTrue(root.getRight().getLeft().getLeft().isLeaf());
		
		assertEquals(new Integer(93), root.getRight().getRight().getElement());
		assertEquals(new Integer(83), root.getRight().getRight().getLeft().getElement());
		assertTrue(root.getRight().getRight().getLeft().isLeaf());
	}
	
	@Test
	public void when_get_for_valids_elements_must_return_it() {
		example();
		
		assertEquals(new Integer(50), tree.get(50).getElement());
		assertEquals(new Integer(80), tree.get(80).getElement());
		assertEquals(new Integer(67), tree.get(67).getElement());
		assertEquals(new Integer(14), tree.get(14).getElement());
		assertEquals(new Integer(93), tree.get(93).getElement());
		assertEquals(new Integer(25), tree.get(25).getElement());
	}
	
	@Test
	public void when_get_for_invalids_elements_must_return_null() {
		example();
		
		assertNull(tree.get(103));
		assertNull(tree.get(17));
		assertNull(tree.get(21));
		assertNull(tree.get(-1));
	}
	
	@Test
	public void when_contains_for_valid_elements_must_return_true() {
		example();
		
		assertTrue(tree.contains(50));
		assertTrue(tree.contains(80));
		assertTrue(tree.contains(67));
		assertTrue(tree.contains(14));
		assertTrue(tree.contains(93));
		assertTrue(tree.contains(25));
	}
	
	@Test
	public void when_contains_for_invalid_elements_must_return_false() {
		example();
		
		assertFalse(tree.contains(103));
		assertFalse(tree.contains(17));
		assertFalse(tree.contains(21));
		assertFalse(tree.contains(-1));
	}
	
	@Test
	public void when_size_with_single_element_tree_must_return_one() {
		assertEquals(1, tree.size());
	}
	
	public void when_size_with_a_tree_with_n_elements_must_return_n() { 
		example();
		
		assertEquals(13, tree.size());
	}
	
	@Test
	public void when_max_with_a_tree_with_one_element_must_return_root() {
		assertEquals(new Integer(50), tree.max());
	}

	@Test
	public void when_max_with_a_tree_with_n_elements_must_return_very_right_elements() {
		example();
		assertEquals(new Integer(93), tree.max());
	}

	@Test
	public void when_mix_with_a_tree_with_one_element_must_return_root() {
		assertEquals(new Integer(50), tree.min());
	}

	@Test
	public void when_mix_with_a_tree_with_n_elements_must_return_very_left_elements() {
		example();
		assertEquals(new Integer(14), tree.min());
	}
	
	@Test
	public void when_height_with_a_single_element_tree_must_return_1() {
		assertEquals(1, tree.height());
	}
	
	@Test
	public void when_height_with_a_tree_with_n_elements_must_return_the_highest_deapth() {
		example();
		assertEquals(5, tree.height());
	}
	
	@Test
	public void when_deapth_for_root_element_must_return_0() {
		example();
		assertEquals(0, tree.deapth(50));
	}

	@Test
	public void when_deapth_for_valid_element_in_a_tree_with_n_elements_must_return_the_death() {
		example();
		assertEquals(1, tree.deapth(20));
		assertEquals(2, tree.deapth(67));
		assertEquals(3, tree.deapth(83));
		assertEquals(4, tree.deapth(25));
	}

	@Test
	public void when_deapth_for_a_invalid_element_must_return_the_minus_1() {
		example();
		assertEquals(-1, tree.deapth(-1));
		assertEquals(-1, tree.deapth(10));
		assertEquals(-1, tree.deapth(99));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void when_remove_root_element_in_a_single_element_tree_must_throw_error() {
		tree.remove(50);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void when_remove_root_in_a_tree_with_n_elements_must_choose_the_first_right_child_as_root_to_replace() {
		example();
		tree.remove(50);
		
		assertEquals(new Integer(12), getSizeFromTree());
		/**
		 * 			80
		 * 		   /  \
		 * 		  67   93
		 * 	     /  \   / 
		 * 	    52  73 83     
		 */
		TreeNode<Integer> root = getRootFromTree();
		assertEquals(new Integer(80), root.getElement());
		assertEquals(new Integer(20), root.getLeft().getElement());
		assertEquals(new Integer(93), root.getRight().getElement());
		
		assertEquals(new Integer(67), root.getRight().getLeft().getElement());
		assertEquals(new Integer(52), root.getRight().getLeft().getLeft().getElement());
		assertEquals(new Integer(73), root.getRight().getLeft().getRight().getElement());
		assertEquals(new Integer(93), root.getRight().getRight().getElement());
		assertEquals(new Integer(83), root.getRight().getRight().getLeft().getElement());
	}
	
	@Test
	public void when_remove_leaf_node_must_not_change_anything_to_replace() {
		example();
		tree.remove(52);

		assertEquals(new Integer(12), getSizeFromTree());
		
		TreeNode<Integer> root = getRootFromTree();
		assertEquals(new Integer(50), root.getElement());
		assertEquals(new Integer(20), root.getLeft().getElement());
		assertEquals(new Integer(80), root.getRight().getElement());
		assertNull(root.getRight().getLeft().getLeft());
	}
	
	@Test
	public void when_remove_invalid_element_must_not_change_anything() {
		example();
		tree.remove(100);
		
		assertEquals(new Integer(13), getSizeFromTree());
		
		TreeNode<Integer> root = getRootFromTree();
		assertEquals(new Integer(50), root.getElement());
	}
	
	@Test
	public void when_pre_order_with_tree_with_several_elements_must_show_the_correct_pre_order() {
		example();
		Array<Object> preOrderList = tree.preOrder();
		
		assertEquals(new Integer(50), preOrderList.get(0));
		assertEquals(new Integer(20), preOrderList.get(1));
		assertEquals(new Integer(14), preOrderList.get(2));
		assertEquals(new Integer(33), preOrderList.get(3));
		assertEquals(new Integer(22), preOrderList.get(4));
		assertEquals(new Integer(25), preOrderList.get(5));
		assertEquals(new Integer(41), preOrderList.get(6));
		assertEquals(new Integer(80), preOrderList.get(7));
		assertEquals(new Integer(67), preOrderList.get(8));
		assertEquals(new Integer(52), preOrderList.get(9));
		assertEquals(new Integer(73), preOrderList.get(10));
		assertEquals(new Integer(93), preOrderList.get(11));
		assertEquals(new Integer(83), preOrderList.get(12));
	}
	
	@Test
	public void when_in_order_with_tree_with_several_elements_must_show_the_correct_in_order() {
		example();
		Array<Object> inOrderList = tree.inOrder();
//		[14, 20, 22, 25, 33, 41, 50, 52, 67, 73
		assertEquals(new Integer(14), inOrderList.get(0));
		assertEquals(new Integer(20), inOrderList.get(1));
		assertEquals(new Integer(22), inOrderList.get(2));
		assertEquals(new Integer(25), inOrderList.get(3));
		assertEquals(new Integer(33), inOrderList.get(4));
		assertEquals(new Integer(41), inOrderList.get(5));
		assertEquals(new Integer(50), inOrderList.get(6));
		assertEquals(new Integer(52), inOrderList.get(7));
		assertEquals(new Integer(67), inOrderList.get(8));
		assertEquals(new Integer(73), inOrderList.get(9));
		assertEquals(new Integer(80), inOrderList.get(10));
		assertEquals(new Integer(83), inOrderList.get(11));
		assertEquals(new Integer(93), inOrderList.get(12));
	}
	
	@Test
	public void when_post_order_with_tree_with_several_elements_must_show_the_correct_post_order() {
		example();
		Array<Object> preOrderList = tree.postOrder();
		
		assertEquals(new Integer(14), preOrderList.get(0));
		assertEquals(new Integer(25), preOrderList.get(1));
		assertEquals(new Integer(22), preOrderList.get(2));
		assertEquals(new Integer(41), preOrderList.get(3));
		assertEquals(new Integer(33), preOrderList.get(4));
		assertEquals(new Integer(20), preOrderList.get(5));
		assertEquals(new Integer(52), preOrderList.get(6));
		assertEquals(new Integer(73), preOrderList.get(7));
		assertEquals(new Integer(67), preOrderList.get(8));
		assertEquals(new Integer(83), preOrderList.get(9));
		assertEquals(new Integer(93), preOrderList.get(10));
		assertEquals(new Integer(80), preOrderList.get(11));
		assertEquals(new Integer(50), preOrderList.get(12));
	}
	
	@Test
	public void when_elements_in_range_gets_no_elements() {
		example();
		Array<Integer> elements = tree.elementsIn(30, 33);
		assertTrue(elements.isEmpty());
	}

	@Test
	public void when_elements_in_range_gets_only_root_element() {
		example();
		Array<Integer> elements = tree.elementsIn(49, 52);
		
		assertEquals(1, elements.size());
		assertEquals(new Integer(50), elements.get(0));
	}
	
	@Test
	public void when_elements_in_range_gets_several_elements() {
		example();
		Array<Integer> elements = tree.elementsIn(21, 70);
		
		assertEquals(7, elements.size());
		assertEquals(new Integer(50), elements.get(0));
		assertEquals(new Integer(67), elements.get(1));
		assertEquals(new Integer(52), elements.get(2));
		assertEquals(new Integer(33), elements.get(3));
		assertEquals(new Integer(41), elements.get(4));
		assertEquals(new Integer(22), elements.get(5));
		assertEquals(new Integer(25), elements.get(6));
		
	}
	/** 
	 * 						50				50
	 * 					   /  \				  \
	 * 					  20   ....			   80
	 * 					 /  \                /   \
	 * 					14  33              67    93
	 * 					   /  \            /  \   /
	 * 					  22  41          52  73 83
	 *                      \
	 *                      25
	 */
	private void example() {
		tree.add(80); tree.add(20); tree.add(67); tree.add(33); tree.add(93); tree.add(52);
		tree.add(73); tree.add(83); tree.add(41); tree.add(22); tree.add(25); tree.add(14);
	}
	
	private Integer getSizeFromTree() {
		return (Integer) ReflectionTestUtils.getField(tree, "size");
	}

	@SuppressWarnings("unchecked")
	private TreeNode<Integer> getRootFromTree() {
		return (TreeNode<Integer>) ReflectionTestUtils.getField(tree, "root");
	}
}
