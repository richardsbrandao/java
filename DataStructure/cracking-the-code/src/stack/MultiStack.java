package stack;

import java.security.InvalidParameterException;
import java.util.EmptyStackException;

public class MultiStack {

	private static final Integer CAPACITY = 5;
	private static final Integer STACK_NUMBER = 3;
	private String[] repository;
	private int[] size;

	public MultiStack() {
		this.repository = new String[CAPACITY * STACK_NUMBER];
		this.size = new int[STACK_NUMBER];
	}
	
	public void push(int stack, String value) {
		validatePush(stack);
		int lastIndexOfStack = lastIndexOfStack(stack);
		
		this.repository[lastIndexOfStack] = value;
		this.size[stack]++;
	}
	
	private void validatePush(int stack) {
		if(stack >= STACK_NUMBER) {
			throw new InvalidParameterException();
		}
		
		if(this.size[stack] == CAPACITY) {
			throw new StackOverflowError();
		}
	}

	private int lastIndexOfStack(int stack) {
		return CAPACITY * stack + this.size[stack];
	}

	public String pop(int stack) {
		validatePopOrPeek(stack);
		int lastIndexOfStack = lastIndexOfStack(stack) - 1;
		String item = this.repository[lastIndexOfStack];
		this.repository[lastIndexOfStack] = null;
		this.size[stack]--;
		return item;
	}
	
	private void validatePopOrPeek(int stack) {
		if(this.size[stack] == 0) {
			throw new EmptyStackException();
		}
	}

	public String peek(int stack) {
		validatePopOrPeek(stack);
		int lastIndexOfStack = lastIndexOfStack(stack) - 1;
		return this.repository[lastIndexOfStack];
	}
	
	public int size(int stack) {
		return Integer.MIN_VALUE;
	}
	
}
