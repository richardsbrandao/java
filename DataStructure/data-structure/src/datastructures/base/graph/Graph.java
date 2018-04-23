package datastructures.base.graph;

import java.util.List;

public interface Graph {

	public static final int DEFAULT_SIZE = 5;
	
	public void addEdge(int v1, int v2);
	public List<Integer> getAdjacentVertices(int v);
	public List<Integer> breadthFirst(int root);
	public List<Integer> depthFirst(int root);
	public boolean hasCycle();
	public boolean isConnected();
	public Integer shortestPath(int source, int destination);
	
	//paths ..
	
}
