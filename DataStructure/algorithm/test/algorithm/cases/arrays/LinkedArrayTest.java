package algorithm.cases.arrays;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedArrayTest {

	@Test
	public void when_path_to_the_end_() {
		int[] numbers = new int[] {5,2,6,-1,4,3,9,7,1,8};
		assertEquals(2, new LinkedArray(numbers).pathToTheEnnd());
	}
	
	@Test
	public void when_path_to_the_end_1() {
		int[] numbers = new int[] {2,1,9,8,5,6,4,-1,7,3};
		assertEquals(5, new LinkedArray(numbers).pathToTheEnnd());
	}
	
}
