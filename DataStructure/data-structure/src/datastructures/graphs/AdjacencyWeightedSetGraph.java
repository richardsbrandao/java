package datastructures.graphs;

import java.util.ArrayList;
import java.util.List;

import datastructures.base.graph.DistanceInfo;
import datastructures.base.graph.GraphType;
import datastructures.base.graph.WeightedGraph;
import datastructures.base.graph.WeightedGraphNode;
import datastructures.heaps.MinHeap;

public class AdjacencyWeightedSetGraph implements WeightedGraph {
	
	private GraphType type;
	private List<WeightedGraphNode> vertices = new ArrayList<WeightedGraphNode>();

	public AdjacencyWeightedSetGraph(GraphType type, Integer size) {
		this.type = type;
		for(int i = 0; i < size; i++) {
			this.vertices.add(new WeightedGraphNode(i, 0));
		}
	}
	
	@Override
	public void addEdge(int source, int destination, int weight) {
		throwErrorIfVertexIsWrong(source, destination);
		this.vertices.get(source).addEdge(new WeightedGraphNode(destination, weight));
		if(type.equals(GraphType.UNDIRECTED)) {
			this.vertices.get(destination).addEdge(new WeightedGraphNode(source, weight));
		}
	}
	
	private void throwErrorIfVertexIsWrong(int source, int destination) {
		if(source < 0 || destination < 0 || source >= this.vertices.size() || destination >= this.vertices.size())
			throw new IllegalArgumentException();
	}

	@Override
	public Integer shortestPath(int source, int destination) {
		int[] visisted = new int[vertices.size()];
		MinHeap<DistanceInfo> queue = new MinHeap<>(DistanceInfo.class);
		List<DistanceInfo> distanceTable = buildDistanceTable(source);
		
		queue.add(distanceTable.get(source));
		
		while(minQueueIsNotEmpty(queue)) {
			DistanceInfo nextPath = queue.remove();
			Integer vertex = nextPath.getVertex();
			DistanceInfo sourceInfo = distanceTable.get(vertex);
			
			visisted[vertex] = 1;
			
			for(WeightedGraphNode neighbor : vertices.get(vertex).getEdges()) {
				if(visisted[neighbor.getIndex()] != 1) {
					queue.add(distanceTable.get(neighbor.getIndex()));
				}
				distanceTable.get(neighbor.getIndex()).updateDistance(vertex, neighbor.getWeight(), sourceInfo);
			}
		}
		
		return distanceTable.get(destination).getDistance();
	}

	private boolean minQueueIsNotEmpty(MinHeap<DistanceInfo> queue) {
		return queue.size() > 0;
	}

	private List<DistanceInfo> buildDistanceTable(int source) {
		List<DistanceInfo> distanceTable = new ArrayList<DistanceInfo>();
		for(int i = 0; i < vertices.size(); i++) {
			DistanceInfo distance = source != i ? DistanceInfo.forDestination(i) : DistanceInfo.forSelf(i);
			distanceTable.add(distance);
		}
		return distanceTable;
	}

}
