import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IslandBridgeTest {
    Graph graph;
    Graph transpose;
    Graph bfsExample;
    Graph transposeExample;

    @Before
    public void setupTestGraphs() {
        bfsExample = new Graph(13);
        bfsExample.addEdge(0,1,0);
        bfsExample.addEdge(0,2,0);
        bfsExample.addEdge(1,3,0);
        bfsExample.addEdge(1,4,0);
        bfsExample.addEdge(2,6,0);
        bfsExample.addEdge(2,7,0);
        bfsExample.addEdge(4,5,0);

        transposeExample = new Graph(5);
        transposeExample.addEdge(0,1,0);
        transposeExample.addEdge(0,3,0);
        transposeExample.addEdge(0,4,0);

        transposeExample.addEdge(3,2,0);

        transposeExample.addEdge(4,1,0);
        transposeExample.addEdge(4,3,0);

        transposeExample.addEdge(2,0,0);
    }

    /** transpose Tests **/
    @Test
    public void testTransposeTwoNodesOneEdge() {
        graph = new Graph(2);
        graph.addEdge(0,1,0);
        Graph transpose = IslandBridge.transpose(graph);
        assertFalse(transpose.hasEdge(0,1));
        assertTrue(transpose.hasEdge(1,0));
    }
    @Test
    public void testTransposeTwoNodesOtherEdge() {
        graph = new Graph(2);
        graph.addEdge(1,0,0);
        Graph transpose = IslandBridge.transpose(graph);
        assertTrue(transpose.hasEdge(0,1));
        assertFalse(transpose.hasEdge(1,0));
    }
    @Test
    public void testTransposeThreeNodes() {
        graph = new Graph(3);
        graph.addEdge(0,1,0);
        graph.addEdge(0,2,0);
        graph.addEdge(1,2,0);
        Graph transpose = IslandBridge.transpose(graph);
        assertTrue(transpose.hasEdge(1,0));
        assertTrue(transpose.hasEdge(2,0));
        assertTrue(transpose.hasEdge(2,1));
        assertFalse(transpose.hasEdge(0,1));
        assertFalse(transpose.hasEdge(0,2));
        assertFalse(transpose.hasEdge(1,2));
    }
    @Test
    public void testTransposeFiveNodes() {
        Graph transpose = IslandBridge.transpose(bfsExample);
        assertFalse(transpose.hasEdge(0,1));
        assertFalse(transpose.hasEdge(0,2));
        assertFalse(transpose.hasEdge(1,3));
        assertFalse(transpose.hasEdge(1,4));
        assertFalse(transpose.hasEdge(2,6));
        assertFalse(transpose.hasEdge(2,7));
        assertFalse(transpose.hasEdge(4,5));

        assertTrue(transpose.hasEdge(1,0));
        assertTrue(transpose.hasEdge(2,0));
        assertTrue(transpose.hasEdge(3,1));
        assertTrue(transpose.hasEdge(4,1));
        assertTrue(transpose.hasEdge(6,2));
        assertTrue(transpose.hasEdge(7,2));
        assertTrue(transpose.hasEdge(5,4));
    }
    @Test
    public void testTransposeThirteenNodes() {
        transpose = IslandBridge.transpose(transposeExample);
        assertFalse(transpose.hasEdge(0,1));
        assertFalse(transpose.hasEdge(0,3));
        assertFalse(transpose.hasEdge(0,4));

        assertFalse(transpose.hasEdge(3,2));

        assertFalse(transpose.hasEdge(4,3));
        assertFalse(transpose.hasEdge(4,1));

        assertFalse(transpose.hasEdge(2,0));

        assertTrue(transpose.hasEdge(1,0));
        assertTrue(transpose.hasEdge(3,0));
        assertTrue(transpose.hasEdge(4,0));

        assertTrue(transpose.hasEdge(2,3));

        assertTrue(transpose.hasEdge(3,4));
        assertTrue(transpose.hasEdge(1,4));

        assertTrue(transpose.hasEdge(0,2));
    }

    /** NAVIGABLE TESTS **/
    @Test
    public void testOneIsland() {
        graph = new Graph(1);
        assertTrue(IslandBridge.allNavigable(graph, 0));
    }

    @Test
    public void testTwoIslandsTrue() {
        graph = new Graph(2);
        graph.addEdge(0,1, 0);
        graph.addEdge(1,0, 0);
        assertTrue(IslandBridge.allNavigable(graph, 0));
    }

    @Test
    public void testThreeIslandsCompleteCycle() {
        graph = new Graph(3);
        graph.addEdge(0,1, 0);
        graph.addEdge(1,2, 0);
        graph.addEdge(2,0, 0);
        assertTrue(IslandBridge.allNavigable(graph, 0));
        assertTrue(IslandBridge.allNavigable(graph, 1));
        assertTrue(IslandBridge.allNavigable(graph, 2));
    }

    @Test
    public void testFiveIslandsFigureBowtie() {
        graph = new Graph(5);
        graph.addEdge(0,1, 0);
        graph.addEdge(1,2, 0);
        graph.addEdge(2,3, 0);
        graph.addEdge(3,4, 0);
        graph.addEdge(4,2, 0);
        graph.addEdge(2,0, 0);

        assertTrue(IslandBridge.allNavigable(graph, 0));
        assertTrue(IslandBridge.allNavigable(graph, 1));
        assertTrue(IslandBridge.allNavigable(graph, 2));
        assertTrue(IslandBridge.allNavigable(graph, 3));
        assertTrue(IslandBridge.allNavigable(graph, 4));
    }

    /** UNNAVIGABLE TESTS **/

    @Test
    public void testTwoIslandsFalseZeroToOne() {
        graph = new Graph(2);
        graph.addEdge(0,1, 0);
        assertFalse(IslandBridge.allNavigable(graph, 0));
    }

    @Test
    public void testTwoIslandsTrueZeroToOne() {
        graph = new Graph(2);
        graph.addEdge(0,1, 0);
        assertTrue(IslandBridge.allNavigable(graph, 1));
    }

    @Test
    public void testThreeIslandsFalseV() {
        graph = new Graph(3);
        graph.addEdge(0,1,0);
        graph.addEdge(0,2,0);
        assertFalse(IslandBridge.allNavigable(graph,0));
    }

    @Test
    public void testThreeIslandsLineAtZero() {
        graph = new Graph(3);
        graph.addEdge(0,1,0);
        graph.addEdge(1,2,0);
        assertFalse(IslandBridge.allNavigable(graph,0));
    }

    @Test
    public void testThreeIslandsLineAtOne() {
        graph = new Graph(3);
        graph.addEdge(0,1,0);
        graph.addEdge(1,2,0);
        assertFalse(IslandBridge.allNavigable(graph,1));
    }

    @Test
    public void testThreeIslandsLineAtTwo() {
        graph = new Graph(3);
        graph.addEdge(0,1,0);
        graph.addEdge(1,2,0);
        assertTrue(IslandBridge.allNavigable(graph,2));
    }
    @Test
    public void testFourIslandsFalseCycleWithLegAtLegFromOne() {
        graph = new Graph(4);
        graph.addEdge(0,1,0);
        graph.addEdge(1,2,0);
        graph.addEdge(2,0,0);
        graph.addEdge(2,3,0);
        assertFalse(IslandBridge.allNavigable(graph,1));
    }

    @Test
    public void testFourIslandsFalseCycleWithLegAtLegFromThree() {
        graph = new Graph(4);
        graph.addEdge(0,1,0);
        graph.addEdge(1,2,0);
        graph.addEdge(2,0,0);
        graph.addEdge(2,3,0);
        assertTrue(IslandBridge.allNavigable(graph,3));
    }

    @Test
    public void testFiveIslandsFrom0() {
        assertFalse(IslandBridge.allNavigable(transposeExample,0));
    }

    @Test
    public void testFiveIslandsFrom1() {
        assertTrue(IslandBridge.allNavigable(transposeExample,1));
    }

    @Test
    public void testFiveIslandsFrom2() {
        assertFalse(IslandBridge.allNavigable(transposeExample,2));
    }

    @Test
    public void testFiveIslandsFrom3() {
        assertFalse(IslandBridge.allNavigable(transposeExample,3));
    }

    @Test
    public void testFiveIslandsFrom4() {
        assertFalse(IslandBridge.allNavigable(transposeExample,4));
    }

    @Test
    public void testFiveIslandsCycleWithLongLeg() {
        graph = new Graph(5);
        graph.addEdge(0,1,0);
        graph.addEdge(1,2,0);
        graph.addEdge(2,3,0);
        graph.addEdge(3,4,0);
        graph.addEdge(4,2,0);
        assertFalse(IslandBridge.allNavigable(graph,0));
    }
    @Test
    public void testFiveIslandsCycleWithLongLine() {
        graph = new Graph(5);
        graph.addEdge(0,1,0);
        graph.addEdge(1,2,0);
        graph.addEdge(2,3,0);
        graph.addEdge(3,4,0);
        assertFalse(IslandBridge.allNavigable(graph,0));
    }

    @Test
    public void testFourIslandsTwoCCs() {
        graph = new Graph(4);
        graph.addEdge(0,1,0);
        graph.addEdge(2,3,0);
        assertFalse(IslandBridge.allNavigable(graph,2));
    }

    @Test
    public void testDAGSink() {
        graph = new Graph(4);
        graph.addEdge(0,3,0);
        graph.addEdge(1,3,0);
        graph.addEdge(2,3,0);
        assertTrue(IslandBridge.allNavigable(graph,3));
    }
    @Test
    public void testMultipleCycles() {
        graph = new Graph(4);
        graph.addEdge(0,1,0);
        graph.addEdge(1,2,0);
        graph.addEdge(2,0,0);
        graph.addEdge(1,3,0);
        graph.addEdge(3,2,0);
        assertTrue(IslandBridge.allNavigable(graph,3));
    }
    @Test
    public void testComplexDAGSink() {
        graph = new Graph(6);
        graph.addEdge(0,3,0);
        graph.addEdge(0,4,0);
        graph.addEdge(1,3,0);
        graph.addEdge(2,3,0);
        graph.addEdge(5,2,0);
        graph.addEdge(4,3,0);
        assertTrue(IslandBridge.allNavigable(graph,3));
    }
}
