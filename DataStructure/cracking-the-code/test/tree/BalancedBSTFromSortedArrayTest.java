package tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tree.impl.BST;

@DisplayName("BalancedBSTFromSortedArray")
public class BalancedBSTFromSortedArrayTest {
	BalancedBSTFromSortedArray algorithm = new BalancedBSTFromSortedArray();

	@Test
	@DisplayName("when empty array must return it")
	public void case_one() {
		BST bst = algorithm.execute(new int[] {});
		
		assertNull(bst.getRoot());
	}

	@Test
	@DisplayName("when single element array must return it")
	public void case_two() {
		BST bst = algorithm.execute(new int[] {1});
		
		assertEquals(new Integer(1), bst.getRoot().getValue());
	}

	@Test
	@DisplayName("when array with 5 elements must return balanced array")
	public void case_three() {
		BST bst = algorithm.execute(new int[] {1,2,3,4,5});
		
		assertEquals(new Integer(3), bst.getRoot().getValue());

		assertEquals(new Integer(1), bst.getRoot().getLeft().getValue());
		assertEquals(new Integer(2), bst.getRoot().getLeft().getRight().getValue());
		assertNull(bst.getRoot().getLeft().getLeft());

		assertEquals(new Integer(4), bst.getRoot().getRight().getValue());
		assertEquals(new Integer(5), bst.getRoot().getRight().getRight().getValue());
		assertNull(bst.getRoot().getRight().getLeft());

	}
	
	@Test
	@DisplayName("when array with 7 elements must return a perfect balanced array")
	public void case_four() {
		BST bst = algorithm.execute(new int[] {1,2,3,4,5,6,7});
		
		assertEquals(new Integer(4), bst.getRoot().getValue());

		assertEquals(new Integer(2), bst.getRoot().getLeft().getValue());
		assertEquals(new Integer(1), bst.getRoot().getLeft().getLeft().getValue());
		assertEquals(new Integer(3), bst.getRoot().getLeft().getRight().getValue());

		assertEquals(new Integer(6), bst.getRoot().getRight().getValue());
		assertEquals(new Integer(5), bst.getRoot().getRight().getLeft().getValue());
		assertEquals(new Integer(7), bst.getRoot().getRight().getRight().getValue());

	}
	
	@DisplayName("when array with 10 elements must return balanced array")
	@Test
	public void case_five() {
		BST bst = algorithm.execute(new int[] {1,2,3,4,5,6,7,8,9,10});
		
		assertEquals(new Integer(5), bst.getRoot().getValue());
		
		assertEquals(new Integer(2), bst.getRoot().getLeft().getValue());
		assertEquals(new Integer(1), bst.getRoot().getLeft().getLeft().getValue());
		assertEquals(new Integer(3), bst.getRoot().getLeft().getRight().getValue());
		assertEquals(new Integer(4), bst.getRoot().getLeft().getRight().getRight().getValue());

		assertEquals(new Integer(8), bst.getRoot().getRight().getValue());
		assertEquals(new Integer(6), bst.getRoot().getRight().getLeft().getValue());
		assertEquals(new Integer(7), bst.getRoot().getRight().getLeft().getRight().getValue());
		assertEquals(new Integer(9), bst.getRoot().getRight().getRight().getValue());
		assertEquals(new Integer(10), bst.getRoot().getRight().getRight().getRight().getValue());
	}
}
