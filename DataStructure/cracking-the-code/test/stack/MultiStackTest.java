package stack;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.InvalidParameterException;
import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("MultiStack: ")
public class MultiStackTest {
	private MultiStack multiStack;
	
	@BeforeEach
	public void setUp() {
		this.multiStack = new MultiStack();
	}
	
	@Nested
	@DisplayName("when push")
	class WhenPush {
		
		@Nested
		class Push {
			@DisplayName("first item in first empty stack must insert in right stack")
			@Test
			public void case_one() {
				multiStack.push(0, "Y");
				
				String[] repository = getRepositoryInMultiStack();
				assertAll("repository", 
						() -> assertEquals("Y", repository[0], "repository[0] != Y"),
						() -> assertNull(repository[1], "repository[1] != NULL"),
						() -> assertNull(repository[2], "repository[2] != NULL"),
						() -> assertNull(repository[3], "repository[3] != NULL"),
						() -> assertNull(repository[4], "repository[4] != NULL"),
						() -> assertNull(repository[5], "repository[5] != NULL"),
						() -> assertNull(repository[6], "repository[6] != NULL"),
						() -> assertNull(repository[7], "repository[7] != NULL"),
						() -> assertNull(repository[8], "repository[8] != NULL"),
						() -> assertNull(repository[9], "repository[9] != NULL"),
						() -> assertNull(repository[10], "repository[10] != NULL"),
						() -> assertNull(repository[11], "repository[11] != NULL"),
						() -> assertNull(repository[12], "repository[12] != NULL"),
						() -> assertNull(repository[13], "repository[13] != NULL"),
						() -> assertNull(repository[14], "repository[14] != NULL")
				);
				
				int[] size = getSizeInMultiStack();
				assertAll("size", 
						() -> assertEquals(1, size[0], "size[0] != 1"),
						() -> assertEquals(0, size[1], "size[1] != 0"),
						() -> assertEquals(0, size[2], "size[2] != 0")
				);
			}

			@DisplayName("first item in second empty stack must insert in right stack")
			@Test
			public void case_two() {
				multiStack.push(1, "Y");
				
				String[] repository = getRepositoryInMultiStack();
				assertAll("repository", 
						() -> assertNull(repository[0], "repository[0] != NULL"),
						() -> assertNull(repository[1], "repository[1] != NULL"),
						() -> assertNull(repository[2], "repository[2] != NULL"),
						() -> assertNull(repository[3], "repository[3] != NULL"),
						() -> assertNull(repository[4], "repository[4] != NULL"),
						() -> assertEquals("Y", repository[5], "repository[5] != Y"),
						() -> assertNull(repository[6], "repository[6] != NULL"),
						() -> assertNull(repository[7], "repository[7] != NULL"),
						() -> assertNull(repository[8], "repository[8] != NULL"),
						() -> assertNull(repository[9], "repository[9] != NULL"),
						() -> assertNull(repository[10], "repository[10] != NULL"),
						() -> assertNull(repository[11], "repository[11] != NULL"),
						() -> assertNull(repository[12], "repository[12] != NULL"),
						() -> assertNull(repository[13], "repository[13] != NULL"),
						() -> assertNull(repository[14], "repository[14] != NULL")
				);
				
				int[] size = getSizeInMultiStack();
				assertAll("size", 
						() -> assertEquals(0, size[0], "size[0] != 0"),
						() -> assertEquals(1, size[1], "size[1] != 1"),
						() -> assertEquals(0, size[2], "size[2] != 0")
				);
			}
			
			@DisplayName("first item in third empty stack must insert in right stack")
			@Test
			public void case_three() {
				multiStack.push(2, "Y");
				
				String[] repository = getRepositoryInMultiStack();
				assertAll("repository", 
						() -> assertNull(repository[0], "repository[0] != NULL"),
						() -> assertNull(repository[1], "repository[1] != NULL"),
						() -> assertNull(repository[2], "repository[2] != NULL"),
						() -> assertNull(repository[3], "repository[3] != NULL"),
						() -> assertNull(repository[4], "repository[4] != NULL"),
						() -> assertNull(repository[5], "repository[5] != NULL"),
						() -> assertNull(repository[6], "repository[6] != NULL"),
						() -> assertNull(repository[7], "repository[7] != NULL"),
						() -> assertNull(repository[8], "repository[8] != NULL"),
						() -> assertNull(repository[9], "repository[9] != NULL"),
						() -> assertEquals("Y", repository[10], "repository[10] != Y"),
						() -> assertNull(repository[11], "repository[11] != NULL"),
						() -> assertNull(repository[12], "repository[12] != NULL"),
						() -> assertNull(repository[13], "repository[13] != NULL"),
						() -> assertNull(repository[14], "repository[14] != NULL")
				);
				
				int[] size = getSizeInMultiStack();
				assertAll("size", 
						() -> assertEquals(0, size[0], "size[0] != 0"),
						() -> assertEquals(0, size[1], "size[1] != 0"),
						() -> assertEquals(1, size[2], "size[2] != 1")
				);
			}
			
