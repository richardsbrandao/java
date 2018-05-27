package algorithm.cases.distancecounter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DistanceCounterTest {

	@Test
	public void case_one() {
		int[] T = new int[10];
		T[0] = 9; T[1] = 1; T[2] = 4;
		T[3] = 9; T[4] = 0; T[5] = 4;
		T[6] = 8; T[7] = 9; T[8] = 0;
		T[9] = 1;
		
		int[] solution = new DistanceCounter().solution(T);
		
		assertEquals(9, solution.length);
		assertEquals(1, solution[0]);
		assertEquals(3, solution[1]);
		assertEquals(2, solution[2]);
		assertEquals(3, solution[3]);
		assertEquals(0, solution[4]);
		assertEquals(0, solution[5]);
		assertEquals(0, solution[6]);
		assertEquals(0, solution[7]);
		assertEquals(0, solution[8]);
	}
	
}
