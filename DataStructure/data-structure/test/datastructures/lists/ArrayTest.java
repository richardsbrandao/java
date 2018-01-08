package datastructures.lists;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("ArrayList ")
public class ArrayTest {

	@Nested
	@DisplayName("when new")
	class WhenNew {
		
		@Test
		@DisplayName("with right params must construct correctly")
		public void case_1() {
			Array<String> array = new Array<String>(3);

			Object[] elements = (Object[]) ReflectionTestUtils.getField(array, "elements");
			Integer size = getSize(array);
			
			assertAll("properties",
					() -> assertEquals(3, elements.length),
					() -> assertEquals(Integer.valueOf(0), size)
			);
		}
		
		@Test
		@DisplayName("with wrong params must throw IllegalArgumentException")
		public void case_2() {
			assertThrows(IllegalArgumentException.class, () -> new Array<String>(-1));
		}
	}
	
	@Nested
	@DisplayName("when add")
	class WhenAdd {
		@DisplayName("one item must store the element and change size")
		@Test
		public void case_1() {
			Array<String> array = new Array<String>(3);
			assertEquals(Integer.valueOf(0), getSize(array));
			
			array.add("Richard");
			assertEquals(Integer.valueOf(1), getSize(array));
		}
		
		@DisplayName("more than capacity must double the store array and save all elements")
		@Test
		public void case_2() {
			Array<String> array = new Array<String>(3);
			array.add("Richard");
			array.add("Ketherin");
			array.add("Carlos");
			array.add("Cristina");
			
			assertAll("properties",
					() -> assertEquals(Integer.valueOf(4), getSize(array)),
					() -> assertEquals(6, getElements(array).length)
			);
		}
	}
	
	@Nested
	@DisplayName("when remove")
	class WhenRemove {
		@DisplayName("by valid index must remove element and resize the array and change size")
		@Test
		public void case_1() {
			Array<String> array = new Array<String>(5);
			array.add("A");
			array.add("B");
			array.add("C");
			array.add("1");
			array.add("D");
			
			array.remove(3);
			
			assertAll("properties",
					() -> assertEquals(Integer.valueOf(4), getSize(array)),
					() -> {
						Object[] elements = getElements(array);
						assertAll("elements",
								() -> assertEquals("A", elements[0]),
								() -> assertEquals("B", elements[1]),
								() -> assertEquals("C", elements[2]),
								() -> assertEquals("D", elements[3])
						);
						
					}
			);
			
		}
		
