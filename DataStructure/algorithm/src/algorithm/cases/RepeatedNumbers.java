package algorithm.cases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class RepeatedNumbers {

	private Integer[] numbers;

	public RepeatedNumbers(Integer[] numbers) {
		this.numbers = numbers;
	}

	/**
	 * RUNTIME: O(n)
	 * SPACE: 	O(1) 
	 */
	public Integer solution1() {
		Set<Integer> set = new HashSet<Integer>();
		for(int number : numbers) {
			if(set.contains(number))
				set.remove(number);
			else
				set.add(number);
		}
		return (Integer) set.toArray()[0];
	}
	
	/**
	 * RUNTIME: O(n*(n/2) log n)
	 * SPACE: 	O(1) 
	 */
	public Integer solution2() {
		Arrays.sort(numbers);
		
		for(int i = 1; i < numbers.length; i += 2) {
			Integer previus = numbers[i-1];
			Integer current = numbers[i];
			
			if(previus != current) {
				return previus;
			}
		}
		return null;
	}

	/**
	 * RUNTIME: O(2n)
	 * SPACE: 	O(n) 
	 */
	public Integer solution3() {
		Map<Integer, Integer> numberAndQuantity = new HashMap<>();
		for(Integer number : numbers) {
			Integer newQuantity = numberAndQuantity.getOrDefault(number, 0) + 1;
			numberAndQuantity.put(number, newQuantity);
		}
		for( Entry<Integer, Integer> entry: numberAndQuantity.entrySet() ) {
			if( entry.getValue() == 1 ) {
				return entry.getKey();
			}
		}
		return null;
	}
}
