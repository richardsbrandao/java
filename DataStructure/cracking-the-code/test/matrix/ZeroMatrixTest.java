package matrix;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("When Zero Matrix")
public class ZeroMatrixTest {

	@Nested
	class FirstImplementation {
		
		@DisplayName("With no zeros in matrix must not change matrix")
		@Test
		public void case_one() {
			int[][] matrix = matrixWithNoZero();
			new ZeroMatrix().execute(matrix);
			
			assertAll("matrix", 
					() -> assertEquals(1, matrix[0][0], "R:0 - C:0"),
					() -> assertEquals(2, matrix[0][1], "R:0 - C:1"),
					() -> assertEquals(6, matrix[0][2], "R:0 - C:2"),
					() -> assertEquals(5, matrix[0][3], "R:0 - C:3"),
					() -> assertEquals(8, matrix[0][4], "R:0 - C:4"),
					() -> assertEquals(8, matrix[1][0], "R:1 - C:0"),
					() -> assertEquals(2, matrix[1][1], "R:1 - C:1"),
					() -> assertEquals(5, matrix[1][2], "R:1 - C:2"),
					() -> assertEquals(1, matrix[1][3], "R:1 - C:3"),
					() -> assertEquals(11, matrix[1][4], "R:1 - C:4"),
					() -> assertEquals(6, matrix[2][0], "R:2 - C:0"),
					() -> assertEquals(7, matrix[2][1], "R:2 - C:1"),
					() -> assertEquals(8, matrix[2][2], "R:2 - C:2"),
					() -> assertEquals(2, matrix[2][3], "R:2 - C:3"),
					() -> assertEquals(33, matrix[2][4], "R:2 - C:4"),
					() -> assertEquals(1, matrix[3][0], "R:3 - C:0"),
					() -> assertEquals(2, matrix[3][1], "R:3 - C:1"),
					() -> assertEquals(5, matrix[3][2], "R:3 - C:2"),
					() -> assertEquals(3, matrix[3][3], "R:3 - C:3"),
					() -> assertEquals(40, matrix[3][4], "R:3 - C:4"),
					() -> assertEquals(1, matrix[4][0], "R:4 - C:0"),
					() -> assertEquals(5, matrix[4][1], "R:4 - C:1"),
					() -> assertEquals(9, matrix[4][2], "R:4 - C:2"),
					() -> assertEquals(8, matrix[4][3], "R:4 - C:3"),
					() -> assertEquals(15, matrix[4][4], "R:4 - C:4")
			);
		}

		@DisplayName("With one zero in matrix must change column and row with zero")
		@Test
		public void case_two() {
			int[][] matrix = matrixWithOneZero();
			new ZeroMatrix().execute(matrix);
			assertAll("matrix", 
					() -> assertEquals(1, matrix[0][0], "R:0 - C:0"),
					() -> assertEquals(0, matrix[0][1], "R:0 - C:1"),
					() -> assertEquals(6, matrix[0][2], "R:0 - C:2"),
					() -> assertEquals(5, matrix[0][3], "R:0 - C:3"),
					() -> assertEquals(8, matrix[0][4], "R:0 - C:4"),
					() -> assertEquals(8, matrix[1][0], "R:1 - C:0"),
					() -> assertEquals(0, matrix[1][1], "R:1 - C:1"),
					() -> assertEquals(5, matrix[1][2], "R:1 - C:2"),
					() -> assertEquals(1, matrix[1][3], "R:1 - C:3"),
					() -> assertEquals(11, matrix[1][4], "R:1 - C:4"),
					() -> assertEquals(0, matrix[2][0], "R:2 - C:0"),
					() -> assertEquals(0, matrix[2][1], "R:2 - C:1"),
					() -> assertEquals(0, matrix[2][2], "R:2 - C:2"),
					() -> assertEquals(0, matrix[2][3], "R:2 - C:3"),
					() -> assertEquals(0, matrix[2][4], "R:2 - C:4"),
					() -> assertEquals(1, matrix[3][0], "R:3 - C:0"),
					() -> assertEquals(0, matrix[3][1], "R:3 - C:1"),
					() -> assertEquals(5, matrix[3][2], "R:3 - C:2"),
					() -> assertEquals(3, matrix[3][3], "R:3 - C:3"),
					() -> assertEquals(40, matrix[3][4], "R:3 - C:4"),
					() -> assertEquals(1, matrix[4][0], "R:4 - C:0"),
					() -> assertEquals(0, matrix[4][1], "R:4 - C:1"),
					() -> assertEquals(9, matrix[4][2], "R:4 - C:2"),
					() -> assertEquals(8, matrix[4][3], "R:4 - C:3"),
					() -> assertEquals(15, matrix[4][4], "R:4 - C:4")
			);
		}
		
