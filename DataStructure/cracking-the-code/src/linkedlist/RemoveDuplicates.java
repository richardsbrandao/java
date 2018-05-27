package linkedlist;

import java.util.HashSet;
import java.util.Set;

import linkedlist.helper.LinkedNode;

/**
 * Write a code to remove duplicates from an unsorted linked list
 */
public class RemoveDuplicates {

	public LinkedNode<String> removeDuplicates(LinkedNode<String> list) {
		LinkedNode<String> newList = new LinkedNode<String>(null, null);
		removeDuplicates(list, newList, new HashSet<String>());
		return newList; 
	}

	private LinkedNode<String> removeDuplicates(LinkedNode<String> head, LinkedNode<String> newList, Set<String> repository) {
		if(head == null) {
			return newList;
		}
		
		if(!repository.contains(head.getValue())) {
			repository.add(head.getValue());
			if(newList.getValue() == null) {
				newList.setValue(head.getValue());
				return removeDuplicates(head.getNext(), newList, repository);
			} else {
				newList.setNext(new LinkedNode<>(head.getValue(), null));
				return removeDuplicates(head.getNext(), newList.getNext(), repository);
			}
		}
		
		return removeDuplicates(head.getNext(), newList, repository);
	}
	
}
