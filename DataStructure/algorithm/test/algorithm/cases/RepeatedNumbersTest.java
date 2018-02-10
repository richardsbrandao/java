package algorithm.cases;

import static org.junit.Assert.*;

import org.junit.Test;

public class RepeatedNumbersTest {
	@Test
	public void when_repeated_with_array_only_one_repetition_must_find_not_repeated_saving_hash_map() {
		Integer[] numbers = new Integer[]{6,8,8,1,5,1,6,9,7,9,5};
		assertEquals(new Integer(7), new RepeatedNumbers(numbers).solution1());
	}
	
	@Test
	public void when_repeated_with_array_only_one_repetition_must_find_sorting_first() {
		Integer[] numbers = new Integer[]{6,8,8,1,5,1,6,9,7,9,5};
		assertEquals(new Integer(7), new RepeatedNumbers(numbers).solution2());
	}
	
	@Test
	public void when_repeated_multiple_with_array_only_one_repetition_must_find_not_repeated_saving_hash_map() {
		Integer[] numbers = new Integer[]{6,8,6,6,8,1,8,5,1,6,9,7,5,7};
		assertEquals(new Integer(9), new RepeatedNumbers(numbers).solution3());
	}
	
}