		@DisplayName("With two zeros in same row and two different columns must change to zero only one row and two columns")
		@Test
		public void case_three() {
			int[][] matrix = matrixWithTwoZerosInSameRow();
			new ZeroMatrix().execute(matrix);
			
			assertAll("matrix", 
					() -> assertEquals(1, matrix[0][0], "R:0 - C:0"),
					() -> assertEquals(2, matrix[0][1], "R:0 - C:1"),
					() -> assertEquals(6, matrix[0][2], "R:0 - C:2"),
					() -> assertEquals(0, matrix[0][3], "R:0 - C:3"),
					() -> assertEquals(8, matrix[0][4], "R:0 - C:4"),
					() -> assertEquals(0, matrix[1][0], "R:1 - C:0"),
					() -> assertEquals(0, matrix[1][1], "R:1 - C:1"),
					() -> assertEquals(0, matrix[1][2], "R:1 - C:2"),
					() -> assertEquals(0, matrix[1][3], "R:1 - C:3"),
					() -> assertEquals(0, matrix[1][4], "R:1 - C:4"),
					() -> assertEquals(6, matrix[2][0], "R:2 - C:0"),
					() -> assertEquals(3, matrix[2][1], "R:2 - C:1"),
					() -> assertEquals(8, matrix[2][2], "R:2 - C:2"),
					() -> assertEquals(0, matrix[2][3], "R:2 - C:3"),
					() -> assertEquals(33, matrix[2][4], "R:2 - C:4"),
					() -> assertEquals(0, matrix[3][0], "R:3 - C:0"),
					() -> assertEquals(0, matrix[3][1], "R:3 - C:1"),
					() -> assertEquals(0, matrix[3][2], "R:3 - C:2"),
					() -> assertEquals(0, matrix[3][3], "R:3 - C:3"),
					() -> assertEquals(0, matrix[3][4], "R:3 - C:4"),
					() -> assertEquals(1, matrix[4][0], "R:4 - C:0"),
					() -> assertEquals(5, matrix[4][1], "R:4 - C:1"),
					() -> assertEquals(9, matrix[4][2], "R:4 - C:2"),
					() -> assertEquals(0, matrix[4][3], "R:4 - C:3"),
					() -> assertEquals(15, matrix[4][4], "R:4 - C:4")
			);
		}
		
		@DisplayName("With two zeros in same column and two different rows must change to zero only one column and two rows")
		@Test
		public void case_four() {
			int[][] matrix = matrixWithTwoZerosInSameColumn();
			new ZeroMatrix().execute(matrix);
			assertAll("matrix", 
					() -> assertEquals(0, matrix[0][0], "R:0 - C:0"),
					() -> assertEquals(2, matrix[0][1], "R:0 - C:1"),
					() -> assertEquals(6, matrix[0][2], "R:0 - C:2"),
					() -> assertEquals(0, matrix[0][3], "R:0 - C:3"),
					() -> assertEquals(8, matrix[0][4], "R:0 - C:4"),
					() -> assertEquals(0, matrix[1][0], "R:1 - C:0"),
					() -> assertEquals(0, matrix[1][1], "R:1 - C:1"),
					() -> assertEquals(0, matrix[1][2], "R:1 - C:2"),
					() -> assertEquals(0, matrix[1][3], "R:1 - C:3"),
					() -> assertEquals(0, matrix[1][4], "R:1 - C:4"),
					() -> assertEquals(0, matrix[2][0], "R:2 - C:0"),
					() -> assertEquals(3, matrix[2][1], "R:2 - C:1"),
					() -> assertEquals(8, matrix[2][2], "R:2 - C:2"),
					() -> assertEquals(0, matrix[2][3], "R:2 - C:3"),
					() -> assertEquals(33, matrix[2][4], "R:2 - C:4"),
					() -> assertEquals(0, matrix[3][0], "R:3 - C:0"),
					() -> assertEquals(2, matrix[3][1], "R:3 - C:1"),
					() -> assertEquals(5, matrix[3][2], "R:3 - C:2"),
					() -> assertEquals(0, matrix[3][3], "R:3 - C:3"),
					() -> assertEquals(40, matrix[3][4], "R:3 - C:4"),
					() -> assertEquals(0, matrix[4][0], "R:4 - C:0"),
					() -> assertEquals(5, matrix[4][1], "R:4 - C:1"),
					() -> assertEquals(9, matrix[4][2], "R:4 - C:2"),
					() -> assertEquals(0, matrix[4][3], "R:4 - C:3"),
					() -> assertEquals(15, matrix[4][4], "R:4 - C:4")
			);
		}
		
