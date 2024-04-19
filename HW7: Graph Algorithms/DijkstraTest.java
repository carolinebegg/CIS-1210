import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DijkstraTest {
    Graph graph;
    List<Integer> path;

    @Before
    public void setup() {

    }
    @Test
    public void testOneNode() {
        graph = new Graph(1);
        path = Dijkstra.getShortestPath(graph, 0,0);
        int first = path.get(0);
        assertEquals(1, path.size());
        assertEquals(0, first);
    }
    @Test
    public void testTwoNodesDisconnected() {
        graph = new Graph(2);
        List<Integer> path = Dijkstra.getShortestPath(graph, 0,1);
        assertEquals(0, path.size());
    }
    @Test
    public void testNoPathFromSource() {
        graph = new Graph(5);
        graph.addEdge(0,1,2);
        graph.addEdge(1,2,3);
        graph.addEdge(3,4,4);
        List<Integer> path = Dijkstra.getShortestPath(graph, 0,4);
        assertEquals(0, path.size());
    }
    @Test
    public void testNoPathToTarget() {
        graph = new Graph(5);
        graph.addEdge(0,1,2);
        graph.addEdge(1,2,3);
        List<Integer> path = Dijkstra.getShortestPath(graph, 0,4);
        assertEquals(0, path.size());
    }

    @Test
    public void testTwoNodesConnected() {
        graph = new Graph(2);
        graph.addEdge(0,1,5);
        path = Dijkstra.getShortestPath(graph, 0,1);
        assertEquals(2, path.size());
        int first = path.get(0);
        int second = path.get(1);
        assertEquals(0, first);
        assertEquals(1, second);
    }
    @Test
    public void testThreeNodesDirect() {
        graph = new Graph(3);
        graph.addEdge(0,1,5);
        graph.addEdge(1,2,5);
        graph.addEdge(0,2,5);
        path = Dijkstra.getShortestPath(graph, 0,2);
        assertEquals(2, path.size());
        int first = path.get(0);
        assertEquals(0, first);
    }
    @Test
    public void testThreeNodesPath() {
        graph = new Graph(3);
        graph.addEdge(0,1,5);
        graph.addEdge(1,2,5);
        graph.addEdge(0,2,15);
        path = Dijkstra.getShortestPath(graph, 0,2);
        assertEquals(3, path.size());
        int first = path.get(0);
        int second = path.get(1);
        int third = path.get(2);
        assertEquals(0, first);
        assertEquals(1, second);
        assertEquals(2, third);
    }
    @Test
    public void testThreeNodesEqualPaths() {
        graph = new Graph(3);
        graph.addEdge(0,1,5);
        graph.addEdge(1,2,5);
        graph.addEdge(0,2,10);
        path = Dijkstra.getShortestPath(graph, 0,2);
        assertEquals(2, path.size());
        int first = path.get(0);
        int second = path.get(1);
        assertEquals(0, first);
        assertEquals(2, second);
    }

    @Test
    public void testFiveNodesDirectPath() {
        graph = new Graph(5);
        graph.addEdge(0,4,5);
        graph.addEdge(0,1,5);
        path = Dijkstra.getShortestPath(graph, 0,1);
        assertEquals(2, path.size());
        int first = path.get(0);
        int second = path.get(1);
        assertEquals(0, first);
        assertEquals(1, second);
    }
    @Test
    public void testFiveNodesOnePaths() {
        graph = new Graph(6);
        graph.addEdge(0,3,5);
        graph.addEdge(0,2,5);
        graph.addEdge(5,1,2);
        graph.addEdge(1,0,2);
        graph.addEdge(0,4,5);

        path = Dijkstra.getShortestPath(graph, 5,4);
        assertEquals(4, path.size());
        int first = path.get(0);
        int second = path.get(1);
        int third = path.get(2);
        int fourth = path.get(3);
        assertEquals(5, first);
        assertEquals(1, second);
        assertEquals(0, third);
        assertEquals(4, fourth);
    }

    @Test
    public void testDirectPathIsLonger() {
        graph = new Graph(5);
        graph.addEdge(0,4, 5);
        graph.addEdge(0,1, 1);
        graph.addEdge(1,2, 1);
        graph.addEdge(2,3, 1);
        graph.addEdge(3,4, 1);

        path = Dijkstra.getShortestPath(graph, 0,4);
        int first = path.get(0);
        int second = path.get(1);
        int third = path.get(2);
        int fourth = path.get(3);
        int fifth = path.get(4);
        assertEquals(5, path.size());
        assertEquals(0, first);
        assertEquals(1, second);
        assertEquals(2, third);
        assertEquals(3, fourth);
        assertEquals(4, fifth);
    }
}
