package quicksort;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class QuickSortExecutor {

	static final int SIZE = 100000;

	public static void main(String[] args) throws Exception {
		Integer[] numbers = numbers();
		
		int numberOfCors = ForkJoinPool.getCommonPoolParallelism();
		int numberOfRepetitions = 3;
		
		for(int parallelism = 1; parallelism <= numberOfCors; parallelism++) {
			for(int i = 0; i < numberOfRepetitions; i ++) {
				execute(numbers.clone(), parallelism, i);
			}
		}
	}

	private static void execute(Integer[] numbers, int parallelism, int repetitionId) throws Exception {
		QuickSortTask task = new QuickSortTask(numbers);
		ForkJoinPool forkJoinPool = new ForkJoinPool(parallelism);
		
		long start = System.currentTimeMillis();
		forkJoinPool.submit(task).get();
		long end = System.currentTimeMillis();
		
		long time = end - start;
		System.out.println("NumberOf Cors: " + parallelism + " - Repetition: " + repetitionId + " Time: " + time);
		System.out.println("-------");
	}

	private static Integer[] numbers() {
		Integer[] datas = new Integer[SIZE];
		Random random = new Random();
		for(int i = 0; i < SIZE; i++) {
			datas[i] = random.nextInt(SIZE+1);
		}
		return datas;
	}
	
}
