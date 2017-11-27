package algorithm.binarytree;

import java.util.LinkedList;
import java.util.concurrent.Callable;

import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTest {
	private BinaryTree tree;
	
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
	}
}
