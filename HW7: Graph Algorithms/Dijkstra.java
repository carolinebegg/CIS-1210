import java.util.LinkedList;
import java.util.List;

/**
 * Provides access to Dijkstra's algorithm for a weighted graph.
 */
final public class Dijkstra {
    private Dijkstra() {}

    /**
     * Computes the shortest path between two nodes in a weighted graph.
     * Input graph is guaranteed to be valid and have no negative-weighted edges.
     *
     * @param g   the weighted graph to compute the shortest path on
     * @param src the source node
     * @param tgt the target node
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     */
    public static List<Integer> getShortestPath(Graph g, int src, int tgt) {
        BinaryMinHeapImpl<Integer, Integer> pQueue = build(g, src);
        LinkedList<Integer> paths = new LinkedList<>();

        int[] distance = new int[g.getSize()];
        Integer[] parent = new Integer[g.getSize()];

        for (int v = 0; v < g.getSize(); v++) {
            distance[v] = Integer.MAX_VALUE;
            parent[v] = null;
        }
        distance[src] = 0;

        while (!pQueue.isEmpty()) {
            int u = pQueue.extractMin().value;
            for (int v : g.outNeighbors(u)) {
                if (pQueue.containsValue(v) && distance[v] > distance[u] + g.getWeight(u,v)) {
                    distance[v] = distance[u] + g.getWeight(u,v);
                    pQueue.decreaseKey(v,distance[v]);
                    parent[v] = u;
                }
            }
        }

        if (parent[tgt] == null && src != tgt) {
            return paths;
        }

        for (int i = 1; i < tgt; i++) {
            if (parent[i] == null && parent[i - 1] != null) {
                return paths;
            }
        }

        int curr = tgt;
        while (parent[curr] != null) {
            paths.addFirst(curr);
            curr = parent[curr];
        }
        paths.addFirst(src);
        return paths;
    }
    static BinaryMinHeapImpl<Integer, Integer> build(Graph g, int src) {
        BinaryMinHeapImpl<Integer, Integer> pQueue = new BinaryMinHeapImpl<>();
        for (int i = 0; i < g.getSize(); i++) {
            if (i != src) {
                pQueue.add(Integer.MAX_VALUE, i);
            } else {
                pQueue.add(0, src);
            }
        }
        return pQueue;
    }
}
