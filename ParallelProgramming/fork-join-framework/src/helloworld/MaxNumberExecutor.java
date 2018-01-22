package helloworld;

import java.util.concurrent.ForkJoinPool;

import helloworld.task.MaxNumber;

public class MaxNumberExecutor {
	public static void main(String[] args) {
		System.out.println("Parallelism " + ForkJoinPool.getCommonPoolParallelism());
		ForkJoinPool pool = ForkJoinPool.commonPool();
		MaxNumber action = new MaxNumber(example());
		Integer invoke = pool.invoke(action);
		System.out.println(invoke);
	}

	private static int[] example() {
		return new int[]{5,1,10,12,54,12,33,44,21,34,55,89,11,2,57,88,48,101,58};
	}
}
