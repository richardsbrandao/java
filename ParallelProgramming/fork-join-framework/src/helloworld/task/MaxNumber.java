package helloworld.task;

import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class MaxNumber extends RecursiveTask<Integer> {

	private static final int THREADSHOLD = 2;
	private int[] numbers;
	private int start;
	private int end;

	public MaxNumber(int[] numbers) {
		this.numbers = numbers;
		this.start = 0;
		this.end = numbers.length;
	}
	
	public MaxNumber(int[] numbers, int start, int end) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int range = end - start;
		if(range < THREADSHOLD) {
			return Math.max(numbers[start], numbers[end-1]);
		} else {
			int split = range / 2;
			MaxNumber left = new MaxNumber(numbers, start, start+split);
			left.fork();
			MaxNumber right = new MaxNumber(numbers, start+split, end);
			return Math.max(right.compute(), left.join());
		}
	}

}
