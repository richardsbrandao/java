package tree;

import tree.impl.BST;
import tree.impl.Node;

public class BalancedBSTFromSortedArray {
	
	public BST execute(int[] sortedArray) {
		return new BST( balanceBST(sortedArray, 0, sortedArray.length-1) );
	}

	private Node<Integer> balanceBST(int[] sortedArray, int start, int end) {
		if( end < start ) {
			return null;
		}
		
		int middle = (end + start) / 2;

		Node<Integer> head = new Node<Integer>( sortedArray[middle] );

		head.setLeft( balanceBST(sortedArray, start, middle-1) );
		head.setRight( balanceBST(sortedArray, middle+1, end) );
		
		return head;
	}
	
}