		@DisplayName("by invalid index must throws ArrayIndexOutOfBounds")
		@Test
		public void case_2() {
			Array<String> array = new Array<String>(5);
			
			array.add("A");
			array.add("B");
			
			assertAll("properties and behavior",
					() -> {
						assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.remove(4));
						assertEquals(Integer.valueOf(2), getSize(array));
					},
					() -> {
						assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.remove(8));
						assertEquals(Integer.valueOf(2), getSize(array));
					},
					() -> {
						assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.remove(-1));
						assertEquals(Integer.valueOf(2), getSize(array));
					}
			);
		}
	}
	

	@Nested
	@DisplayName("when get element")
	class WhenGetElement {
		@DisplayName("by existing index must get it")
		@Test
		public void case_1() {
			Array<String> array = new Array<String>(5);
			array.add("A");
			array.add("B");
			
			assertAll("result",
					() -> assertEquals("A", array.get(0)),
					() -> assertEquals("B", array.get(1))
			);
		}
		
		@DisplayName("by invalid index must throw ArrayIndexOutOfBoundsException")
		@Test
		public void case_2() {
			Array<String> element = new Array<String>(4);
			
			assertAll("result",
					() -> assertThrows(ArrayIndexOutOfBoundsException.class, () -> element.get(-1)),
					() -> assertThrows(ArrayIndexOutOfBoundsException.class, () -> element.get(0)),
					() -> assertThrows(ArrayIndexOutOfBoundsException.class, () -> element.get(8))
			);
		}
	}
	
	@Nested
	@DisplayName("when check if")
	class WhenEmpty {
		@Test
		@DisplayName("empty array must return true")
		public void case_1() {
			Array<String> array = new Array<String>(5);
			assertTrue(array.isEmpty());
		}
		
		@Test
		@DisplayName("not empty array must return false")
		public void case_2() {
			Array<String> array = new Array<String>(5);
			array.add("A");
			assertFalse(array.isEmpty());
		}
		
		@Test
		@DisplayName("empty after remove the only element in array must return false")
		public void case_3() {
			Array<String> array = new Array<String>(5);
			array.add("A");
			array.remove(0);
			assertTrue(array.isEmpty());
		}
	}
	
	@Nested
	@DisplayName("when clear")
	class WhenClear {
		@Test
		@DisplayName("array must remove all elements and unset size")
		public void case_1() {
			Array<String> array = new Array<String>(4);
			array.add("A");
			array.clear();
			
			assertAll("properties",
					() -> assertEquals(Integer.valueOf(0), getSize(array)),
					() -> assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.get(0))
			);
		}
	}
	
	@Nested
	@DisplayName("when merge")
	class WhenMerge {
		@Test
		@DisplayName("two lists must merge all elements resizing the array and change size")
		public void case_1() {
			Array<String> firstArray = new Array<String>(4);
			firstArray.add("A");
			firstArray.add("B");
			Array<String> secondArray = new Array<String>(7);
			secondArray.add("C");
			secondArray.add("D");
			secondArray.add("E");
			
			firstArray.addAll(secondArray);
			
			assertAll("properties",
					() -> assertEquals(Integer.valueOf(5), getSize(firstArray)),
					() -> assertEquals(Integer.valueOf(3), getSize(secondArray)),
					() -> assertEquals(8, getElements(firstArray).length),
					() -> assertEquals(7, getElements(secondArray).length),
					() -> {
						Object[] elements = getElements(firstArray);
						assertEquals("A", elements[0]);
						assertEquals("B", elements[1]);
						assertEquals("C", elements[2]);
						assertEquals("D", elements[3]);
						assertEquals("E", elements[4]);
					},
					() -> {
						Object[] elements = getElements(secondArray);
						assertEquals("C", elements[0]);
						assertEquals("D", elements[1]);
						assertEquals("E", elements[2]);
					}
			);
		}
	}
	
	@Nested
	@DisplayName("when contains")
	class WhenContains {
		private Array<String> array;
		
		@BeforeEach
		public void setUp() {
			this.array = new Array<String>(4);
			array.add("A");
			array.add("B");
		}
		
		@Test
		@DisplayName("right element must return true")
		public void case_1() {
			assertTrue(array.contains("A"));
		}
		
		@Test
		@DisplayName("absent element must return false")
		public void case_2() {
			assertFalse(array.contains("V"));
		}
		
		@DisplayName("null element must return false")
		public void case_3() {
			assertFalse(array.contains(null));
		}
		
		@DisplayName("case sensitive element must return false")
		public void case_4() {
			assertFalse(array.contains("a"));
		}
	}
	
	@Nested
	@DisplayName("when add element at")
	class WhenAddToIndex {
		private Array<String> array;
		
		@BeforeEach
		public void setUp() {
			this.array = new Array<String>(4);
			array.add(0, "A");
			array.add(1, "B");
		}
		
		@Test
		@DisplayName("repeated index must add and move others elements forward")
		public void case_1() {
			array.add(0, "C");
			
			assertEquals("C", array.get(0));
			assertEquals("A", array.get(1));
			assertEquals("B", array.get(2));
		}
		
		@Test
		@DisplayName("invalid position must throws ArrayIndexOutOfBoundsException")
		public void case_2() {
			assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.add(3, "C"));
		}
		
		@Test
		@DisplayName("valid position in a full array must resize and add the element")
		public void case_3() {
			array.add(2, "C");
			array.add(3, "D");
			array.add(4, "E");
			
			assertAll("properties", 
				() -> {
					assertEquals("A", array.get(0));
					assertEquals("B", array.get(1));
					assertEquals("C", array.get(2));
					assertEquals("D", array.get(3));
					assertEquals("E", array.get(4));
				},
				() -> assertEquals(Integer.valueOf(5), getSize(array))	
			);
		}
	}
	
	private Object[] getElements(Array<String> array) {
		return (Object[]) ReflectionTestUtils.getField(array, "elements");
	}
	
	private <T> Integer getSize(Array<T> array) {
		return (Integer) ReflectionTestUtils.getField(array, "size"); 
	}
}
