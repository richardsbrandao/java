package datastructures.graphs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.graph.GraphType;
import datastructures.base.graph.WeightedGraphNode;

public class AdjacencyWeightedSetGraphTest {

	@Test
	public void when_add_edge_with_correct_coordinates_in_directed_graph_must_add_only_source_to_destination() {
		AdjacencyWeightedSetGraph graph = new AdjacencyWeightedSetGraph(GraphType.DIRECTED, 5);
		graph.addEdge(3, 1, 10);
		
		List<WeightedGraphNode> vertices = getGraphVertices(graph);
		
		assertEquals(3, vertices.get(3).getIndex());
		Set<WeightedGraphNode> edges = vertices.get(3).getEdges();
		assertEquals(1, edges.size());
		assertEquals(10, ((WeightedGraphNode) edges.toArray()[0]).getWeight());
		assertEquals(1, ((WeightedGraphNode) edges.toArray()[0]).getIndex());
		
		assertEquals(1, vertices.get(1).getIndex());
		assertEquals(10, ((WeightedGraphNode) edges.toArray()[0]).getWeight());
		assertTrue(vertices.get(1).getEdges().isEmpty());
	}
	
	@Test
	public void when_add_edge_with_correct_coordinates_in_undirect_graph_must_add_edge_in_both_directions() {
		AdjacencyWeightedSetGraph graph = new AdjacencyWeightedSetGraph(GraphType.UNDIRECTED, 5);
		graph.addEdge(3, 1, 10);

		List<WeightedGraphNode> vertices = getGraphVertices(graph);
		
		assertEquals(3, vertices.get(3).getIndex());
		Set<WeightedGraphNode> edges = vertices.get(3).getEdges();
		assertEquals(1, edges.size());
		assertEquals(10, ((WeightedGraphNode) edges.toArray()[0]).getWeight());
		assertEquals(1, ((WeightedGraphNode) edges.toArray()[0]).getIndex());
		
		assertEquals(1, vertices.get(1).getIndex());
		edges = vertices.get(1).getEdges();
		assertEquals(1, edges.size());
		assertEquals(10, ((WeightedGraphNode) edges.toArray()[0]).getWeight());
		assertEquals(3, ((WeightedGraphNode) edges.toArray()[0]).getIndex());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void when_add_edge_with_wrong_coordinates_must_throw_error() {
		AdjacencyWeightedSetGraph graph = new AdjacencyWeightedSetGraph(GraphType.DIRECTED, 5);
		graph.addEdge(1, 8, 10);
	}
	
	@Test
	public void when_shortest_path_with_directed_graph_must_execute_dijstra_algorithim_correctlt() {
		// data:image/png;base64,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
		AdjacencyWeightedSetGraph graph = getDirectedGraph();
		
		assertEquals(new Integer(0), graph.shortestPath(0, 0));
		assertEquals(new Integer(10), graph.shortestPath(0, 4));
		assertEquals(new Integer(15), graph.shortestPath(1, 0));
		assertEquals(new Integer(20), graph.shortestPath(3, 5));
	}
	
	@Test
	public void when_shortest_path_with_undirected_graph_must_execute_dijstra_algorithim_correctlt() {
		// http://article.sciencepublishinggroup.com/journal/149/149_340077/image004.jpg
		AdjacencyWeightedSetGraph graph = getUndirectedGraph();
		
		assertEquals(new Integer(0), graph.shortestPath(3, 3));
		assertEquals(new Integer(9), graph.shortestPath(3, 2));
		assertEquals(new Integer(15), graph.shortestPath(5, 0));
		assertEquals(new Integer(9), graph.shortestPath(4, 0));
	}
	
	private AdjacencyWeightedSetGraph getDirectedGraph() {
		AdjacencyWeightedSetGraph graph = new AdjacencyWeightedSetGraph(GraphType.DIRECTED, 6);
		graph.addEdge(0, 1, 5);
		graph.addEdge(0, 2, 20);
		graph.addEdge(1, 2, 15);
		graph.addEdge(1, 4, 5);
		graph.addEdge(2, 3, 35);
		graph.addEdge(2, 4, 25);
		graph.addEdge(3, 0, 3);
		graph.addEdge(4, 3, 7);
		graph.addEdge(4, 5, 7);
		graph.addEdge(5, 1, 8);
		return graph;
	}

	private AdjacencyWeightedSetGraph getUndirectedGraph() {
		AdjacencyWeightedSetGraph graph = new AdjacencyWeightedSetGraph(GraphType.UNDIRECTED, 6);
		graph.addEdge(0, 1, 5);
		graph.addEdge(0, 2, 6);
		graph.addEdge(1, 2, 8);
		graph.addEdge(1, 3, 3);
		graph.addEdge(1, 4, 4);
		graph.addEdge(2, 4, 6);
		graph.addEdge(3, 4, 3);
		graph.addEdge(3, 5, 7);
		graph.addEdge(4, 5, 7);
		return graph;
	}
	
	@SuppressWarnings("unchecked")
	private List<WeightedGraphNode> getGraphVertices(AdjacencyWeightedSetGraph graph) {
		return (List<WeightedGraphNode>) ReflectionTestUtils.getField(graph, "vertices");
	}
}
