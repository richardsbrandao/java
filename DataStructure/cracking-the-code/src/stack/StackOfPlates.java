package stack;

import java.util.Stack;

public class StackOfPlates {
	private static final Integer CAPACITY = 5;
	private Stack<Stack<String>> set;
	private int size;
	
	public StackOfPlates() {
		this.set = new Stack<Stack<String>>();
		this.size = 0;
	}
	
	public void push(String value) {
		if(this.size % CAPACITY == 0) {
			this.set.push(new Stack<>());
		}
		this.set.peek().push(value);
		this.size++;
	}
	
	public String pop() {
		String item = set.peek().pop();
		this.size--;
		if(this.size % CAPACITY == 0) {
			this.set.pop();
		}
		return item;
	}
	
}
