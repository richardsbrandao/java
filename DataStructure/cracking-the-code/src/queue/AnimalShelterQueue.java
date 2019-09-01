package queue;

import java.util.Deque;
import java.util.LinkedList;

import queue.shelter.Animal;
import queue.shelter.Cat;
import queue.shelter.Dog;

public class AnimalShelterQueue {

	private Deque<Cat> cats = new LinkedList<>();
	private Deque<Dog> dogs = new LinkedList<>();
	
	private int order;
	
	public void enqueue(Animal animal) {
		animal.setOrder(order++);
		if(animal.isCat()) {
			this.cats.addLast((Cat) animal);
		} else {
			this.dogs.addLast((Dog) animal);
		}
	}
	
	public Animal dequeueAny() {
		if(hasOnlyCats()) {
			return dequeueCat();
		} 
		
		if(hasOnlyDogs()) {
			return dequeueDog();
		} 
		
		return getAnimalByArrival();
	}
	
	private Animal getAnimalByArrival() {
		Cat cat = this.cats.peek();
		Dog dog = this.dogs.peek();
		return cat.getOrder() < dog.getOrder() ? dequeueCat() : dequeueDog();
	}

	private boolean hasOnlyDogs() {
		return !this.dogs.isEmpty() && this.cats.isEmpty();
	}

	private boolean hasOnlyCats() {
		return !this.cats.isEmpty() && this.dogs.isEmpty();
	}

	public Cat dequeueCat() {
		if(this.cats.isEmpty()) {
			return null;
		}
		return this.cats.pop();
	}
	
	public Dog dequeueDog() {
		if(this.dogs.isEmpty()) {
			return null;
		}
		return this.dogs.pop();
	}
	
}
