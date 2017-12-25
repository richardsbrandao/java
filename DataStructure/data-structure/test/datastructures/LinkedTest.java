package datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.lists.Linked;
import datastructures.lists.linked.Node;


@RunWith(JUnit4.class)
public class LinkedTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void new_linked_list() {
		Linked<Integer> list = new Linked<Integer>();

		Integer size = getSize(list);
		Node<Integer> first = getFirst(list);
		Node<Integer> last = getLast(list);
	
		assertEquals(new Integer(0), size);
		assertNull(first);
		assertNull(last);
	}

	@Test
	public void add_one_element() {
		Linked<Integer> list = new Linked<Integer>();
		list.add(4);
		
		Integer size = getSize(list);
		Node<Integer> first = getFirst(list);
		Node<Integer> last = getLast(list);
		
		assertEquals(new Integer(1), size);
		
		assertEquals(new Integer(4), first.value());
		assertNull(first.previus());
		assertNull(first.next());
		
		assertEquals(new Integer(4), last.value());
		assertNull(last.previus());
		assertNull(last.next());
	}

	@Test
	public void add_two_elements() {
		Linked<Integer> list = new Linked<Integer>();
		list.add(4);
		list.add(7);
		
		Integer size = getSize(list);
		Node<Integer> first = getFirst(list);
		Node<Integer> last = getLast(list);
		
		assertEquals(new Integer(2), size);
		
		assertEquals(new Integer(4), first.value());
		assertNull(first.previus());
		assertEquals(new Integer(7), first.next().value());
		
		assertEquals(new Integer(7), last.value());
		assertEquals(new Integer(4), last.previus().value());
		assertNull(last.next());
	}
	
	@Test
	public void add_four_elements() {
		Linked<Integer> list = new Linked<Integer>();
		list.add(4);
		list.add(7);
		list.add(9);
		list.add(1);
		
		Integer size = getSize(list);
		Node<Integer> first = getFirst(list);
		Node<Integer> last = getLast(list);
		
		assertEquals(new Integer(4), size);
		
		assertEquals(new Integer(4), first.value());
		assertEquals(new Integer(7), first.next().value());
		assertEquals(new Integer(9), first.next().next().value());
		assertEquals(new Integer(1), first.next().next().next().value());
		
		assertEquals(new Integer(1), last.value());
		assertEquals(new Integer(9), last.previus().value());
		assertEquals(new Integer(7), last.previus().previus().value());
		assertEquals(new Integer(4), last.previus().previus().previus().value());
	}
	
	@Test
	public void size_of_linked_list() {
		Linked<String> list = new Linked<String>();
		assertEquals(0, list.size());
		list.add("a");
		list.add("b");
		assertEquals(2, list.size());
	}
	
	@Test
	public void linked_list_is_empty() {
		Linked<String> list = new Linked<String>();
		assertTrue(list.isEmpty());
		list.add("a");
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void get_first_with_empty_list() {
		Linked<Integer> list = new Linked<Integer>();
		exception.expect(NoSuchElementException.class);
		list.getFirst();
	}
	
	@Test
	public void get_last_with_empty_list() {
		Linked<Integer> list = new Linked<Integer>();
		exception.expect(NoSuchElementException.class);
		list.getLast();
	}
	
	@Test
	public void get_first_and_last_with_single_element() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		
		assertEquals("4", list.getFirst());
		assertEquals("4", list.getLast());
	}
	
	@Test
	public void get_first_and_last_with_two_elements_in_a_list() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		list.add("7");
		
		assertEquals("4", list.getFirst());
		assertEquals("7", list.getLast());
	}
	
	@Test
	public void get_first_and_last_with_three_elements_in_a_list() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		list.add("7");
		list.add("9");
		
		assertEquals("4", list.getFirst());
		assertEquals("9", list.getLast());
	}
	
	@Test
	public void add_last_empty_list() {
		Linked<String> list = new Linked<String>();
		list.addLast("4");
		
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);
		
		assertEquals("4", first.value());
		assertEquals("4", last.value());
	}
	
	@Test
	public void add_last_single_element_list() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		list.addLast("7");
		
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);
		
		assertEquals("4", first.value());
		assertEquals("7", last.value());
	}
	
	@Test
	public void add_last_list_with_two_elements() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		list.add("7");
		list.addLast("9");
		
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);
		
		assertEquals("4", first.value());
		assertEquals("9", last.value());
	}
	
	@Test
	public void add_last_list_with_three_elements() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		list.add("7");
		list.add("9");
		list.addLast("1");
		
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);
		
		assertEquals("4", first.value());
		assertEquals("1", last.value());
	}
	
	@Test
	public void add_first_empty_list() {
		Linked<String> list = new Linked<String>();
		list.addFirst("4");
		
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);
		
		assertEquals("4", first.value());
		assertEquals("4", last.value());
	}
	
	@Test
	public void add_first_single_element_list() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		list.addFirst("7");
		
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);
		
		assertEquals("7", first.value());
		assertEquals("4", last.value());
	}
	
	@Test
	public void add_first_list_with_two_elements() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		list.add("7");
		list.addFirst("9");
		
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);
		
		assertEquals("9", first.value());
		assertEquals("7", last.value());
	}
	
	@Test
	public void add_first_list_with_three_elements() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		list.add("7");
		list.add("9");
		list.addFirst("1");
		
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);
		
		assertEquals("1", first.value());
		assertEquals("9", last.value());
	}
	
	@Test
	public void remove_first_element_with_empty_list() {
		Linked<String> list = new Linked<String>();
		exception.expect(NoSuchElementException.class);
		list.removeFirst();
	}
	
	@Test
	public void remove_first_element_with_single_element_list() {
		Linked<String> list = new Linked<String>();
		list.add("4");

		list.removeFirst();
		
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);

		assertEquals(new Integer(0), size);
		assertNull(first);
		assertNull(last);
	}
	
	@Test
	public void remove_first_element_in_list_with_two_elements() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		list.add("7");

		list.removeFirst();
		
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);

		assertEquals(new Integer(1), size);
		assertEquals("7", first.value());
		assertEquals("7", last.value());
	}
	
	@Test
	public void remove_first_element_in_list_with_three_elements() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		list.add("7");
		list.add("9");
		
		list.removeFirst();
		
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);

		assertEquals(new Integer(2), size);
		assertEquals("7", first.value());
		assertEquals("9", last.value());
	}
	
	@Test
	public void remove_last_element_with_empty_list() {
		Linked<String> list = new Linked<String>();
		exception.expect(NoSuchElementException.class);
		list.removeLast();
	}
	
	@Test
	public void remove_last_element_with_single_element_list() {
		Linked<String> list = new Linked<String>();
		list.add("4");

		list.removeLast();
		
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);

		assertEquals(new Integer(0), size);
		assertNull(first);
		assertNull(last);
	}
	
	@Test
	public void remove_last_element_in_list_with_two_elements() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		list.add("7");

		list.removeLast();
		
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);

		assertEquals(new Integer(1), size);
		assertEquals("4", first.value());
		assertEquals("4", last.value());
	}
	
	@Test
	public void remove_last_element_in_list_with_three_elements() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		list.add("7");
		list.add("9");
		
		list.removeLast();
		
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);

		assertEquals(new Integer(2), size);
		assertEquals("4", first.value());
		assertEquals("7", last.value());
	}
	
	@Test
	public void contains_null_element_in_a_list() {
		Linked<String> list = new Linked<String>();
		list.add("4");
		assertFalse(list.contains(null));
		
		list.add(null);
		list.add("O");
		assertTrue(list.contains(null));
	}
	
	@Test
	public void contains_empty_list() {
		Linked<Object> list = new Linked<Object>();
		assertFalse(list.contains("A"));
	}
	
	@Test
	public void contains_in_a_list_with_some_elements() {
		Linked<String> list = new Linked<String>();
		list.add("B");
		list.add("C");
		list.add("I");
		list.add("D");
		list.add("D");
		list.add("A");
		list.add("B");
		
		assertTrue(list.contains("A"));
		assertTrue(list.contains("B"));
		assertFalse(list.contains("1"));
	}
	
	public void get_element_in_empty_list() {
		Linked<String> list = new Linked<String>();
		
		try {
			list.get(0);
			fail("Must throw ArrayIndexOutOfBoundsException");
		} catch(ArrayIndexOutOfBoundsException e) { }
	}
	
	@Test
	public void get_element_by_index() {
		Linked<String> list = new Linked<String>();
		list.add("B"); list.add("C"); list.add("I"); list.add("D");
		list.add("D"); list.add("A"); list.add("B"); list.add("O");
		
		assertEquals("B", list.get(0));
		assertEquals("C", list.get(1));
		assertEquals("O", list.get(7));
		assertEquals("A", list.get(5));
		assertEquals("D", list.get(3));
	}
	
	@Test
	public void get_element_by_invalid_index() {
		Linked<String> list = new Linked<String>();
		list.add("B"); list.add("C"); list.add("I"); list.add("D");
		list.add("D"); list.add("A"); list.add("B"); list.add("O");
		
		try {
			list.get(-1);
			fail("Must throw ArrayIndexOutOfBoundsException");
		} catch(ArrayIndexOutOfBoundsException e) { }
		
		try {
			list.get(8);
			fail("Must throw ArrayIndexOutOfBoundsException");
		} catch(ArrayIndexOutOfBoundsException e) { }
	}
	
	public void remove_element_in_empty_list() {
		Linked<String> list = new Linked<String>();
		
		try {
			list.remove(0);
			fail("Must throw ArrayIndexOutOfBoundsException");
		} catch(ArrayIndexOutOfBoundsException e) { }
	}
	
	@Test
	public void remove_first_element_by_index() {
		Linked<String> list = new Linked<String>();
		list.add("B"); list.add("C"); list.add("I"); list.add("D");
		list.add("D"); list.add("A"); list.add("B"); list.add("O");
		
		list.remove(0);
		
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		
		assertEquals(new Integer(7), size);
		assertEquals("C", first.value());
	}
	
	@Test
	public void remove_last_element_by_index() {
		Linked<String> list = new Linked<String>();
		list.add("B"); list.add("C"); list.add("I"); list.add("D");
		list.add("D"); list.add("A"); list.add("B"); list.add("O");
		
		list.remove(7);
		
		Integer size = getSize(list);
		Node<String> last = getLast(list);
		
		assertEquals(new Integer(7), size);
		assertEquals("B", last.value());
	}
	
	@Test
	public void remove_any_element_by_index() {
		Linked<String> list = new Linked<String>();
		list.add("B"); list.add("C"); list.add("I"); list.add("D");
		list.add("D"); list.add("A"); list.add("B"); list.add("O");
		
		list.remove(6);
		list.remove(3);
		
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);
		
		assertEquals(new Integer(6), size);
		assertEquals("D", first.next().next().next().value());
		assertEquals("A", last.previus().value());
	}
	
	@Test
	public void remove_element_by_invalid_index() {
		Linked<String> list = new Linked<String>();
		list.add("B"); list.add("C"); list.add("I"); list.add("D");
		list.add("D"); list.add("A"); list.add("B"); list.add("O");
		
		try {
			list.remove(-1);
			fail("Must throw ArrayIndexOutOfBoundsException");
		} catch(ArrayIndexOutOfBoundsException e) { }
		
		try {
			list.remove(8);
			fail("Must throw ArrayIndexOutOfBoundsException");
		} catch(ArrayIndexOutOfBoundsException e) { }
	}
	
	@Test
	public void merge_empty_list() {
		Linked<String> list = new Linked<String>();
		Linked<String> anotherList = new Linked<String>();
		anotherList.add("A");
		anotherList.add("B");
		list.addAll(anotherList);
		
		Node<String> first = getFirst(list);
		assertEquals(new Integer(2), getSize(list));
		assertEquals("A", first.value());
		assertEquals("B", first.next().value());
	}
	
	@Test
	public void merge_two_empty_lists() {
		Linked<Object> list = new Linked<Object>();
		list.addAll(new Linked<Object>());
		
		assertEquals(new Integer(0), getSize(list));
		assertNull(getFirst(list));
		assertNull(getLast(list));
	}
	
	@Test
	public void merge_two_non_empty_lists() {
		Linked<String> list = new Linked<String>();
		list.add("C");
		list.add("B");
		Linked<String> anotherList = new Linked<String>();
		anotherList.add("A");
		anotherList.add("B");
		list.addAll(anotherList);
		
		Node<String> first = getFirst(list);
		assertEquals(new Integer(4), getSize(list));
		assertEquals("C", first.value());
		assertEquals("B", first.next().value());
		assertEquals("A", first.next().next().value());
		assertEquals("B", first.next().next().next().value());
	}
	
	@Test
	public void add_element_in_index_0_in_empty_list() {
		Linked<String> list = new Linked<String>();
		list.add(0, "A");
		
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		
		assertEquals(new Integer(1), size);
		assertEquals("A", first.value());
	}
	
	@Test
	public void add_element_in_index_0_in_non_empty_list() {
		Linked<String> list = new Linked<String>();
		list.add("B");
		list.add(0, "A");
		
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		
		assertEquals(new Integer(2), size);
		assertEquals("A", first.value());
		assertEquals("B", first.next().value());
	}

	@Test
	public void add_element_in_invalid_index() {
		Linked<String> list = new Linked<String>();
		exception.expect(ArrayIndexOutOfBoundsException.class);
		list.add(1, "B");
	}

	@Test
	public void add_element_in_middle() {
		Linked<String> list = new Linked<String>();
		list.add("A"); list.add("B"); list.add("C"); list.add("E");
		list.add(3, "D");
		
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		
		assertEquals(new Integer(5), size);
		assertEquals("A", first.value());
		assertEquals("B", first.next().value());
		assertEquals("C", first.next().next().value());
		assertEquals("D", first.next().next().next().value());
		assertEquals("E", first.next().next().next().next().value());
	}

	@Test
	public void add_element_in_last_index() {
		Linked<String> list = new Linked<String>();
		list.add("A"); list.add("B"); list.add("C");
		list.add(3, "D");
		
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		
		assertEquals(new Integer(4), size);
		assertEquals("A", first.value());
		assertEquals("B", first.next().value());
		assertEquals("C", first.next().next().value());
		assertEquals("D", first.next().next().next().value());
	}
	
	@Test
	public void clean_empty_list() {
		Linked<String> list = new Linked<String>();
		list.clear();
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);
		
		assertEquals(new Integer(0), size);
		assertNull(first);
		assertNull(last);
	}
	
	@Test
	public void clean_list() {
		Linked<String> list = new Linked<String>();
		list.add("A"); list.add("B"); list.add("C");
		list.clear();
		Integer size = getSize(list);
		Node<String> first = getFirst(list);
		Node<String> last = getLast(list);
		
		assertEquals(new Integer(0), size);
		assertNull(first);
		assertNull(last);
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Object> Node<T> getLast(Linked<T> list) {
		return (Node<T>) ReflectionTestUtils.getField(list, "last");
	}

	@SuppressWarnings("unchecked")
	private <T extends Object> Node<T> getFirst(Linked<T> list) {
		return (Node<T>) ReflectionTestUtils.getField(list, "first");
	}

	private <T extends Object> Integer getSize(Linked<T> list) {
		return (Integer) ReflectionTestUtils.getField(list, "size");
	}
	
}
