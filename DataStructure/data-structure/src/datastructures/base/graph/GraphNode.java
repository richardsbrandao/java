package datastructures.base.graph;

import java.util.LinkedHashSet;
import java.util.Set;

public class GraphNode {

	private int index;
	private Set<GraphNode> edges;
	
	public GraphNode(int index) {
		this.index = index;
		this.edges = new LinkedHashSet<GraphNode>();
	}

	public int getIndex() {
		return index;
	}

	public void addEdge(GraphNode destination) {
		this.edges.add(destination);
	}
	
	public Set<GraphNode> getEdges() {
		return edges;
	}

}