			@DisplayName("items in different stacks must respect stack separation")
			@Test
			public void case_four() {
				multiStack.push(2, "A");
				multiStack.push(0, "B");
				multiStack.push(0, "C");
				multiStack.push(1, "D");
				multiStack.push(2, "E");
				multiStack.push(2, "F");
				multiStack.push(0, "G");
				
				String[] repository = getRepositoryInMultiStack();
				assertAll("repository", 
						() -> assertEquals("B", repository[0], "repository[0] != B"),
						() -> assertEquals("C", repository[1], "repository[1] != C"),
						() -> assertEquals("G", repository[2], "repository[2] != G"),
						() -> assertNull(repository[3], "repository[3] != NULL"),
						() -> assertNull(repository[4], "repository[4] != NULL"),
						() -> assertEquals("D", repository[5], "repository[5] != D"),
						() -> assertNull(repository[6], "repository[6] != NULL"),
						() -> assertNull(repository[7], "repository[7] != NULL"),
						() -> assertNull(repository[8], "repository[8] != NULL"),
						() -> assertNull(repository[9], "repository[9] != NULL"),
						() -> assertEquals("A", repository[10], "repository[10] != A"),
						() -> assertEquals("E", repository[11], "repository[11] != E"),
						() -> assertEquals("F", repository[12], "repository[12] != F"),
						() -> assertNull(repository[13], "repository[13] != NULL"),
						() -> assertNull(repository[14], "repository[14] != NULL")
				);
				
				int[] size = getSizeInMultiStack();
				assertAll("size", 
						() -> assertEquals(3, size[0], "size[0] != 3"),
						() -> assertEquals(1, size[1], "size[1] != 1"),
						() -> assertEquals(3, size[2], "size[2] != 3")
				);
			}
			
			@DisplayName("item in invalid stack must throw error")
			public void case_five() {
				assertThrows(InvalidParameterException.class, () -> multiStack.push(3, "A"));
			}
		}
		
		@Nested
		class StackOverflow {
			@DisplayName("in first full stack must throw stackoverflowexception")
			@Test
			public void case_one() {
				multiStack.push(0, "A");
				multiStack.push(0, "B");
				multiStack.push(0, "C");
				multiStack.push(0, "D");
				multiStack.push(0, "E");

				assertThrows(StackOverflowError.class, () -> multiStack.push(0, "F"));
			}
			
			@DisplayName("in second full stack must throw stackoverflowexception")
			@Test
			public void case_two() {
				multiStack.push(1, "A");
				multiStack.push(1, "B");
				multiStack.push(1, "C");
				multiStack.push(1, "D");
				multiStack.push(1, "E");

				assertThrows(StackOverflowError.class, () -> multiStack.push(1, "F"));
			}
			
			@DisplayName("in third full stack must throw stackoverflowexception")
			@Test
			public void case_three() {
				multiStack.push(2, "A");
				multiStack.push(2, "B");
				multiStack.push(2, "C");
				multiStack.push(2, "D");
				multiStack.push(2, "E");

				assertThrows(StackOverflowError.class, () -> multiStack.push(2, "F"));
			}
		}
	}