		@DisplayName("With two zeros in two different columns and rows must change to zero both columns and rows")
		@Test
		public void case_five() {
			int[][] matrix = matrixWithTwoZerosInDifferentColumns();
			new ZeroMatrix().execute(matrix);
			assertAll("matrix", 
					() -> assertEquals(1, matrix[0][0], "R:0 - C:0"),
					() -> assertEquals(2, matrix[0][1], "R:0 - C:1"),
					() -> assertEquals(0, matrix[0][2], "R:0 - C:2"),
					() -> assertEquals(0, matrix[0][3], "R:0 - C:3"),
					() -> assertEquals(8, matrix[0][4], "R:0 - C:4"),
					() -> assertEquals(0, matrix[1][0], "R:1 - C:0"),
					() -> assertEquals(0, matrix[1][1], "R:1 - C:1"),
					() -> assertEquals(0, matrix[1][2], "R:1 - C:2"),
					() -> assertEquals(0, matrix[1][3], "R:1 - C:3"),
					() -> assertEquals(0, matrix[1][4], "R:1 - C:4"),
					() -> assertEquals(6, matrix[2][0], "R:2 - C:0"),
					() -> assertEquals(3, matrix[2][1], "R:2 - C:1"),
					() -> assertEquals(0, matrix[2][2], "R:2 - C:2"),
					() -> assertEquals(0, matrix[2][3], "R:2 - C:3"),
					() -> assertEquals(33, matrix[2][4], "R:2 - C:4"),
					() -> assertEquals(0, matrix[3][0], "R:3 - C:0"),
					() -> assertEquals(0, matrix[3][1], "R:3 - C:1"),
					() -> assertEquals(0, matrix[3][2], "R:3 - C:2"),
					() -> assertEquals(0, matrix[3][3], "R:3 - C:3"),
					() -> assertEquals(0, matrix[3][4], "R:3 - C:4"),
					() -> assertEquals(1, matrix[4][0], "R:4 - C:0"),
					() -> assertEquals(5, matrix[4][1], "R:4 - C:1"),
					() -> assertEquals(0, matrix[4][2], "R:4 - C:2"),
					() -> assertEquals(0, matrix[4][3], "R:4 - C:3"),
					() -> assertEquals(15, matrix[4][4], "R:4 - C:4")
			);
		}
	}
	
	private int[][] matrixWithNoZero() {
		int[][] matrix = new int[5][5];
		matrix[0] = new int[] {1,2,6,5,8};
		matrix[1] = new int[] {8,2,5,1,11};
		matrix[2] = new int[] {6,7,8,2,33};
		matrix[3] = new int[] {1,2,5,3,40};
		matrix[4] = new int[] {1,5,9,8,15};
		return matrix;
	}
	
	private int[][] matrixWithOneZero() {
		int[][] matrix = new int[5][5];
		matrix[0] = new int[] {1,2,6,5,8};
		matrix[1] = new int[] {8,2,5,1,11};
		matrix[2] = new int[] {6,0,8,2,33};
		matrix[3] = new int[] {1,2,5,3,40};
		matrix[4] = new int[] {1,5,9,8,15};
		return matrix;
	}
	
	private int[][] matrixWithTwoZerosInSameRow() {
		int[][] matrix = new int[5][5];
		matrix[0] = new int[] {1,2,6,5,8};
		matrix[1] = new int[] {8,2,5,0,11};
		matrix[2] = new int[] {6,3,8,2,33};
		matrix[3] = new int[] {1,2,5,0,40};
		matrix[4] = new int[] {1,5,9,8,15};
		return matrix;
	}
	
	private int[][] matrixWithTwoZerosInSameColumn() {
		int[][] matrix = new int[5][5];
		matrix[0] = new int[] {1,2,6,5,8};
		matrix[1] = new int[] {0,2,5,0,11};
		matrix[2] = new int[] {6,3,8,2,33};
		matrix[3] = new int[] {1,2,5,6,40};
		matrix[4] = new int[] {1,5,9,8,15};
		return matrix;
	}
	
	private int[][] matrixWithTwoZerosInDifferentColumns() {
		int[][] matrix = new int[5][5];
		matrix[0] = new int[] {1,2,6,5,8};
		matrix[1] = new int[] {8,2,5,0,11};
		matrix[2] = new int[] {6,3,8,2,33};
		matrix[3] = new int[] {1,2,0,5,40};
		matrix[4] = new int[] {1,5,9,8,15};
		return matrix;
	}

}
