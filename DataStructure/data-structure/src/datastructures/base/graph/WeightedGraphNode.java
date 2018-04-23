package datastructures.base.graph;

import java.util.LinkedHashSet;
import java.util.Set;

public class WeightedGraphNode {

	private int index;
	private Set<WeightedGraphNode> edges;
	private int weight;
	
	public WeightedGraphNode(int index, int weight) {
		this.index = index;
		this.edges = new LinkedHashSet<WeightedGraphNode>();
		this.weight = weight;
	}

	public int getIndex() {
		return index;
	}

	public void addEdge(WeightedGraphNode destination) {
		this.edges.add(destination);
	}
	
	public Set<WeightedGraphNode> getEdges() {
		return edges;
	}
	
	public int getWeight() {
		return weight;
	}
	
}
