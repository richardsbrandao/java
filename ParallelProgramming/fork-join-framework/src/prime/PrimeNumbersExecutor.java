package prime;

import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class PrimeNumbersExecutor {

	public static void main(String[] args) {
		ForkJoinPool pool = ForkJoinPool.commonPool();
		PrimeNumbersAction action = new PrimeNumbersAction(1000);
		Set<Integer> invoke = pool.invoke(action);
		System.out.println(invoke);
	}
	
}
