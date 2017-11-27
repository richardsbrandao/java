package algorithm.sort;

import static org.junit.Assert.*;

import org.junit.Test;

public class SelectionSortTest {
	SortAlgorithm<Integer> algorithm = new SelectionSort<Integer>();

	@Test
	public void test_case_best_case() {
		Integer[] items = new Integer[]{1,6,8,10,15,100,403};
		Sorter<Integer> sorter = new Sorter<Integer>(algorithm, items);
		Integer[] sortItems = sorter.sort();
		
		assertEquals(Integer.valueOf(1), sortItems[0]);
		assertEquals(Integer.valueOf(6), sortItems[1]);
		assertEquals(Integer.valueOf(8), sortItems[2]);
		assertEquals(Integer.valueOf(10), sortItems[3]);
		assertEquals(Integer.valueOf(15), sortItems[4]);
		assertEquals(Integer.valueOf(100), sortItems[5]);
		assertEquals(Integer.valueOf(403), sortItems[6]);
	}
	
	@Test
	public void test_case_worst_case() {
		Integer[] items = new Integer[]{100,53,23,21,20,5,2};
		Sorter<Integer> sorter = new Sorter<Integer>(algorithm, items);
		Integer[] sortItems = sorter.sort();
		
		assertEquals(Integer.valueOf(2), sortItems[0]);
		assertEquals(Integer.valueOf(5), sortItems[1]);
		assertEquals(Integer.valueOf(20), sortItems[2]);
		assertEquals(Integer.valueOf(21), sortItems[3]);
		assertEquals(Integer.valueOf(23), sortItems[4]);
		assertEquals(Integer.valueOf(53), sortItems[5]);
		assertEquals(Integer.valueOf(100), sortItems[6]);
	}
	
	@Test
	public void test_case_regular_case() {
		Integer[] items = new Integer[]{9,100,1,20,50,23,53,30,10,44,94};
		Sorter<Integer> sorter = new Sorter<Integer>(algorithm, items);
		Integer[] sortItems = sorter.sort();
		
		assertEquals(Integer.valueOf(1), sortItems[0]);
		assertEquals(Integer.valueOf(9), sortItems[1]);
		assertEquals(Integer.valueOf(10), sortItems[2]);
		assertEquals(Integer.valueOf(20), sortItems[3]);
		assertEquals(Integer.valueOf(23), sortItems[4]);
		assertEquals(Integer.valueOf(30), sortItems[5]);
		assertEquals(Integer.valueOf(44), sortItems[6]);
		assertEquals(Integer.valueOf(50), sortItems[7]);
		assertEquals(Integer.valueOf(53), sortItems[8]);
		assertEquals(Integer.valueOf(94), sortItems[9]);
		assertEquals(Integer.valueOf(100), sortItems[10]);
	}
}
