import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GraphTest {
    private Graph graph;

    /** Graph Constructor Test **/

    @Test(expected = IllegalArgumentException.class)
    public void testGraphZeroVertices() {
        graph = new Graph(0);
    }

    /** getSize() Tests **/
    @Test
    public void testGraphOneVertex() {
        graph = new Graph(1);
        assertEquals(1, graph.getSize());
    }

    @Test
    public void testGraphTwoVertices() {
        graph = new Graph(2);
        assertEquals(2, graph.getSize());
    }

    @Test
    public void testGraphThreeVertices() {
        graph = new Graph(3);
        assertEquals(3, graph.getSize());
    }

    @Test
    public void testGraphFourVertices() {
        graph = new Graph(4);
        assertEquals(4, graph.getSize());
    }

    /** hasEdge() Tests **/

    @Test(expected = IllegalArgumentException.class)
    public void testHasEdgeVertexUNotFound() {
        graph = new Graph(1);
        graph.hasEdge(1,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHasEdgeVertexVertexVNotFound() {
        graph = new Graph(1);
        graph.hasEdge(0,1);
    }

    @Test
    public void testHasEdgeFalseOneVertexSameVertex() {
        graph = new Graph(1);
        assertFalse(graph.hasEdge(0,0));
    }

    @Test
    public void testHasEdgeFalseTwoVertices() {
        graph = new Graph(2);
        assertFalse(graph.hasEdge(0,1));
        assertFalse(graph.hasEdge(1,0));
    }

    @Test
    public void testHasEdgeFalseThreeVertices() {
        graph = new Graph(3);
        assertFalse(graph.hasEdge(0,1));
        assertFalse(graph.hasEdge(1,2));
        assertFalse(graph.hasEdge(2,0));
        assertFalse(graph.hasEdge(0,2));
        assertFalse(graph.hasEdge(2,1));
        assertFalse(graph.hasEdge(1,0));
    }

    /** getWeight() Tests **/
    @Test(expected = IllegalArgumentException.class)
    public void testGetWeightVertexUNotFound() {
        graph = new Graph(1);
        graph.getWeight(1,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWeightVertexVNotFound() {
        graph = new Graph(1);
        graph.getWeight(0,1);
    }
    @Test(expected = NoSuchElementException.class)
    public void testGetWeightNoSuchArgOneVertexZeroToZero() {
        graph = new Graph(1);
        graph.getWeight(0,0);
    }
    @Test(expected = NoSuchElementException.class)
    public void testGetWeightNoSuchArgOneVertexZeroToOne() {
        graph = new Graph(2);
        graph.getWeight(0,1);
    }
    @Test(expected = NoSuchElementException.class)
    public void testGetWeightNoSuchArgOneVertexOneToZero() {
        graph = new Graph(2);
        graph.getWeight(1,0);
    }

    @Test
    public void testGetWeightDirectedOneWay() {
        graph = new Graph(2);
        graph.addEdge(0,1,5);
        assertEquals(5,graph.getWeight(0,1));
    }

    @Test
    public void testGetWeightDirectedTwoWaysSame() {
        graph = new Graph(2);
        graph.addEdge(0,1,5);
        graph.addEdge(1,0,5);
        assertEquals(5,graph.getWeight(0,1));
        assertEquals(5,graph.getWeight(1,0));
    }
    @Test
    public void testGetWeightDirectedTwoWaysDifferent() {
        graph = new Graph(2);
        graph.addEdge(0,1,10);
        graph.addEdge(1,0,5);
        assertEquals(10,graph.getWeight(0,1));
        assertEquals(5,graph.getWeight(1,0));
    }

    /** addEdge() Tests **/
    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeVertexUNotFound() {
        graph = new Graph(1);
        graph.addEdge(-1,0,5);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeVertexVNotFound() {
        graph = new Graph(1);
        graph.addEdge(0,1,5);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeBetweenSameUAndVOneVertex() {
        graph = new Graph(1);
        graph.addEdge(0,0,5);
    }
    @Test
    public void testAddEdgeAlreadyExists() {
        graph = new Graph(2);
        graph.addEdge(0,1,5);
        assertFalse(graph.addEdge(0,1,5));
    }
    @Test
    public void testAddEdgeBackEdge() {
        graph = new Graph(2);
        graph.addEdge(0,1,5);
        assertTrue(graph.addEdge(1,0,5));
    }

    @Test
    public void testAddEdgeTrue() {
        graph = new Graph(2);
        assertTrue(graph.addEdge(0,1,5));
        assertTrue(graph.addEdge(1,0,5));
    }

    /** outNeighbors() Tests **/
    @Test(expected = IllegalArgumentException.class)
    public void testOutNeighborsVertexVNotFound() {
        graph = new Graph(1);
        graph.outNeighbors(1).size();
    }

    @Test
    public void testOutNeighborsOneVertex() {
        graph = new Graph(1);
        assertEquals(0,graph.outNeighbors(0).size());
    }

    @Test
    public void testOutNeighborsTwoVerticesDirected() {
        graph = new Graph(2);
        graph.addEdge(0,1,5);
        assertEquals(1,graph.outNeighbors(0).size());
        assertEquals(0,graph.outNeighbors(1).size());
    }
    @Test
    public void testOutNeighborsTwoVerticesUndirected() {
        graph = new Graph(2);
        graph.addEdge(0,1,5);
        graph.addEdge(1,0,5);
        assertEquals(1,graph.outNeighbors(0).size());
        assertEquals(1,graph.outNeighbors(1).size());
    }
    @Test
    public void testOutNeighborsTwoThreeVerticesNoEdges() {
        graph = new Graph(3);
        assertEquals(0,graph.outNeighbors(0).size());
        assertEquals(0,graph.outNeighbors(1).size());
        assertEquals(0,graph.outNeighbors(2).size());
    }
    @Test
    public void testOutNeighborsTwoThreeVerticesCycle() {
        graph = new Graph(3);
        graph.addEdge(0,1,5);
        graph.addEdge(1,2,5);
        graph.addEdge(2,0,5);
        assertEquals(1,graph.outNeighbors(0).size());
        assertEquals(1,graph.outNeighbors(1).size());
        assertEquals(1,graph.outNeighbors(2).size());
    }
    @Test
    public void testOutNeighborsTwoThreeVerticesComplete() {
        graph = new Graph(3);
        graph.addEdge(0,1,5);
        graph.addEdge(0,2,5);
        graph.addEdge(1,0,5);
        graph.addEdge(1,2,5);
        graph.addEdge(2,1,5);
        graph.addEdge(2,0,5);
        assertEquals(2,graph.outNeighbors(0).size());
        assertEquals(2,graph.outNeighbors(1).size());
        assertEquals(2,graph.outNeighbors(2).size());
    }
}
