package linkedlist;

import linkedlist.helper.LinkedNode;

/**
 * Implement an algorithm to find the kth to last element of singly linked list 
 */
public class KthElement {

	public <T extends Object> T get(LinkedNode<T> linkedList, Integer kth) {
		int size = size(linkedList, 0);
		if(size < kth) {
			return null;
		}
		return find(linkedList, size - kth, 0);
	}

	private <T extends Object> T find(LinkedNode<T> linkedList, int kthFromFirst, int index) {
		if(index == kthFromFirst) {
			return linkedList.getValue();
		}
		return find(linkedList.getNext(), kthFromFirst, index+1);
	}

	private <T extends Object> int size(LinkedNode<T> linkedList, int counter) {
		if(linkedList == null) {
			return counter;
		}
		return size(linkedList.getNext(), counter+1);
	}
	
}
