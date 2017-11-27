package algorithm.binarytree;

import java.util.LinkedList;
import java.util.concurrent.Callable;

public class BinaryTree<T> {

	private Node<T> root;
	private LinkedList<T> valuesProcessed;

	public BinaryTree(Node<T> root) {
		this.root = root;
		this.valuesProcessed = new LinkedList<T>();
	}
	
	public void breadthProcess() {
		
	}
	
	public void preOrderProcess() {
		
	}
	
	public void postOrderProcess() {
		
	}
	
	public void inOrderProcess() {
		
	}
	
	private void print(Node node) {
		System.out.println(node);
	}
}
