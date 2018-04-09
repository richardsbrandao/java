package datastructures.graphs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.graph.Graph;
import datastructures.base.graph.GraphNode;
import datastructures.base.graph.GraphType;

public class AdjacencySetGraphTest {

	@Test
	public void when_construct_with_directed_type_must_create_a_valid_graph_with_no_connections() {
		AdjacencySetGraph graph = new AdjacencySetGraph(GraphType.DIRECTED);
		
		GraphType type = getGraphType(graph);
		List<GraphNode> vertices = getGraphVertices(graph);
		
		assertEquals(GraphType.DIRECTED, type);
		assertEquals(Graph.DEFAULT_SIZE, vertices.size());
		for(int i = 0; i < AdjacencySetGraph.DEFAULT_SIZE; i++) {
			assertNotNull(vertices.get(i));
			assertEquals(i, vertices.get(i).getIndex());
			assertTrue(vertices.get(i).getEdges().isEmpty());
		}
	}
	
	@Test
	public void when_construct_with_undirected_type_must_create_a_valid_graph_with_no_connections() {
		AdjacencySetGraph graph = new AdjacencySetGraph(GraphType.UNDIRECTED);
		
		GraphType type = getGraphType(graph);
		List<GraphNode> vertices = getGraphVertices(graph);
		
		assertEquals(GraphType.UNDIRECTED, type);
		assertEquals(Graph.DEFAULT_SIZE, vertices.size());
		for(int i = 0; i < AdjacencySetGraph.DEFAULT_SIZE; i++) {
			assertNotNull(vertices.get(i));
			assertEquals(i, vertices.get(i).getIndex());
			assertTrue(vertices.get(i).getEdges().isEmpty());
		}
	}
	
	@Test
	public void when_add_edge_with_correct_coordinates_in_directed_graph_must_add_only_source_to_destination() {
		AdjacencySetGraph graph = new AdjacencySetGraph(GraphType.DIRECTED);
		graph.addEdge(3, 1);
		
		List<GraphNode> vertices = getGraphVertices(graph);
		
		assertEquals(3, vertices.get(3).getIndex());
		Set<GraphNode> edges = vertices.get(3).getEdges();
		assertEquals(1, edges.size());
		assertEquals(1, ((GraphNode) edges.toArray()[0]).getIndex());
		
		assertEquals(1, vertices.get(1).getIndex());
		assertTrue(vertices.get(1).getEdges().isEmpty());
	}
	