	@Nested
	@DisplayName("when pop")
	class WhenPop {
		@DisplayName("in first stack must pop element")
		@Test
		public void case_one() {
			multiStack.push(0, "A");
			
			String item = multiStack.pop(0);
			
			assertEquals("A", item);
			
			String[] repository = getRepositoryInMultiStack();
			assertNull(repository[0]);
			
			int[] size = getSizeInMultiStack();
			assertAll("size", 
					() -> assertEquals(0, size[0], "size[0] != 0"),
					() -> assertEquals(0, size[1], "size[1] != 0"),
					() -> assertEquals(0, size[2], "size[2] != 0")
			);
		}
		@DisplayName("in second stack must pop element")
		@Test
		public void case_two() {
			multiStack.push(1, "A");
			
			String item = multiStack.pop(1);
			
			assertEquals("A", item);
			
			String[] repository = getRepositoryInMultiStack();
			assertNull(repository[5]);
			
			int[] size = getSizeInMultiStack();
			assertAll("size", 
					() -> assertEquals(0, size[0], "size[0] != 0"),
					() -> assertEquals(0, size[1], "size[1] != 0"),
					() -> assertEquals(0, size[2], "size[2] != 0")
			);
		}
		@DisplayName("in third stack must pop element")
		@Test
		public void case_three() {
			multiStack.push(2, "A");
			
			String item = multiStack.pop(2);

			assertEquals("A", item);
			
			String[] repository = getRepositoryInMultiStack();
			assertNull(repository[10]);
			
			int[] size = getSizeInMultiStack();
			assertAll("size", 
					() -> assertEquals(0, size[0], "size[0] != 0"),
					() -> assertEquals(0, size[1], "size[1] != 0"),
					() -> assertEquals(0, size[2], "size[2] != 0")
			);
		}
		@DisplayName("in empty first stack must throw error")
		@Test
		public void case_four() {
			assertThrows(EmptyStackException.class, () -> multiStack.pop(0));
		}
		@DisplayName("in empty second stack must throw error")
		@Test
		public void case_five() {
			assertThrows(EmptyStackException.class, () -> multiStack.pop(1));
		}
		@DisplayName("in empty third stack must throw error")
		@Test
		public void case_six() {
			assertThrows(EmptyStackException.class, () -> multiStack.pop(2));
		}
	}

	@Nested
	@DisplayName("when peek")
	class WhenPeek {
		@DisplayName("in first stack must peek and let the item")
		@Test
		public void case_one() {
			multiStack.push(0, "A");
			
			String item = multiStack.peek(0);
			
			assertEquals("A", item);
			
			String[] repository = getRepositoryInMultiStack();
			assertEquals("A", repository[0]);
			
			int[] size = getSizeInMultiStack();
			assertAll("size", 
					() -> assertEquals(1, size[0], "size[0] != 1"),
					() -> assertEquals(0, size[1], "size[1] != 0"),
					() -> assertEquals(0, size[2], "size[2] != 0")
			);
		}
		@DisplayName("in second stack must peek and let the item")
		@Test
		public void case_two() {
			multiStack.push(1, "A");
			
			String item = multiStack.peek(1);
			
			assertEquals("A", item);
			
			String[] repository = getRepositoryInMultiStack();
			assertEquals("A", repository[5]);
			
			int[] size = getSizeInMultiStack();
			assertAll("size", 
					() -> assertEquals(0, size[0], "size[0] != 0"),
					() -> assertEquals(1, size[1], "size[1] != 1"),
					() -> assertEquals(0, size[2], "size[2] != 0")
			);
		}
		
		@DisplayName("in third stack must peek and let the item")
		@Test
		public void case_three() {
			multiStack.push(2, "A");
			
			String item = multiStack.peek(2);
			
			assertEquals("A", item);
			
			String[] repository = getRepositoryInMultiStack();
			assertEquals("A", repository[10]);
			
			int[] size = getSizeInMultiStack();
			assertAll("size", 
					() -> assertEquals(0, size[0], "size[0] != 0"),
					() -> assertEquals(0, size[1], "size[1] != 0"),
					() -> assertEquals(1, size[2], "size[2] != 1")
			);
		}
		
		@DisplayName("in first empty stack must throw error")
		@Test
		public void case_four() {
			assertThrows(EmptyStackException.class, () -> multiStack.pop(0));
		}
		
		@DisplayName("in second empty stack must throw error")
		@Test
		public void case_five() {
			assertThrows(EmptyStackException.class, () -> multiStack.pop(1));
		}
		
		@DisplayName("in third empty stack must throw error")
		@Test
		public void case_six() {
			assertThrows(EmptyStackException.class, () -> multiStack.pop(2));
		}
	}

	private String[] getRepositoryInMultiStack() {
		return (String[]) ReflectionTestUtils.getField(multiStack, "repository");
	}

	private int[] getSizeInMultiStack() {
		return (int[]) ReflectionTestUtils.getField(multiStack, "size");
	}
	
}
