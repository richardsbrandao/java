package algorithm.cases.island;

public class IslandCounter {

	private int[][] territory;

	public IslandCounter(int[][] territory) {
		this.territory = territory;
	}
	
	public int islandCounter() {
		boolean[][] visited = new boolean[territory.length][territory[0].length];
		int count = 0;
		for(int i = 0; i < territory.length; i++) {
			for(int j = 0; j < territory[0].length; j++) {
				if(!visited[i][j] && isLand(i, j)) {
					count += countIslandInTerritory(i, j, visited, 0, true);
				}
				visited[i][j] = true;
			}
		}
		return count;
	}

	private int countIslandInTerritory(int i, int j, boolean[][] visited, int islandFounded, boolean self) {
		if(!isSafe(i, j)) {
			return islandFounded;
		}
		
		if(visited[i][j]) {
			return islandFounded;
		}
		
		visited[i][j] = true;

		if(!self) {
			if(isLand(i, j)) {
				islandFounded++;
			} else {
				return islandFounded;
			}
		}
		
		
		islandFounded += countIslandInTerritory(i, j-1, visited, 0, false);
		islandFounded += countIslandInTerritory(i, j+1, visited, 0, false);
		islandFounded += countIslandInTerritory(i-1, j, visited, 0, false);
		islandFounded += countIslandInTerritory(i+1, j, visited, 0, false);
		
		return islandFounded;
		
	}

	private boolean isLand(int i, int j) {
		return territory[i][j] == 1;
	}

	private boolean isSafe(int i, int j) {
		return i >= 0 && j >= 0 && i < territory.length && j < territory[i].length;
	}

}
