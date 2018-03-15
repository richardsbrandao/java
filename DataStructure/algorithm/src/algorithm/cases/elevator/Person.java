package algorithm.cases.elevator;

public class Person {

	private String name;
	private int weight;
	private int destination;

	public Person(String name, int weight, int destination) {
		this.name = name;
		this.weight = weight;
		this.destination = destination;
	}

	public int getWeight() {
		return weight;
	}

	public Integer getDestination() {
		return destination;
	}

}
