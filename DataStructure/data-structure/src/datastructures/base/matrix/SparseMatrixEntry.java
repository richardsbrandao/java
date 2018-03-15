package datastructures.base.matrix;

public class SparseMatrixEntry implements Comparable<SparseMatrixEntry> {
	
	private int row;
	private int column;

	public SparseMatrixEntry(int row, int column) {
		this.row = row;
		this.column = column;
	}

	@Override
	public int compareTo(SparseMatrixEntry o) {
		if(row == o.row && column == o.column) {
			return 0;
		}
		if(row == o.row) {
			return column > o.column ? 1 : -1;
 		}
		return row > o.row ? 1 : -1;
	}
	
	@Override
	public String toString() {
		return "SparseMatrixEntry [row=" + row + ", column=" + column + "]";
	}

	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
}
