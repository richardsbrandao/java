package matrix;

/**
 * Write an algorithm such that if an element in an MxN matrix is 0.
 * Its entire row and column are set to 0
 */
public class ZeroMatrix {

	/**
	 * Runtime: O(NM)
	 * Space:   O(NM)
	 */
	public void execute(int[][] matrix) {
		boolean[] nullifyRows = new boolean[matrix.length];
		boolean[] nullifyColumns = new boolean[matrix[0].length];
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				if(matrix[i][j] == 0) {
					nullifyRows[i] = true;
					nullifyColumns[j] = true;
				}
			}
		}
		
		nullifyRows(matrix, nullifyRows);
		nullifyColumns(matrix, nullifyColumns);
	}

	private void nullifyRows(int[][] matrix, boolean[] nullifyRows) {
		for(int i = 0; i < nullifyRows.length; i++) {
			if(nullifyRows[i]) {
				nullifyRow(matrix, i);
			}
		}
	}

	private void nullifyRow(int[][] matrix, int row) {
		for(int colNum = 0; colNum < matrix[0].length; colNum++) {
			matrix[row][colNum] = 0;
		}
	}

	private void nullifyColumns(int[][] matrix, boolean[] nullifyColumns) {
		for(int i = 0; i < nullifyColumns.length; i++) {
			if(nullifyColumns[i]) {
				nullifyCol(matrix, i);
			}
		}
	}

	private void nullifyCol(int[][] matrix, int col) {
		for(int rowNum = 0; rowNum < matrix.length; rowNum++) {
			matrix[rowNum][col] = 0;
		}
	}
}
