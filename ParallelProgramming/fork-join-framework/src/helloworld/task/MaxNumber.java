package helloworld.task;

import java.util.concurrent.RecursiveTask;

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
		if(range <= 1) {
			return Math.max(numbers[start], numbers[end-1]);
		} else {
			int split = range / 2;
			MaxNumber left = new MaxNumber(numbers, start, start+split);
			left.fork();
			MaxNumber right = new MaxNumber(numbers, start+split, end);
			right.fork();
			return Math.max(left.join(), right.join());
		}
//		int length =  end - start;
//		int max = 0;
//		if(length < THREADSHOLD) {
//			for(int x = start; x < end; x++) {
//				max = numbers[x];
//			}
//			return max;
//		} else {
//			int split = length / 2;
//			MaxNumber left = new MaxNumber(numbers, start, start+split);
//			left.fork();
//			MaxNumber right = new MaxNumber(numbers, start+split, end);
//			return Math.max(right.compute(), left.join());
//		}
	}

}
