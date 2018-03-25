package datastructures.graphs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import datastructures.base.graph.GraphType;

public class AdjacencyMatrixGraphTest {

	@Test
	public void when_construct_with_directed_type_must_create_a_valid_graph_with_no_connections() {
		AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(GraphType.DIRECTED);
		
		GraphType type = getGraphType(graph);
		int[][] representation = getGraphRepresentation(graph);
		
		assertEquals(GraphType.DIRECTED, type);
		for(int i = 0; i < AdjacencyMatrixGraph.DEFAULT_SIZE; i++) {
			for(int j = 0; j < AdjacencyMatrixGraph.DEFAULT_SIZE; j++) {
				assertEquals(0, representation[i][j]);
			}
		}
	}

	private int[][] getGraphRepresentation(AdjacencyMatrixGraph graph) {
		return (int[][]) ReflectionTestUtils.getField(graph, "representation");
	}

	private GraphType getGraphType(AdjacencyMatrixGraph graph) {
		return (GraphType) ReflectionTestUtils.getField(graph, "type");
	}
	
	@Test
	public void when_construct_with_undirected_type_must_create_a_valid_graph_with_no_connections() {
		AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(GraphType.UNDIRECTED);
		
		GraphType type = getGraphType(graph);
		int[][] representation = getGraphRepresentation(graph);
		
		assertEquals(GraphType.UNDIRECTED, type);
		for(int i = 0; i < AdjacencyMatrixGraph.DEFAULT_SIZE; i++) {
			for(int j = 0; j < AdjacencyMatrixGraph.DEFAULT_SIZE; j++) {
				assertEquals(0, representation[i][j]);
			}
		}
	}
	
	@Test
	public void when_add_edge_with_correct_coordinates_in_directed_graph_must_add_only_source_to_destination() {
		AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(GraphType.DIRECTED);
		graph.addEdge(3, 1);
		
		int[][] representation = getGraphRepresentation(graph);
		assertEquals(1, representation[3][1]);
		assertEquals(0, representation[1][3]);
	}
	
	@Test
	public void when_add_edge_with_correct_coordinates_in_undirect_graph_must_add_edge_in_both_directions() {
		AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(GraphType.UNDIRECTED);
		graph.addEdge(3, 1);

		int[][] representation = getGraphRepresentation(graph);
		assertEquals(1, representation[3][1]);
		assertEquals(1, representation[1][3]);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void when_add_edge_with_wrong_coordinates_must_throw_error() {
		AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(GraphType.DIRECTED);
		graph.addEdge(8, 1);
	}
	
	@Test
	public void when_get_adjacency_list_with_vertex_without_edges_must_return_empty_list() {
		AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(GraphType.DIRECTED);
		
		assertTrue(graph.getAdjacentVertices(3).isEmpty());
	}
	
	@Test
	public void when_get_adjacency_list_with_vertex_with_connections_in_directed_graph_must_return_a_list_with_all_edges_connected() {
		AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(GraphType.DIRECTED);
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
		AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(GraphType.UNDIRECTED);
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
}
