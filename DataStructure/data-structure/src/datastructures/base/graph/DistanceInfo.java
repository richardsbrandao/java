package datastructures.base.graph;

public class DistanceInfo implements Comparable<DistanceInfo> {

	private int vertex;
	private int distance;
	private int lastVertex;
	
	private DistanceInfo(int vertex, int distance) {
		this.vertex = vertex;
		this.distance = distance;
		this.lastVertex = distance != 1 ? -1 : vertex;
	}
	
	public void setLastVertex(int lastVertex) {
		this.lastVertex = lastVertex;
	}

	public int getVertex() {
		return vertex;
	}

	public int getLastVertex() {
		return lastVertex;
	}

	public Integer getDistance() {
		return distance != Integer.MAX_VALUE ? distance : -1;
	}
	
	public static DistanceInfo forSelf(int vertex) {
		return new DistanceInfo(vertex, 0);
	}

	public static DistanceInfo forDestination(int vertex) {
		return new DistanceInfo(vertex, Integer.MAX_VALUE);
	}

	@Override
	public String toString() {
		return "DistanceInfo [vertex=" + vertex + ", distance=" + distance + ", lastVertex=" + lastVertex + "]";
	}

	public void updateDistance(Integer lastVertex, Integer weight, DistanceInfo source) {
		this.lastVertex = lastVertex;
		Integer newDistance = source.getDistance() + weight;
		if(newDistance < this.distance) {
			this.distance = newDistance;
		}
	}

	public void updateDistance(Integer lastVertex, DistanceInfo source) {
		updateDistance(lastVertex, 1, source);
	}

	@Override
	public int compareTo(DistanceInfo o) {
		return this.distance > o.distance ? 1 : -1;
	}
	
}
