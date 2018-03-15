package datastructures.matrix;

import static org.junit.Assert.assertEquals;

import java.util.TreeSet;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.matrix.SparseMatrixEntry;

public class SparseMatrixTest {

	@Test
	public void when_construct_with_correct_params_must_attribute_in_write_place() {
		SparseMatrix<Integer> matrix = new SparseMatrix<Integer>(5, 7, 4);
		
		Integer rows = getRows(matrix);
		Integer columns = getColumns(matrix);
		Integer nonZeros = getNonZeros(matrix);
		
		assertEquals(new Integer(5), rows);
		assertEquals(new Integer(7), columns);
		assertEquals(new Integer(4), nonZeros);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void when_construct_with_non_zeros_greater_than_row_power_column_must_throw_error() {
		new SparseMatrix<Integer>(2, 3, 1000);
	}

	@Test
	public void when_add_with_valid_place_must_add_in_the_collection() {
		SparseMatrix<Integer> matrix = new SparseMatrix<Integer>(5, 7, 4);
		matrix.add(3, 4);
		
		Integer rows = getRows(matrix);
		Integer columns = getColumns(matrix);
		Integer nonZeros = getNonZeros(matrix);
		Integer size = getSize(matrix);
		
		assertEquals(new Integer(5), rows);
		assertEquals(new Integer(7), columns);
		assertEquals(new Integer(4), nonZeros);
		assertEquals(new Integer(1), size);
		
		SparseMatrixEntry[] entries = getEntries(matrix);
		assertEquals(3, entries[0].getRow());
		assertEquals(4, entries[0].getColumn());
	}
	
	@SuppressWarnings("unchecked")
	private <T> SparseMatrixEntry[] getEntries(SparseMatrix<T> matrix) {
		TreeSet<SparseMatrixEntry> heap = (TreeSet<SparseMatrixEntry>) ReflectionTestUtils.getField(matrix, "elements");
		SparseMatrixEntry[] a = new SparseMatrixEntry[heap.size()];
		return (SparseMatrixEntry[]) heap.toArray(a);
	}

	private Integer getSize(SparseMatrix<Integer> matrix) {
		return (Integer) ReflectionTestUtils.getField(matrix, "size");
	}

	@Test
	public void when_add_2_times_with_valid_entry_must_add_it_with_entries_in_row_order() {
		SparseMatrix<Integer> matrix = new SparseMatrix<Integer>(5, 7, 4);
		matrix.add(3, 4);
		matrix.add(1, 2);
		
		Integer rows = getRows(matrix);
		Integer columns = getColumns(matrix);
		Integer nonZeros = getNonZeros(matrix);
		Integer size = getSize(matrix);
		
		assertEquals(new Integer(5), rows);
		assertEquals(new Integer(7), columns);
		assertEquals(new Integer(4), nonZeros);
		assertEquals(new Integer(2), size);
		
		SparseMatrixEntry[] entries = getEntries(matrix);
		assertEquals(1, entries[0].getRow());
		assertEquals(2, entries[0].getColumn());
		
		assertEquals(3, entries[1].getRow());
		assertEquals(4, entries[1].getColumn());
	}
	
	@Test
	public void when_add_with_more_than_one_elements_in_same_row_must_add_it() {
		SparseMatrix<Integer> matrix = new SparseMatrix<Integer>(5, 7, 4);
		matrix.add(3, 4);
		matrix.add(3, 2);
		
		Integer rows = getRows(matrix);
		Integer columns = getColumns(matrix);
		Integer nonZeros = getNonZeros(matrix);
		Integer size = getSize(matrix);
		
		assertEquals(new Integer(5), rows);
		assertEquals(new Integer(7), columns);
		assertEquals(new Integer(4), nonZeros);
		assertEquals(new Integer(2), size);
		
		SparseMatrixEntry[] entries = getEntries(matrix);
		assertEquals(3, entries[0].getRow());
		assertEquals(2, entries[0].getColumn());
		
		assertEquals(3, entries[1].getRow());
		assertEquals(4, entries[1].getColumn());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void when_add_with_equal_element_must_throw_error() {
		SparseMatrix<Integer> matrix = new SparseMatrix<Integer>(5, 7, 4);
		matrix.add(3, 2);
		matrix.add(3, 2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void when_add_with_invalid_row_must_throw_error() {
		SparseMatrix<Integer> matrix = new SparseMatrix<Integer>(5, 7, 4);
		matrix.add(6, 2);
	}

	@Test(expected=IllegalArgumentException.class)
	public void when_add_with_invalid_column_must_throw_error() {
		SparseMatrix<Integer> matrix = new SparseMatrix<Integer>(5, 7, 4);
		matrix.add(3, 8);
	}
	
	@Test(expected=IllegalStateException.class)
	public void when_add_with_full_valid_sparse_matrix_must_throw_error() {
		SparseMatrix<Integer> matrix = new SparseMatrix<Integer>(5, 7, 4);
		matrix.add(3, 4);
		matrix.add(3, 1);
		matrix.add(1, 2);
		matrix.add(0, 6);
		matrix.add(1, 1);
	}
	
	@Test
	public void when_draw_with_incomplete_matrix_must_print_it() {
		SparseMatrix<Integer> matrix = new SparseMatrix<Integer>(3, 3, 4);
		matrix.add(0, 1);
		matrix.add(1, 2);
		matrix.add(2, 2);
		matrix.add(0, 0);
		
		int[][] array = matrix.toMatrix();
		assertEquals(1, array[0][0]);
		assertEquals(1, array[0][1]);
		assertEquals(0, array[0][2]);
		assertEquals(0, array[1][0]);
		assertEquals(0, array[1][1]);
		assertEquals(1, array[1][2]);
		assertEquals(0, array[2][0]);
		assertEquals(0, array[2][1]);
		assertEquals(1, array[2][2]);
	}
	
	@Test
	public void when_draw_with_complete_matrix_must_print_it() {
		SparseMatrix<Integer> matrix = new SparseMatrix<Integer>(3, 3, 9);
		matrix.add(0, 0);
		matrix.add(0, 1);
		matrix.add(0, 2);
		matrix.add(1, 0);
		matrix.add(1, 1);
		matrix.add(1, 2);
		matrix.add(2, 0);
		matrix.add(2, 1);
		matrix.add(2, 2);
		
		int[][] array = matrix.toMatrix();
		assertEquals(1, array[0][0]);
		assertEquals(1, array[0][1]);
		assertEquals(1, array[0][2]);
		assertEquals(1, array[1][0]);
		assertEquals(1, array[1][1]);
		assertEquals(1, array[1][2]);
		assertEquals(1, array[2][0]);
		assertEquals(1, array[2][1]);
		assertEquals(1, array[2][2]);
	}
	
	private Integer getNonZeros(SparseMatrix<Integer> matrix) {
		return (Integer) ReflectionTestUtils.getField(matrix, "nonZeros");
	}

	private Integer getColumns(SparseMatrix<Integer> matrix) {
		return (Integer) ReflectionTestUtils.getField(matrix, "columns");
	}

	private Integer getRows(SparseMatrix<Integer> matrix) {
		return (Integer) ReflectionTestUtils.getField(matrix, "rows");
	}
	
}
