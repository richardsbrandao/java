package algorithm.cases.elevator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElevatorStats {

	private int weightLimit;
	private int peopleLimit;
	
	private Integer trips = 0;
	private int currentWeight = 0;
	private int currentPeople = 0;
	private Set<Integer> stops = new HashSet<Integer>();
	
	private List<Integer> weight = new ArrayList<Integer>();
	private List<Integer> people = new ArrayList<Integer>();
	private List<Integer> stopsByTrip = new ArrayList<Integer>();

	public ElevatorStats(int weightLimit, int peopleLimit) {
		this.weightLimit = weightLimit;
		this.peopleLimit = peopleLimit;
	}

	public int people(int tripId) {
		return people.size() > tripId ? people.get(tripId) : currentPeople;
	}

	public int stops(int tripId) {
		return stopsByTrip.size() > tripId ? stopsByTrip.get(tripId) : stops.size();
	}

	public int trips() {
		return trips;
	}

	public int weights(int tripId) {
		return weight.size() > tripId ? weight.get(tripId) : currentWeight;
	}

	public void add(Person person) {
		newTripIfReachedTheLimits(person);
		
		currentWeight += person.getWeight();
		currentPeople++;
		if(trips == 0) {
			trips = 1;
		}
		stops.add(person.getDestination());
	}

	private void newTripIfReachedTheLimits(Person person) {
		if((currentWeight + person.getWeight()) > weightLimit || currentPeople == peopleLimit ) {
			trips++;
			closeTripStats();
			restartTripCounters();
		}
	}

	private void closeTripStats() {
		weight.add(currentWeight);
		people.add(currentPeople);
		stopsByTrip.add(stops.size());
	}

	private void restartTripCounters() {
		currentWeight = 0;
		currentPeople = 0;
		stops = new HashSet<Integer>();
	}

}
