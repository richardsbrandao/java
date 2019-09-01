package queue;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import queue.shelter.Animal;
import queue.shelter.Cat;
import queue.shelter.Dog;

@DisplayName("AnimalShelter")
public class AnimalShelterQueueTest {

	@Nested
	@DisplayName("when enqueue ")
	class Enqueue {
		@DisplayName("with cat must enqueue")
		@Test
		public void case_one() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			queue.enqueue(new Cat("Meowth"));
			
			LinkedList<Cat> catsQueue = getCats(queue);
			
			assertAll("catOne",
					() -> assertEquals("Meowth", catsQueue.get(0).getName()),
					() -> assertEquals(0, catsQueue.get(0).getOrder())
			);
		}
		
		@DisplayName("with dog must enqueue")
		@Test
		public void case_two() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			queue.enqueue(new Dog("Billy"));
			
			LinkedList<Dog> dogsQueue = getDogs(queue);
			
			assertAll("dogOne",
					() -> assertEquals("Billy", dogsQueue.get(0).getName()),
					() -> assertEquals(0, dogsQueue.get(0).getOrder())
			);
		}
		
		@DisplayName("with cats and dogs must enqueue")
		@Test
		public void case_three() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			queue.enqueue(new Dog("Billy"));
			queue.enqueue(new Cat("Meowth"));
			queue.enqueue(new Cat("Persian"));
			queue.enqueue(new Dog("Max"));
			queue.enqueue(new Dog("Buddy"));
			
			LinkedList<Cat> catsQueue = getCats(queue);
			
			assertAll("catOne",
					() -> assertEquals("Meowth", catsQueue.get(0).getName()),
					() -> assertEquals(1, catsQueue.get(0).getOrder())
			);
			
			assertAll("catTwo",
					() -> assertEquals("Persian", catsQueue.get(1).getName()),
					() -> assertEquals(2, catsQueue.get(1).getOrder())
			);
			
			LinkedList<Dog> dogsQueue = getDogs(queue);
			
			assertAll("dogOne",
					() -> assertEquals("Billy", dogsQueue.get(0).getName()),
					() -> assertEquals(0, dogsQueue.get(0).getOrder())
			);
			
			assertAll("dogTwo",
					() -> assertEquals("Max", dogsQueue.get(1).getName()),
					() -> assertEquals(3, dogsQueue.get(1).getOrder())
			);
			
			assertAll("dogThree",
					() -> assertEquals("Buddy", dogsQueue.get(2).getName()),
					() -> assertEquals(4, dogsQueue.get(2).getOrder())
			);
		}
	}

	@Nested
	@DisplayName("when dequeue cat ")
	class DequeueCat {
		@DisplayName("with empty cat list must return null")
		@Test
		public void case_one() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			
			assertNull(queue.dequeueCat());
		}
		@DisplayName("with single element cat list must return the cat and clean the list")
		@Test
		public void case_two() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			queue.enqueue(new Cat("Meowth"));
			
			Cat cat = queue.dequeueCat();
			assertEquals("Meowth", cat.getName());
			assertEquals(0, cat.getOrder());
			
			LinkedList<Cat> cats = getCats(queue);
			assertTrue(cats.isEmpty());
		}
		@DisplayName("with more than one element must return the cat and let the others waiting")
		@Test
		public void case_three() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			queue.enqueue(new Cat("Meowth"));
			queue.enqueue(new Cat("Persian"));
			
			Cat cat = queue.dequeueCat();
			assertEquals("Meowth", cat.getName());
			assertEquals(0, cat.getOrder());
			
			LinkedList<Cat> catsQueue = getCats(queue);
			assertAll("catTwo",
					() -> assertEquals("Persian", catsQueue.get(0).getName()),
					() -> assertEquals(1, catsQueue.get(0).getOrder())
			);
		}
		@DisplayName("with twice must dequeue elements by order")
		@Test
		public void case_four() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			queue.enqueue(new Cat("Meowth"));
			queue.enqueue(new Cat("Persian"));
			queue.enqueue(new Cat("Bella"));
			queue.enqueue(new Cat("Luna"));
			
			Cat cat = queue.dequeueCat();
			assertEquals("Meowth", cat.getName());
			assertEquals(0, cat.getOrder());
			
			cat = queue.dequeueCat();
			assertEquals("Persian", cat.getName());
			assertEquals(1, cat.getOrder());
			
			LinkedList<Cat> catsQueue = getCats(queue);
			
			assertAll("catTwo",
					() -> assertEquals("Bella", catsQueue.get(0).getName()),
					() -> assertEquals(2, catsQueue.get(0).getOrder())
			);
			
			assertAll("catThree",
					() -> assertEquals("Luna", catsQueue.get(1).getName()),
					() -> assertEquals(3, catsQueue.get(1).getOrder())
			);
		}
	}
	
	@Nested
	@DisplayName("when dequeue dog ")
	class DequeueDog {
		@DisplayName("with empty dog list must return null")
		@Test
		public void case_one() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			
			assertNull(queue.dequeueCat());
		}
		@DisplayName("with single element dog list must return the cat and clean the list")
		@Test
		public void case_two() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			queue.enqueue(new Dog("Billy"));
			
			Dog dog = queue.dequeueDog();
			assertEquals("Billy", dog.getName());
			assertEquals(0, dog.getOrder());
			
			LinkedList<Dog> dogs = getDogs(queue);
			assertTrue(dogs.isEmpty());
		}
		@DisplayName("with more than one element must return the dog and let the others waiting")
		@Test
		public void case_three() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			queue.enqueue(new Dog("Billy"));
			queue.enqueue(new Dog("Charlie"));
			
			Dog dog = queue.dequeueDog();
			assertEquals("Billy", dog.getName());
			assertEquals(0, dog.getOrder());
			
			LinkedList<Dog> dogsQueue = getDogs(queue);
			assertAll("dogTwo",
					() -> assertEquals("Charlie", dogsQueue.get(0).getName()),
					() -> assertEquals(1, dogsQueue.get(0).getOrder())
			);
		}
		@DisplayName("with twice must dequeue elements by order")
		@Test
		public void case_four() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			queue.enqueue(new Dog("Billy"));
			queue.enqueue(new Dog("Charlie"));
			queue.enqueue(new Dog("Max"));
			queue.enqueue(new Dog("Buddy"));
			
			Dog dog = queue.dequeueDog();
			assertEquals("Billy", dog.getName());
			assertEquals(0, dog.getOrder());
			
			dog = queue.dequeueDog();
			assertEquals("Charlie", dog.getName());
			assertEquals(1, dog.getOrder());
			
			LinkedList<Dog> dogsQueue = getDogs(queue);
			
			assertAll("dogThree",
					() -> assertEquals("Max", dogsQueue.get(0).getName()),
					() -> assertEquals(2, dogsQueue.get(0).getOrder())
			);
			
			assertAll("dogFour",
					() -> assertEquals("Buddy", dogsQueue.get(1).getName()),
					() -> assertEquals(3, dogsQueue.get(1).getOrder())
			);
		}
	}
	
	@Nested
	@DisplayName("when dequeue any ")
	class DequeueAny {
		@DisplayName("with empty queue must return null")
		public void case_one() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			
			assertNull(queue.dequeueAny());
		}
		@DisplayName("with only cats must return first cat")
		@Test
		public void case_two() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			queue.enqueue(new Cat("Meowth"));
			
			Animal animal = queue.dequeueAny();
			
			assertTrue(animal.isCat());
			assertEquals("Meowth", animal.getName());
			assertEquals(0, animal.getOrder());
		}
		@DisplayName("with only dogs must return first dog")
		@Test
		public void case_three() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			queue.enqueue(new Dog("Billy"));
			
			Animal animal = queue.dequeueAny();
			
			assertFalse(animal.isCat());
			assertEquals("Billy", animal.getName());
			assertEquals(0, animal.getOrder());
		}
		@DisplayName("with multiple cats and dogs must get for arrival order")
		@Test
		public void case_four() {
			AnimalShelterQueue queue = new AnimalShelterQueue();
			queue.enqueue(new Dog("Billy"));
			queue.enqueue(new Cat("Meowth"));
			queue.enqueue(new Cat("Persian"));
			queue.enqueue(new Dog("Charlie"));
			
			Animal animal = queue.dequeueAny();
			assertFalse(animal.isCat());
			assertEquals("Billy", animal.getName());
			assertEquals(0, animal.getOrder());
			
			animal = queue.dequeueAny();
			assertTrue(animal.isCat());
			assertEquals("Meowth", animal.getName());
			assertEquals(1, animal.getOrder());
			
			animal = queue.dequeueAny();
			assertTrue(animal.isCat());
			assertEquals("Persian", animal.getName());
			assertEquals(2, animal.getOrder());
			
			animal = queue.dequeueAny();
			assertFalse(animal.isCat());
			assertEquals("Charlie", animal.getName());
			assertEquals(3, animal.getOrder());
		}
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<Cat> getCats(AnimalShelterQueue queue) {
		return (LinkedList<Cat>) ReflectionTestUtils.getField(queue, "cats");
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<Dog> getDogs(AnimalShelterQueue queue) {
		return (LinkedList<Dog>) ReflectionTestUtils.getField(queue, "dogs");
	}
	
}
