package algorithm.cases.elevator;

import java.util.LinkedList;

public class ElevatorQueue {

	private LinkedList<Person> queue = new LinkedList<>();
	private ElevatorStats stats = new ElevatorStats(750, 10);
	
	public void push(Person person) {
		queue.push(person);
		stats.add(person);
	}

	public ElevatorStats statsForThisQueue() {
		return stats;
	}

}
