package datastructures.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import datastructures.base.graph.Graph;
import datastructures.base.graph.GraphNode;
import datastructures.base.graph.GraphType;

public class AdjacencySetGraph implements Graph {

	private GraphType type;
	private List<GraphNode> vertices = new ArrayList<GraphNode>();

	public AdjacencySetGraph(GraphType type) {
		this.type = type;
		for(int i = 0; i < DEFAULT_SIZE; i++) {
			this.vertices.add(new GraphNode(i));
		}
	}

	@Override
	public void addEdge(int v1, int v2) {
		throwErrorIfVertexIsWrong(v1, v2);
		
		vertices.get(v1).addEdge(vertices.get(v2));
		if(type == GraphType.UNDIRECTED) {
			vertices.get(v2).addEdge(vertices.get(v1));
		}
	}

	private void throwErrorIfVertexIsWrong(int v1, int v2) {
		if(v1 < 0 || v2 < 0 || v1 >= Graph.DEFAULT_SIZE || v2 >= Graph.DEFAULT_SIZE)
			throw new IllegalArgumentException();
	}

	@Override
	public List<Integer> getAdjacentVertices(int v) {
		return vertices.get(v).getEdges().stream()
					.map(destination -> destination.getIndex())
					.collect(Collectors.toList());
	}	

	@Override
	public List<Integer> breadthFirst(int root) {
		List<Integer> breadthFirst = new ArrayList<Integer>();
		int[] visited = new int[vertices.size()];
		
//		while(!queue.isEmpty()) {
//		}
		
		return breadthFirst;
	}

	@Override
	public List<Integer> deathFirst(int root) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCycle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		return false;
	}

}