	@Test
	public void when_add_edge_with_correct_coordinates_in_undirect_graph_must_add_edge_in_both_directions() {
		AdjacencySetGraph graph = new AdjacencySetGraph(GraphType.UNDIRECTED);
		graph.addEdge(3, 1);

		List<GraphNode> vertices = getGraphVertices(graph);
		
		assertEquals(3, vertices.get(3).getIndex());
		Set<GraphNode> edges = vertices.get(3).getEdges();
		assertEquals(1, edges.size());
		assertEquals(1, ((GraphNode) edges.toArray()[0]).getIndex());
		
		assertEquals(1, vertices.get(1).getIndex());
		edges = vertices.get(1).getEdges();
		assertEquals(1, edges.size());
		assertEquals(3, ((GraphNode) edges.toArray()[0]).getIndex());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void when_add_edge_with_wrong_coordinates_must_throw_error() {
		AdjacencySetGraph graph = new AdjacencySetGraph(GraphType.DIRECTED);
		graph.addEdge(1, 8);
	}
	
	@Test
	public void when_get_adjacency_list_with_vertex_without_edges_must_return_empty_list() {
		AdjacencySetGraph graph = new AdjacencySetGraph(GraphType.DIRECTED);
		
		assertTrue(graph.getAdjacentVertices(3).isEmpty());
	}
	
	@Test
	public void when_get_adjacency_list_with_vertex_with_connections_in_directed_graph_must_return_a_list_with_all_edges_connected() {
		AdjacencySetGraph graph = new AdjacencySetGraph(GraphType.DIRECTED);
		graph.addEdge(2, 3);
		graph.addEdge(1, 2);
		graph.addEdge(0, 2);
		graph.addEdge(1, 4);
		
		List<Integer> adjacentVertices = graph.getAdjacentVertices(2);
		
		assertEquals(1, adjacentVertices.size());
		assertTrue(adjacentVertices.contains(3));
	}
	
	@Test
	public void when_get_adjacency_list_with_vertex_with_connections_in_undirected_graph_must_return_a_list_with_all_edges_connected() {
		AdjacencySetGraph graph = new AdjacencySetGraph(GraphType.UNDIRECTED);
		graph.addEdge(2, 3);
		graph.addEdge(1, 2);
		graph.addEdge(0, 2);
		graph.addEdge(1, 4);
		
		List<Integer> adjacentVertices = graph.getAdjacentVertices(2);
		
		assertEquals(3, adjacentVertices.size());
		assertTrue(adjacentVertices.contains(0));
		assertTrue(adjacentVertices.contains(1));
		assertTrue(adjacentVertices.contains(3));
	}
	
	@Test
	public void when_beadth_first_with_directed_and_connected_graph_must_return_all_correctly() {
		Graph graph = getDefaultGraph(GraphType.DIRECTED);
		List<Integer> breadthFirst = graph.breadthFirst(0);
		
		assertEquals(new Integer(0), breadthFirst.get(0));
		assertEquals(new Integer(4), breadthFirst.get(1));
		assertEquals(new Integer(1), breadthFirst.get(2));
		assertEquals(new Integer(3), breadthFirst.get(3));
		assertEquals(new Integer(2), breadthFirst.get(4));
	}
	
	@Test
	public void when_beadth_first_with_undirected_and_connected_graph_must_return_all_correctly() {
		Graph graph = getDefaultGraph(GraphType.UNDIRECTED);
		List<Integer> breadthFirst = graph.breadthFirst(0);
		
		assertEquals(new Integer(0), breadthFirst.get(0));
		assertEquals(new Integer(4), breadthFirst.get(1));
		assertEquals(new Integer(1), breadthFirst.get(2));
		assertEquals(new Integer(3), breadthFirst.get(3));
		assertEquals(new Integer(2), breadthFirst.get(4));
	}
	
	@Test
	public void when_beadth_first_with_other_root_undirected_and_connected_graph_must_return_all_correctly() {
		Graph graph = getDefaultGraph(GraphType.UNDIRECTED);
		List<Integer> breadthFirst = graph.breadthFirst(3);
		
		assertEquals(new Integer(3), breadthFirst.get(0));
		assertEquals(new Integer(1), breadthFirst.get(1));
		assertEquals(new Integer(2), breadthFirst.get(2));
		assertEquals(new Integer(4), breadthFirst.get(3));
		assertEquals(new Integer(0), breadthFirst.get(4));
	}
	
	@Test
	public void when_beadth_first_with_directed_and_unconnected_graph_must_return_all_correctly() {
		Graph graph = getDefaultUnconnectedGraph(GraphType.DIRECTED);
		List<Integer> breadthFirst = graph.breadthFirst(0);
		
		assertEquals(new Integer(0), breadthFirst.get(0));
		assertEquals(new Integer(4), breadthFirst.get(1));
		assertEquals(new Integer(1), breadthFirst.get(2));
		assertEquals(new Integer(3), breadthFirst.get(3));
		assertEquals(new Integer(2), breadthFirst.get(4));
		assertEquals(new Integer(5), breadthFirst.get(5));
		assertEquals(new Integer(6), breadthFirst.get(6));
	}

	@Test
	public void when_beadth_first_with_undirected_and_unconnected_graph_must_return_all_correctly() {
		Graph graph = getDefaultUnconnectedGraph(GraphType.UNDIRECTED);
		List<Integer> breadthFirst = graph.breadthFirst(0);
		
		assertEquals(new Integer(0), breadthFirst.get(0));
		assertEquals(new Integer(4), breadthFirst.get(1));
		assertEquals(new Integer(1), breadthFirst.get(2));
		assertEquals(new Integer(3), breadthFirst.get(3));
		assertEquals(new Integer(2), breadthFirst.get(4));
		assertEquals(new Integer(5), breadthFirst.get(5));
		assertEquals(new Integer(6), breadthFirst.get(6));
	}
	
	@Test
	public void when_depth_first_with_directed_and_connected_graph_must_return_all_correctly() {
		Graph graph = getDefaultGraph(GraphType.DIRECTED);
		List<Integer> depthFirst = graph.depthFirst(0);
		
		assertEquals(new Integer(4), depthFirst.get(0));
		assertEquals(new Integer(3), depthFirst.get(1));
		assertEquals(new Integer(2), depthFirst.get(2));
		assertEquals(new Integer(1), depthFirst.get(3));
		assertEquals(new Integer(0), depthFirst.get(4));
	}
	
	@Test
	public void when_depth_first_with_undirected_and_connected_graph_must_return_all_correctly() {
		Graph graph = getDefaultGraph(GraphType.UNDIRECTED);
		List<Integer> depthFirst = graph.depthFirst(0);
		
		assertEquals(new Integer(2), depthFirst.get(0));
		assertEquals(new Integer(3), depthFirst.get(1));
		assertEquals(new Integer(1), depthFirst.get(2));
		assertEquals(new Integer(4), depthFirst.get(3));
		assertEquals(new Integer(0), depthFirst.get(4));
	}
	
	@Test
	public void when_depth_first_with_other_root_undirected_and_connected_graph_must_return_all_correctly() {
		Graph graph = getDefaultGraph(GraphType.UNDIRECTED);
		List<Integer> depthFirst = graph.depthFirst(3);
		
		assertEquals(new Integer(4), depthFirst.get(0));
		assertEquals(new Integer(0), depthFirst.get(1));
		assertEquals(new Integer(2), depthFirst.get(2));
		assertEquals(new Integer(1), depthFirst.get(3));
		assertEquals(new Integer(3), depthFirst.get(4));
	}
	
	@Test
	public void when_depth_first_with_directed_and_unconnected_graph_must_return_all_correctly() {
		Graph graph = getDefaultUnconnectedGraph(GraphType.DIRECTED);
		List<Integer> depthFirst = graph.depthFirst(0);
		
		assertEquals(new Integer(4), depthFirst.get(0));
		assertEquals(new Integer(3), depthFirst.get(1));
		assertEquals(new Integer(2), depthFirst.get(2));
		assertEquals(new Integer(1), depthFirst.get(3));
		assertEquals(new Integer(0), depthFirst.get(4));
		assertEquals(new Integer(6), depthFirst.get(5));
		assertEquals(new Integer(5), depthFirst.get(6));
	}

	@Test
	public void when_depth_first_with_undirected_and_unconnected_graph_must_return_all_correctly() {
		Graph graph = getDefaultUnconnectedGraph(GraphType.UNDIRECTED);
		List<Integer> depthFirst = graph.depthFirst(0);
		
		assertEquals(new Integer(2), depthFirst.get(0));
		assertEquals(new Integer(3), depthFirst.get(1));
		assertEquals(new Integer(1), depthFirst.get(2));
		assertEquals(new Integer(4), depthFirst.get(3));
		assertEquals(new Integer(0), depthFirst.get(4));
		assertEquals(new Integer(6), depthFirst.get(5));
		assertEquals(new Integer(5), depthFirst.get(6));
	}
	
	@Test
	public void when_has_cycle_with_directed_graph_with_cycle_must_return_true() {
		Graph graph = getDirectedGraphWithCycle();
		assertTrue(graph.hasCycle());
	}
	
	@Test
	public void when_has_cycle_with_directed_graph_without_cycle_must_return_false() {
		Graph graph = getGraphWithoutCycle(GraphType.DIRECTED);
		assertFalse(graph.hasCycle());
	}
	
	@Test
	public void when_has_cycle_with_undirected_graph_with_cycle_must_return_true() {
		Graph graph = getDefaultGraph(GraphType.UNDIRECTED);
		assertTrue(graph.hasCycle());
	}
	
	@Test
	public void when_has_cycle_with_undirected_graph_without_cycle_must_return_false() {
		Graph graph = getGraphWithoutCycle(GraphType.UNDIRECTED);
		assertFalse(graph.hasCycle());
	}
	
	@Test
	public void when_has_cycle_with_unconnected_graph_without_cycle_must_return_false() {
		Graph graph = getUnconnectedGraphWithoutCycle(GraphType.UNDIRECTED);
		assertFalse(graph.hasCycle());
	}
	
	@Test
	public void when_has_cycle_with_unconnected_graph_with_cycle_must_return_true() {
		Graph graph = getUnconnectedGraphWithCycle(GraphType.UNDIRECTED);
		assertTrue(graph.hasCycle());
	}
	
	@Test
	public void when_is_connected_with_undirected_connected_graph_must_return_true() {
		Graph graph = getDefaultGraph(GraphType.UNDIRECTED);
		assertTrue(graph.isConnected());
	}
	
	@Test
	public void when_is_connected_with_directed_connected_graph_must_return_true() {
		Graph graph = getDefaultGraph(GraphType.DIRECTED);
		assertTrue(graph.isConnected());
	}
	
	@Test
	public void when_is_connected_with_undirected_unconnected_graph_must_return_false() {
		Graph graph = getUnconnectedGraphWithoutCycle(GraphType.UNDIRECTED);
		assertFalse(graph.isConnected());
	}
	
	@Test
	public void when_is_connected_with_directed_unconnected_graph_must_return_false() {
		Graph graph = getUnconnectedGraphWithoutCycle(GraphType.UNDIRECTED);
		assertFalse(graph.isConnected());
	}
	
	private Graph getUnconnectedGraphWithCycle(GraphType type) {
		Graph graph = new AdjacencySetGraph(type, 6);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(3, 5);
		graph.addEdge(5, 4);
		graph.addEdge(4, 3);
		return graph;
	}
	
	private Graph getUnconnectedGraphWithoutCycle(GraphType type) {
		Graph graph = new AdjacencySetGraph(type, 7);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(3, 5);
		graph.addEdge(5, 4);
		graph.addEdge(4, 6);
		return graph;
	}

	private Graph getDirectedGraphWithCycle() {
		Graph graph = new AdjacencySetGraph(GraphType.DIRECTED);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		return graph;
	}
	
	private Graph getGraphWithoutCycle(GraphType type) {
		//https://en.wikipedia.org/wiki/Tree_(graph_theory)#/media/File:Tree_graph.svg
		Graph graph = new AdjacencySetGraph(type, 6);
		graph.addEdge(5, 4);
		graph.addEdge(4, 3);
		graph.addEdge(3, 0);
		graph.addEdge(3, 1);
		graph.addEdge(3, 2);
		return graph;
	}

	private Graph getDefaultGraph(GraphType type) {
		//http://www3.cs.stonybrook.edu/~algorith/files/graph-data-structures-L.gif
		Graph graph = new AdjacencySetGraph(type);
		graph.addEdge(0, 4);
		graph.addEdge(0, 1);
		graph.addEdge(1, 4);
		graph.addEdge(1, 3);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		return graph;
	}
	
	private Graph getDefaultUnconnectedGraph(GraphType type) {
		Graph graph = new AdjacencySetGraph(type, 7);
		graph.addEdge(0, 4);
		graph.addEdge(0, 1);
		graph.addEdge(1, 4);
		graph.addEdge(1, 3);
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 4);
		graph.addEdge(5, 6);
		return graph;
	}

	private GraphType getGraphType(AdjacencySetGraph graph) {
		return (GraphType) ReflectionTestUtils.getField(graph, "type");
	}

	@SuppressWarnings("unchecked")
	private List<GraphNode> getGraphVertices(AdjacencySetGraph graph) {
		return (List<GraphNode>) ReflectionTestUtils.getField(graph, "vertices");
	}
	
}
