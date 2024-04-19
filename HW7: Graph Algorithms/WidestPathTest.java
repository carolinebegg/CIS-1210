import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WidestPathTest {
    Graph g;
    List<Integer> widest;

    @Test
    public void testSrcEqualsTgtOneNode() {
        g = new Graph(1);
        widest = WidestPath.getWidestPath(g, 0, 0);
        int e0 = widest.get(0);
        assertEquals(1, widest.size());
        assertEquals(0, e0);
    }
    @Test
    public void testSrcEqualsTgtMultipleNodes() {
        g = new Graph(3);

        g.addEdge(0,1,5);
        g.addEdge(1,0,5);

        g.addEdge(1,2,2);
        g.addEdge(2,1,2);

        g.addEdge(2,0,5);
        g.addEdge(0,2,5);

        widest = WidestPath.getWidestPath(g, 0, 0);
        int e0 = widest.get(0);
        assertEquals(1, widest.size());
        assertEquals(0, e0);
    }
    @Test
    public void testTwoNodesPath() {
        g = new Graph(2);
        g.addEdge(0,1,2);
        g.addEdge(1,0,2);

        widest = WidestPath.getWidestPath(g, 0, 1);
        assertEquals(2, widest.size());
        int e0 = widest.get(0);
        int e1 = widest.get(1);
        assertEquals(0, e0);
        assertEquals(1, e1);
    }
    @Test
    public void testThreeNodesDirectPath() {
        g = new Graph(3);
        g.addEdge(0,1,5);
        g.addEdge(1,0,5);

        g.addEdge(1,2,2);
        g.addEdge(2,1,2);

        g.addEdge(0,2,3);
        g.addEdge(2,0,3);

        widest = WidestPath.getWidestPath(g, 0, 1);
        assertEquals(2, widest.size());
        int e0 = widest.get(0);
        assertEquals(0, e0);
    }
    @Test
    public void testThreeNodesIndirectPath() {
        g = new Graph(3);
        g.addEdge(0,1,2);
        g.addEdge(1,0,2);

        g.addEdge(1,2,5);
        g.addEdge(2,1,5);

        g.addEdge(0,2,3);
        g.addEdge(2,0,3);

        widest = WidestPath.getWidestPath(g, 0, 2);
        assertEquals(2, widest.size());
        int e0 = widest.get(0);
        int e1 = widest.get(1);
        assertEquals(0, e0);
        assertEquals(2, e1);
    }
    @Test
    public void testFourNodes() {
        g = new Graph(4);
        g.addEdge(0,1,1);
        g.addEdge(1,0,1);

        g.addEdge(1,2,3);
        g.addEdge(2,1,3);

        g.addEdge(0,3,2);
        g.addEdge(3,0,2);

        g.addEdge(2,3,5);
        g.addEdge(3,2,5);

        widest = WidestPath.getWidestPath(g, 0, 2);
        assertEquals(3, widest.size());
        int e0 = widest.get(0);
        int e1 = widest.get(1);
        int e2 = widest.get(2);
        assertEquals(0, e0);
        assertEquals(3, e1);
        assertEquals(2, e2);
    }
    @Test
    public void testFourNodesWithCycle() {
        g = new Graph(4);
        g.addEdge(0,1,1);
        g.addEdge(1,0,1);

        g.addEdge(1,2,3);
        g.addEdge(2,1,3);

        g.addEdge(2,0,4);
        g.addEdge(0,2,4);

        g.addEdge(2,3,2);
        g.addEdge(3,2,2);

        widest = WidestPath.getWidestPath(g, 0, 3);
        assertEquals(3, widest.size());
        int e0 = widest.get(0);
        int e1 = widest.get(1);
        int e2 = widest.get(2);
        assertEquals(0, e0);
        assertEquals(2, e1);
        assertEquals(3, e2);
    }
    @Test
    public void testLargeGraph() {
        g = new Graph(8);

        g.addEdge(0,1,2);
        g.addEdge(1,0,2);

        g.addEdge(0,2,3);
        g.addEdge(2,0,3);

        g.addEdge(1,3,4);
        g.addEdge(3,1,4);

        g.addEdge(3,2,4);
        g.addEdge(2,3,4);

        g.addEdge(3,4,2);
        g.addEdge(4,3,2);

        g.addEdge(3,5,1);
        g.addEdge(5,3,1);

        g.addEdge(6,4,5);
        g.addEdge(4,6,5);

        g.addEdge(6,5,3);
        g.addEdge(5,6,3);

        g.addEdge(6,7,2);
        g.addEdge(7,6,2);

        widest = WidestPath.getWidestPath(g, 0, 7);
        assertEquals(6, widest.size());
        int e0 = widest.get(0);
        int e1 = widest.get(1);
        int e2 = widest.get(2);
        int e3 = widest.get(3);
        int e4 = widest.get(4);
        int e5 = widest.get(5);
        assertEquals(0, e0);
        assertEquals(2, e1);
        assertEquals(3, e2);
        assertEquals(4, e3);
        assertEquals(6, e4);
        assertEquals(7, e5);
    }
    @Test
    public void testFourNodesUnreachable() {
        g = new Graph(4);
        g.addEdge(0,1,1);
        g.addEdge(1,0,1);

        g.addEdge(1,2,3);
        g.addEdge(2,1,3);

        g.addEdge(0,2,2);
        g.addEdge(2,0,2);

        widest = WidestPath.getWidestPath(g, 0, 3);
        assertTrue(widest.isEmpty());
    }
}
