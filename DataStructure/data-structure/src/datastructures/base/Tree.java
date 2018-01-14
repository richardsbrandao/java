package datastructures.base;

import datastructures.base.tree.TreeNode;
import datastructures.lists.Array;

public interface Tree<T extends Comparable<T>> {
	
	public void add(T value);
	public void remove(T value);
	public TreeNode<T> get(T value);
	public boolean contains(T value);
	
	public int size();
	public T max();
	public T min();
	public int height();
	public int deapth(T value);
	
	public Array<T> elementsIn(T min, T max);
	public Array<Object> preOrder();
	public Array<Object> inOrder();
	public Array<Object> postOrder(); 
}
