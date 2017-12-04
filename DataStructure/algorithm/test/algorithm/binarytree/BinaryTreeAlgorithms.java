package algorithm.binarytree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeAlgorithms {

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
	public void max_value() {
		assertEquals(Integer.valueOf(66), tree.max());
	}
	
	@Test
	public void depth() {
		assertEquals(Integer.valueOf(0), new BinaryTree<Integer>(null).depth());
		assertEquals(Integer.valueOf(3), tree.depth());	
	}
	
}
