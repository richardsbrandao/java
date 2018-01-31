package stringreader;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class MergeArrayExecutor {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String[] texts = generateString();
		int numberOfCors = ForkJoinPool.getCommonPoolParallelism();
		int numberOfRepetitions = 3;

		for(int parallelism = 1; parallelism <= numberOfCors; parallelism++) {
			for(int i = 0; i < numberOfRepetitions; i ++) {
				execute(texts, parallelism, i);
			}
			System.out.println("**************************");
		}
	}

	private static void execute(String[] texts, int parallelism, int repetitionId) throws InterruptedException, ExecutionException {
		MergeArrayTask task = new MergeArrayTask(texts);
		ForkJoinPool forkJoinPool = new ForkJoinPool(6);
		
		long start = System.currentTimeMillis();
		forkJoinPool.submit(task).get();
		long end = System.currentTimeMillis();
		
		long time = end - start;
		System.out.println("NumberOf Cors: " + parallelism + " - Repetition: " + repetitionId + " Time: " + time);
		System.out.println("-------");
	}

	private static String[] generateString() {
		int sizeTask = 1000000;
		String[] texts = new String[sizeTask];
		for(int i = 0; i < sizeTask; i++) {
			texts[i] = "|" + String.valueOf(i);
		}
		return texts ;
	}
	
}
