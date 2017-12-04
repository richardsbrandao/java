package algorithm.binarytree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTraversalsTest {
	private BinaryTree<String> tree;
	
	@Before
	public void setUp() {
		Node<String> root = new Node<String>("A", 
				new Node<String>("B"), 
				new Node<String>("C",
						new Node<String>("D", 
								new Node<String>("F"),
								new Node<String>("H")
						),
						new Node<String>("E",
								null,
								new Node<String>("G")
						)
				)
		);
		
		tree = new BinaryTree<String>(root);
	}
	
	@Test
	public void test_breadth_order() {
		tree.breadthProcess();
		assertEquals("A", tree.getValuesProcessed().get(0));
		assertEquals("B", tree.getValuesProcessed().get(1));
		assertEquals("C", tree.getValuesProcessed().get(2));
		assertEquals("D", tree.getValuesProcessed().get(3));
		assertEquals("E", tree.getValuesProcessed().get(4));
		assertEquals("F", tree.getValuesProcessed().get(5));
		assertEquals("H", tree.getValuesProcessed().get(6));
		assertEquals("G", tree.getValuesProcessed().get(7));
	}
	
	@Test
	public void test_deapth_pre_order_traversal() {
		tree.preOrderProcess();
		assertEquals("A", tree.getValuesProcessed().get(0));
		assertEquals("B", tree.getValuesProcessed().get(1));
		assertEquals("C", tree.getValuesProcessed().get(2));
		assertEquals("D", tree.getValuesProcessed().get(3));
		assertEquals("F", tree.getValuesProcessed().get(4));
		assertEquals("H", tree.getValuesProcessed().get(5));
		assertEquals("E", tree.getValuesProcessed().get(6));
		assertEquals("G", tree.getValuesProcessed().get(7));
	}
	
	@Test
	public void test_deapth_in_order_traversal() {
		tree.inOrderProcess();
		assertEquals("B", tree.getValuesProcessed().get(0));
		assertEquals("A", tree.getValuesProcessed().get(1));
		assertEquals("F", tree.getValuesProcessed().get(2));
		assertEquals("D", tree.getValuesProcessed().get(3));
		assertEquals("H", tree.getValuesProcessed().get(4));
		assertEquals("C", tree.getValuesProcessed().get(5));
		assertEquals("E", tree.getValuesProcessed().get(6));
		assertEquals("G", tree.getValuesProcessed().get(7));
	}
	
	@Test
	public void test_deapth_post_order_traversal() {
		tree.postOrderProcess();
		assertEquals("B", tree.getValuesProcessed().get(0));
		assertEquals("F", tree.getValuesProcessed().get(1));
		assertEquals("H", tree.getValuesProcessed().get(2));
		assertEquals("D", tree.getValuesProcessed().get(3));
		assertEquals("G", tree.getValuesProcessed().get(4));
		assertEquals("E", tree.getValuesProcessed().get(5));
		assertEquals("C", tree.getValuesProcessed().get(6));
		assertEquals("A", tree.getValuesProcessed().get(7));
	}
}
