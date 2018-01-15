package datastructures.trees;

import org.junit.Test;

public class RedBlackTreeTest {

	@Test
	public void test_1() {
		RedBlackTree<Integer> redBlackTree = new RedBlackTree<Integer>(50);
		redBlackTree.insert(20);
		redBlackTree.insert(40);
		redBlackTree.insert(70);
		redBlackTree.insert(90);
		System.out.println(redBlackTree);
	}
	
}
