package algorithm.binarytree;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeOperationsTest {

	private BinaryTree<Integer> tree;
	
	@Before
	public void setUp() {
		Node<Integer> root = new Node<Integer>(20,
					new Node<Integer>(7),
					new Node<Integer>(43,
						new Node<Integer>(26,
								new Node<Integer>(22),
								new Node<Integer>(30)
						),
						new Node<Integer>(48,
								null,
								new Node<Integer>(66)
						)
					)
				);
		this.tree = new BinaryTree<Integer>(root);
	}
	
	@Test
	public void test_insert() {
		tree.insert(47);
		assertEquals(Integer.valueOf(47), tree.getRoot().getRight().getRight().getLeft().getData());
	}
	
	@Test
	public void test_lookup_existing_element() {
		assertNotNull(tree.lookup(48));
	}
	
	@Test
	public void test_lookup_invalid_element() {
		assertNull(tree.lookup(23));
	}
	
	@Test
	public void test_mirror_binnary_tree() {
		tree.mirror();
		assertEquals(Integer.valueOf(20), tree.getRoot().getData());

		assertEquals(Integer.valueOf(7), tree.getRoot().getRight().getData());
		
		assertEquals(Integer.valueOf(66), tree.getRoot().getLeft().getLeft().getLeft().getData());
		assertEquals(Integer.valueOf(48), tree.getRoot().getLeft().getLeft().getData());
		assertEquals(Integer.valueOf(43), tree.getRoot().getLeft().getData());
	}
	
	@Test
	public void test_find_elements_within_range() {
		tree.insert(2);
		List<Integer> collectedElements = tree.elementsInRange(6, 28);
		
		assertEquals(Integer.valueOf(20), collectedElements.get(0));
		assertEquals(Integer.valueOf(7), collectedElements.get(1));
		assertEquals(Integer.valueOf(26), collectedElements.get(2));
		assertEquals(Integer.valueOf(22), collectedElements.get(3));
	}
	
	@Test
	public void test_valid_binnary_tree() {
		assertTrue(tree.isValid());
	}
	
	@Test
	public void test_invalid_binnary_tree() {
		tree.getRoot().getLeft().setRight(new Node<Integer>(100));
		assertFalse(tree.isValid());
	}
}
