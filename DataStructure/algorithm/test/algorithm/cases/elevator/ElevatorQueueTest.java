package algorithm.cases.elevator;

import static org.junit.Assert.*;

import org.junit.Test;

public class ElevatorQueueTest {

	@Test
	public void when_create_with_one_person_within_weight_must_program_for_one_trip_and_one_stop() {
		ElevatorQueue queue = new ElevatorQueue();
		queue.push(new Person("Richard", 55, 12));
		
		ElevatorStats stats = queue.statsForThisQueue();
		
		assertEquals(1, stats.people(0));
		assertEquals(55, stats.weights(0));
		assertEquals(1, stats.trips());
		assertEquals(1, stats.stops(0));
	}
	
	@Test
	public void when_create_with_two_people_within_weight_must_program_for_one_trip_and_two_stops() {
		ElevatorQueue queue = new ElevatorQueue();
		queue.push(new Person("Richard", 55, 12));
		queue.push(new Person("Steve", 60, 5));
		
		ElevatorStats stats = queue.statsForThisQueue();
		
		assertEquals(2, stats.people(0));
		assertEquals(2, stats.stops(0));
		assertEquals(1, stats.trips());
		assertEquals(115, stats.weights(0));
	}
	
	@Test
	public void when_create_with_two_people_within_weight_going_to_the_same_floor_must_program_for_one_trip_and_one_stop() {
		ElevatorQueue queue = new ElevatorQueue();
		queue.push(new Person("Richard", 55, 12));
		queue.push(new Person("Steve", 70, 12));
		
		ElevatorStats stats = queue.statsForThisQueue();
		
		assertEquals(2, stats.people(0));
		assertEquals(1, stats.stops(0));
		assertEquals(1, stats.trips());
		assertEquals(125, stats.weights(0));
	}
	
	@Test
	public void case_one_with_more_than_10_people_even_within_weight() {
		ElevatorQueue queue = new ElevatorQueue();
		queue.push(new Person("Person 1", 20, 11));
		queue.push(new Person("Person 2", 20, 5));
		queue.push(new Person("Person 3", 20, 2));
		queue.push(new Person("Person 4", 20, 13));
		queue.push(new Person("Person 5", 20, 2));
		queue.push(new Person("Person 6", 20, 2));
		queue.push(new Person("Person 7", 20, 10));
		queue.push(new Person("Person 8", 20, 6));
		queue.push(new Person("Person 9", 20, 5));
		queue.push(new Person("Person 10", 20, 4));
		queue.push(new Person("Person 11", 20, 8));
		
		ElevatorStats stats = queue.statsForThisQueue();
		
		assertEquals(10, stats.people(0));
		assertEquals(1, stats.people(1));
		assertEquals(7, stats.stops(0));
		assertEquals(1, stats.stops(1));
		assertEquals(2, stats.trips());
		assertEquals(200, stats.weights(0));
		assertEquals(20, stats.weights(1));
	}
	
	@Test
	public void case_two_with_less_than_10_people_out_of_weight() {
		ElevatorQueue queue = new ElevatorQueue();
		queue.push(new Person("Person 1", 130, 11));
		queue.push(new Person("Person 2", 130, 5));
		queue.push(new Person("Person 3", 130, 2));
		queue.push(new Person("Person 4", 130, 13));
		queue.push(new Person("Person 5", 130, 2));
		queue.push(new Person("Person 6", 130, 2));
		queue.push(new Person("Person 7", 130, 4));

		ElevatorStats stats = queue.statsForThisQueue();
		
		assertEquals(5, stats.people(0));
		assertEquals(2, stats.people(1));
		assertEquals(4, stats.stops(0));
		assertEquals(2, stats.stops(1));
		assertEquals(2, stats.trips());
		assertEquals(650, stats.weights(0));
		assertEquals(260, stats.weights(1));
	}
	
//	public void case_three_with_3_trips_when_people_and_weight_exceed_the_limits() {
//		
//	}
}
