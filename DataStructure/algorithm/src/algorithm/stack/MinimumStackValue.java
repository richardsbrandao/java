package algorithm.stack;

import java.util.Stack;


@SuppressWarnings("serial")
public class MinimumStackValue<T extends Comparable<T>> extends Stack<T> {

	private Stack<T> minimumStack = new Stack<T>();
	
	@Override
	/*
	 * Complexity = O(1)
	 * Space = O(n)
	 */
	public T push(T item) {
		super.push(item);
		if( minimum() == null || item.compareTo(minimum()) < 0 ) {
			minimumStack.push(item);
		} else {
			minimumStack.push(minimum());
		}
		return item;
	}
	
	/*
	 * Complexity = O(1)
	 * Space = O(n)
	 */
	@Override
	public synchronized T pop() {
		T item = super.pop();
		minimumStack.pop();
		return item;
	}

	public T minimum() {
		return ! minimumStack.isEmpty() ? minimumStack.peek() : null;
	}

}
