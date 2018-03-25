package datastructures.graphs;

import static org.junit.Assert.assertEquals;
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

	private GraphType getGraphType(AdjacencySetGraph graph) {
		return (GraphType) ReflectionTestUtils.getField(graph, "type");
	}

	@SuppressWarnings("unchecked")
	private List<GraphNode> getGraphVertices(AdjacencySetGraph graph) {
		return (List<GraphNode>) ReflectionTestUtils.getField(graph, "vertices");
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
	
//	@Test
//	public void when_beadth_first_with_directed_and_connected_graph_must_return_all_correctly() {
//		AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(GraphType.UNDIRECTED);
//	}
	
	@Test
	public void when_beadth_first_with_undirected_and_connected_graph_must_return_all_correctly() {
		Graph graph = getDefaultGraph(GraphType.UNDIRECTED);
		List<Integer> breadthFirst = graph.breadthFirst(0);
		
		assertEquals(new Integer(1), breadthFirst.get(0));
		assertEquals(new Integer(2), breadthFirst.get(1));
		assertEquals(new Integer(5), breadthFirst.get(2));
		assertEquals(new Integer(3), breadthFirst.get(3));
		assertEquals(new Integer(4), breadthFirst.get(4));
	}
	
//	public void when_beadth_first_with_directed_and_unconnected_graph_must_return_all_correctly() {
//		
//	}
//	
//	public void when_beadth_first_with_undirected_and_unconnected_graph_must_return_all_correctly() {
//		
//	}
	
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
	
}
