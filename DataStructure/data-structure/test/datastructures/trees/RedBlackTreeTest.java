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
		tree.insert(20); tree.insert(80); tree.insert(10);
		
		RedBlackNode<Integer> root = getRootFromTree();
		
		assertRedBlackNode(new Integer(50), ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(20), ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(80), ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(10), ColorRedBlackNode.RED, root.getLeft().getLeft());
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_left_left_position_must_balance_it() {
		tree.insert(20); tree.insert(80); tree.insert(10); tree.insert(5);
		
		RedBlackNode<Integer> root = getRootFromTree();
		
		assertRedBlackNode(new Integer(50), ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(10), ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(80), ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(5), ColorRedBlackNode.RED, root.getLeft().getLeft());
		assertRedBlackNode(new Integer(20), ColorRedBlackNode.RED, root.getLeft().getRight());
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_left_right_position_must_balance_it() {
		tree.insert(20); tree.insert(80); tree.insert(10); tree.insert(15);
		
		RedBlackNode<Integer> root = getRootFromTree();
		
		assertRedBlackNode(new Integer(50), ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(15), ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(80), ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(10), ColorRedBlackNode.RED, root.getLeft().getLeft());
		assertRedBlackNode(new Integer(20), ColorRedBlackNode.RED, root.getLeft().getRight());
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_right_left_position_must_balance_it() {
		tree.insert(20); tree.insert(80); tree.insert(90); tree.insert(85);
		
		RedBlackNode<Integer> root = getRootFromTree();
		
		assertRedBlackNode(new Integer(50), ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(20), ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(85), ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(80), ColorRedBlackNode.RED, root.getRight().getLeft());
		assertRedBlackNode(new Integer(90), ColorRedBlackNode.RED, root.getRight().getRight());
	}
	
	@Test
	public void when_insert_with_unbalanced_elements_in_right_right_position_must_balance_it() {
		tree.insert(20); tree.insert(80); tree.insert(90); tree.insert(100);
		
		RedBlackNode<Integer> root = getRootFromTree();
		
		assertRedBlackNode(new Integer(50), ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(20), ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(90), ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(80), ColorRedBlackNode.RED, root.getRight().getLeft());
		assertRedBlackNode(new Integer(100), ColorRedBlackNode.RED, root.getRight().getRight());
	}
	
	@Test
	public void when_insert_with_random_elements_must_have_not_violations_1() {
		tree = new RedBlackTree<Integer>(35);
		tree.insert(25); tree.insert(70); tree.insert(10); tree.insert(100); 
		tree.insert(20); tree.insert(5); tree.insert(85); tree.insert(55); tree.insert(40);

		RedBlackNode<Integer> root = getRootFromTree();
		
		assertRedBlackNode(new Integer(35), ColorRedBlackNode.BLACK, root);
		assertRedBlackNode(new Integer(20), ColorRedBlackNode.RED, root.getLeft());
		assertRedBlackNode(new Integer(10), ColorRedBlackNode.BLACK, root.getLeft().getLeft());
		assertRedBlackNode(new Integer(25), ColorRedBlackNode.BLACK, root.getLeft().getRight());
		assertRedBlackNode(new Integer(5), ColorRedBlackNode.RED, root.getLeft().getLeft().getLeft());
		
		assertRedBlackNode(new Integer(85), ColorRedBlackNode.RED, root.getRight());
		assertRedBlackNode(new Integer(55), ColorRedBlackNode.BLACK, root.getRight().getLeft());
		assertRedBlackNode(new Integer(40), ColorRedBlackNode.RED, root.getRight().getLeft().getLeft());
		assertRedBlackNode(new Integer(70), ColorRedBlackNode.RED, root.getRight().getLeft().getRight());
		assertRedBlackNode(new Integer(100), ColorRedBlackNode.BLACK, root.getRight().getRight());
		
	}
	
	@Test
	public void when_insert_with_random_elements_must_have_not_violations_2() {
		tree = new RedBlackTree<Integer>(93); 
		tree.insert(3); 
		tree.insert(7);  tree.insert(39); 
		tree.insert(4);  tree.insert(95);
		tree.insert(33); tree.insert(18);
		tree.insert(70); tree.insert(10);
		tree.insert(34); tree.insert(86);
		tree.insert(24); tree.insert(51);
		
		RedBlackNode<Integer> root = getRootFromTree();
		
		assertRedBlackNode(new Integer(33), ColorRedBlackNode.BLACK, root);
		//left
		assertRedBlackNode(new Integer(7), ColorRedBlackNode.BLACK, root.getLeft());
		assertRedBlackNode(new Integer(3), ColorRedBlackNode.BLACK, root.getLeft().getLeft());
		assertRedBlackNode(new Integer(4), ColorRedBlackNode.RED, root.getLeft().getLeft().getRight());
		assertRedBlackNode(new Integer(18), ColorRedBlackNode.BLACK, root.getLeft().getRight());
		assertRedBlackNode(new Integer(10), ColorRedBlackNode.RED, root.getLeft().getRight().getLeft());
		assertRedBlackNode(new Integer(24), ColorRedBlackNode.RED, root.getLeft().getRight().getRight());
		//right
		assertRedBlackNode(new Integer(93), ColorRedBlackNode.BLACK, root.getRight());
		assertRedBlackNode(new Integer(39), ColorRedBlackNode.RED, root.getRight().getLeft());
		assertRedBlackNode(new Integer(34), ColorRedBlackNode.BLACK, root.getRight().getLeft().getLeft());
		assertRedBlackNode(new Integer(70), ColorRedBlackNode.BLACK, root.getRight().getLeft().getRight());
		assertRedBlackNode(new Integer(51), ColorRedBlackNode.RED, root.getRight().getLeft().getRight().getLeft());
		assertRedBlackNode(new Integer(86), ColorRedBlackNode.RED, root.getRight().getLeft().getRight().getRight());
		assertRedBlackNode(new Integer(95), ColorRedBlackNode.BLACK, root.getRight().getRight());
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
