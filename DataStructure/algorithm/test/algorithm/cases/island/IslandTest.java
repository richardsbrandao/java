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
	
	@Test
	public void find_for_island_case_2() {
		int[][] territory = territoryTwo();
		int islanCounter = new IslandCounter(territory).islandCounter();
		assertEquals(5, islanCounter);
	}

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
	
	private int[][] territoryTwo() {
		int[][] territory = new int[6][6];
		territory[0] = new int[] {0,0,0,0,0,1};
		territory[1] = new int[] {1,1,1,0,1,0};
		territory[2] = new int[] {0,0,0,0,0,0};
		territory[3] = new int[] {1,0,1,0,0,1};
		territory[4] = new int[] {0,0,0,0,1,1};
		territory[5] = new int[] {1,1,0,0,0,0};
		return territory;
	}
	
}
