package algorithm.cases.drones;

public class Drone implements Comparable<Drone> {

	private Integer id;
	private Integer flightRange;

	public Drone(Integer id, Integer flightRange) {
		this.id = id;
		this.flightRange = flightRange;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFlightRange() {
		return flightRange;
	}
	public void setFlightRange(Integer flightRange) {
		this.flightRange = flightRange;
	}

	@Override
	public int compareTo(Drone other) {
		if(flightRange == other.flightRange) {
			return 0;
		}
		return flightRange > other.flightRange ? 1 : -1;
	}

	@Override
	public String toString() {
		return "Drone [id=" + id + ", flightRange=" + flightRange + "]";
	}
	
}
