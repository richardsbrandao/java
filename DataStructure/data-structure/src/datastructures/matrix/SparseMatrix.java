package datastructures.matrix;

import java.util.TreeSet;

import datastructures.base.matrix.SparseMatrixEntry;

public class SparseMatrix<T> {

	private int rows;
	private int columns;
	private int nonZeros;
	private int size;
	private TreeSet<SparseMatrixEntry> elements;

	public SparseMatrix(int rows, int columns, int nonZeros) {
		if(Math.pow(rows, columns) < nonZeros) {
			throw new IllegalArgumentException();
		}
		this.rows = rows;
		this.columns = columns;
		this.nonZeros = nonZeros;
		this.size = 0;
		this.elements = new TreeSet<SparseMatrixEntry>();
	}
	
	public void add(int row, int column) {
		throwErrorIfFull();
		throwErrorIfInvalidEntry(row, column);
		SparseMatrixEntry entry = new SparseMatrixEntry(row, column);
		throwErrorIfEntryAlreadyExists(entry);
		elements.add(entry);
		this.size++;
	}

	private void throwErrorIfInvalidEntry(int row, int column) {
		if(!isValidEntry(row, column)) {
			throw new IllegalArgumentException();
		}
	}

	private void throwErrorIfEntryAlreadyExists(SparseMatrixEntry entry) {
		if(elements.contains(entry)) {
			throw new IllegalArgumentException();
		}
	}

	private void throwErrorIfFull() {
		if(this.nonZeros == elements.size()) {
			throw new IllegalStateException();
		}
	}
	
	private boolean isValidEntry(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public int[][] toMatrix() {
		int[][] matrix = new int[rows][columns];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(elements.contains(new SparseMatrixEntry(i, j))) {
					matrix[i][j] = 1;
				}
			}
		}
		return matrix;
	}
}
