package prime;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class PrimeNumbersAction extends RecursiveTask<Set<Integer>> {

	private Integer target;
	private int start;
	private int end;
	private final static int THREADSHOLD = 100;
	
	public PrimeNumbersAction(Integer target) {
		this(target, 0, target);
	}
	
	public PrimeNumbersAction(Integer target, int start, int end) {
		this.target = target;
		this.start = start;
		this.end = end;
	}
	
	@Override
	protected Set<Integer> compute() {
		System.out.println(end + " - " + start + " = " + Thread.currentThread().getName());
		if(end-start <= THREADSHOLD) {
			Set<Integer> primes = new LinkedHashSet<Integer>();
			for(int i = start; i < end; i++) {
				if(isPrime(i)) {
					primes.add(i);
				}
			}
			return primes;
		} else {
			int halfAmount = ((end - start) / 2) + start;
			PrimeNumbersAction left = new PrimeNumbersAction(target, start, halfAmount);
			left.fork();
			PrimeNumbersAction right = new PrimeNumbersAction(target, halfAmount+1, end);
			Set<Integer> result = right.compute();
			result.addAll(left.join());
			return result;
		}
	}

	private boolean isPrime(int number) {
		if(number == 2 || number == 3) {
			return true;
		}
		if(number % 2 == 0 || number % 3 == 0) {
			return false;
		}
		for(int i = 3; i <= Math.sqrt(number); i += 2) {
			if(number % i == 0) {
				return false;
			}
		}
		return true;
	}

}
