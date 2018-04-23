package datastructures.base.graph;

public interface WeightedGraph {

	public static final int DEFAULT_SIZE = 5;
	
	void addEdge(int source, int destination, int weight);
	Integer shortestPath(int source, int destination);
	
}
