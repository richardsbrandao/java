package algorithm.cases.fibo;

public class Fibonacci {

	/**
	 * COMPLEXITY: O(2^n)
	 * SPACE: O(1)
	 */
	public long recursive(int n) {
		if(n < 2) {
			return n; // 1 or 0
		}
		return recursive(n-1) + recursive(n-2);
	}

	/**
	 * COMPLEXITY: O(n)
	 * SPACE: O(n)
	 */
	public long withVector(int n) {
		int sizeArray = n > 1 ? n : 2;
		Integer[] fiboList = new Integer[++sizeArray];
		fiboList[0] = 0;
		fiboList[1] = 1;
		return recursionWithVector(fiboList, n, 2);
	}
	
	private long recursionWithVector(Integer[] fiboList, int total, int current) {
		if( total < current ) {
			return fiboList[total];
		}
		fiboList[current] = fiboList[current-1] + fiboList[current-2];
		return recursionWithVector(fiboList, total, ++current);
	}

	/**
	 * COMPLEXITY: O(n)
	 * SPACE: O(1)
	 */
	public long withLooping(int n) {
		long last = 0;
		long actual = 0;
		for(int i = 1; i <= n; i++) {
			if( i == 1 ) {
				actual = 1;
			} else {
				actual += last;
				last = actual - last;
			}
		}
		return actual;
	}
	
}
