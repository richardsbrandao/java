package algorithm.cases.island;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IslandTest {

	@Test
	public void find_for_island_case_1() {
		int[][] territory = territoryOne();
		int islandCounter = new IslandCounter(territory).islandCounter();
		assertEquals(2, islandCounter);
	}

	/**
	 *      	 0   1   2   3
     *		____________________
	 *		0 -  1 | 0 | 0 | 0 |
	 *		1 -  0 | 0 | 1 | 1 |   
	 *		2 -  0 | 0 | 0 | 0 |   
	 *		3 -  0 | 1 | 0 | 1 |   
	 *		4 -  0 | 1 | 0 | 0 |   
	 *		5 -  1 | 0 | 0 | 0 |   
	 */
	private int[][] territoryOne() {
		int[][] territory = new int[6][4];
		territory[0] = new int[] {1,0,0,0};
		territory[1] = new int[] {0,0,1,1};
		territory[2] = new int[] {0,0,0,0};
		territory[3] = new int[] {0,1,0,1};
		territory[4] = new int[] {0,1,0,0};
		territory[5] = new int[] {1,0,0,0};
		return territory;
	}
	
}
