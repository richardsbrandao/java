package datastructures.graphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import datastructures.base.graph.Graph;
import datastructures.base.graph.GraphNode;
import datastructures.base.graph.GraphType;

public class AdjacencySetGraph implements Graph {

	private GraphType type;
	private List<GraphNode> vertices = new ArrayList<GraphNode>();

	public AdjacencySetGraph(GraphType type) {
		this(type, DEFAULT_SIZE);
	}
	
	public AdjacencySetGraph(GraphType type, Integer defaultSize) {
		this.type = type;
		for(int i = 0; i < defaultSize; i++) {
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
		if(v1 < 0 || v2 < 0 || v1 >= this.vertices.size() || v2 >= this.vertices.size())
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
		List<Integer> collectedElements = new ArrayList<Integer>();
		int[] visited = new int[vertices.size()];
		
		breadthFirst(root, collectedElements, visited);
		
		for(int i = 0; i < vertices.size(); i++) {
			breadthFirst(i, collectedElements, visited);
		}
		
		return collectedElements;
	}

	private void breadthFirst(int root, List<Integer> collectedElements, int[] visited) {
		Deque<GraphNode> queue = new LinkedList<GraphNode>();
		queue.addLast(vertices.get(root));
		
		while(!queue.isEmpty()) {
			GraphNode graphNode = queue.remove();
			if(visited[graphNode.getIndex()] == 1) {
				continue;
			}
			
			visited[graphNode.getIndex()] = 1;
		
			collectedElements.add(graphNode.getIndex());
			
			for( GraphNode adjacencyNodes : graphNode.getEdges() ) {
				if( visited[adjacencyNodes.getIndex()] == 0 ) {
					queue.addLast(adjacencyNodes);
				}
			}
		}
	}

	@Override
	public List<Integer> depthFirst(int root) {
		int[] visited = new int[vertices.size()];
		List<Integer> collectedElements = new ArrayList<Integer>();
		depthFirst(root, collectedElements, visited);
		for(int i = 0; i < vertices.size(); i++) {
			depthFirst(i, collectedElements, visited);
		}
		return collectedElements;
	}

	private List<Integer> depthFirst(int root, List<Integer> collectedElements, int[] visited) {
		GraphNode graphNode = vertices.get(root);
		if(visited[graphNode.getIndex()] == 1) {
			return collectedElements;
		}

		visited[graphNode.getIndex()] = 1;
		
		for( GraphNode adjacencyNodes : graphNode.getEdges() ) {
			if( visited[adjacencyNodes.getIndex()] == 0 ) {
				depthFirst(adjacencyNodes.getIndex(), collectedElements, visited);
			}
		}
		
		collectedElements.add(graphNode.getIndex());
		return collectedElements;
	}

	@Override
	public boolean hasCycle() {
		if( checkIfCycleExists(0, new Stack<Integer>(), new HashSet<Integer>()) ) {
			return true;
		}
		
		for(int i = 1; i < vertices.size(); i++) {
			if( checkIfCycleExists(i, new Stack<Integer>(), new HashSet<Integer>()) ) {
				return true;
			}
		}
		
		return false;
	}

	private boolean checkIfCycleExists(int element, Stack<Integer> visited, Set<Integer> cycle) {
		GraphNode graphNode = vertices.get(element);
		if(visited.contains(graphNode.getIndex())) {
			return cycle.contains(graphNode.getIndex());
		}
		
		cycle.add(graphNode.getIndex());
		
		for(GraphNode neighbour : graphNode.getEdges()) {
			if(!visited.isEmpty() && visited.peek().equals(neighbour.getIndex())) {
				continue;
			}
			
			visited.add(graphNode.getIndex());
			if( checkIfCycleExists(neighbour.getIndex(), visited, cycle) ) {
				return true;
			}
			visited.pop();
		}
		
		cycle.remove(graphNode.getIndex());
		return false;
	}

	@Override
	public boolean isConnected() {
		return false;
	}

}
