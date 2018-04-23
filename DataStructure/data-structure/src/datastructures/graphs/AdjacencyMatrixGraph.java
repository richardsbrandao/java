package datastructures.graphs;


import java.util.ArrayList;
import java.util.List;

import datastructures.base.graph.Graph;
import datastructures.base.graph.GraphType;

public class AdjacencyMatrixGraph implements Graph {
	
	private int[][] representation;
	private GraphType type;

	public AdjacencyMatrixGraph(GraphType type) {
		this.type = type;
		this.representation = new int[DEFAULT_SIZE][DEFAULT_SIZE];
	}

	@Override
	public void addEdge(int v1, int v2) {
		throwErrorIfVertexIsWrong(v1, v2);
		
		representation[v1][v2] = 1;
		if(type.equals(GraphType.UNDIRECTED)) {
			representation[v2][v1] = 1;
		}
	}

	private void throwErrorIfVertexIsWrong(int v1, int v2) {
		if(v1 < 0 || v2 < 0 || v1 >= DEFAULT_SIZE || v2 >= DEFAULT_SIZE) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public List<Integer> getAdjacentVertices(int v) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < DEFAULT_SIZE; i++) {
			if(representation[v][i] == 1)
				list.add(i);
		}
		return list;
	}

	@Override
	public List<Integer> breadthFirst(int root) {
		return null;
	}

	@Override
	public List<Integer> depthFirst(int root) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCycle() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer shortestPath(int source, int destination) {
		// TODO Auto-generated method stub
		return 0;
	}
}
