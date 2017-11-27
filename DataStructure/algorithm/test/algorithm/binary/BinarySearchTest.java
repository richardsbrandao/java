package algorithm.binary;

import static org.junit.Assert.*;

import org.junit.Test;

public class BinarySearchTest {
	@Test
	public void test_case_best_case() {
		Integer[] items = new Integer[]{2,13,42,45,70,100,123};
		assertEquals(new Integer(3), new BinarySearch<Integer>(items).index(45));
	}
	
	@Test
	public void test_case_worst_case() {
		Integer[] items = new Integer[]{2,13,42,45,70,100,123};
		assertEquals(new Integer(6), new BinarySearch<Integer>(items).index(123));
	}
	
	@Test
	public void test_case_regular_case() {
		Integer[] items = new Integer[]{2,13,42,45,70,100,123};
		assertEquals(new Integer(4), new BinarySearch<Integer>(items).index(70));
	}
	
	@Test
	public void test_element_not_found() {
		Integer[] items = new Integer[]{2,13,42,45,70,100,123};
		assertNull(new BinarySearch<Integer>(items).index(999));
	}
}
