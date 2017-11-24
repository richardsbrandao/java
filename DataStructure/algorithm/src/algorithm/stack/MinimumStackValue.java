package algorithm.stack;

import java.util.Stack;

public class MinimumStackValue<T> extends Stack<T> {

	@Override
	public void add(int index, T element) {
		super.add(index, element);
	}
	
	@Override
	public synchronized T pop() {
		return super.pop();
	}

	public Integer minimum() {
		return null;
	}

}
