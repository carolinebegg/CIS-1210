final public class IslandBridge {
    private IslandBridge() {}

    /**
     * See question details in the write-up. Input is guaranteed to be valid.
     *
     * @param g the input graph representing the islands as vertices and bridges as directed edges
     * @param x the starting node
     * @return true, if no matter how you navigate through the one-way bridges from node x,
     * there is always a way to drive back to node x, and false otherwise.
     * @implSpec This method should run in worst-case O(n + m) time.
     */
    public static boolean allNavigable(Graph g, int x) {
        boolean[] discovered = bfs(g,x);
        Graph t = transpose(g);
        boolean[] discoveredTranspose = bfs(t, x);

        for (int i = 0; i < g.getSize(); i++) {
            if (discovered[i]) {
                if (discovered[i] != discoveredTranspose[i]) {
                    return false;
                }
            }
        }
        return true;
    }
    static Graph transpose(Graph g) {
        Graph gTranspose = new Graph(g.getSize());
        for (int i = 0; i < g.getSize(); i++) {
            for (int v : g.outNeighbors(i)) {
                gTranspose.addEdge(v,i,0);
            }
        }
        return gTranspose;
    }
    static boolean[] bfs(Graph graph, int x) {
        ResizingDequeImpl<Integer> deque  = new ResizingDequeImpl<>();
        boolean[] discovered = new boolean[graph.getSize()];
        deque.addLast(x);
        discovered[x] = true;
        while (deque.size() > 0) {
            int v = deque.pollFirst();
            for (int u : graph.outNeighbors(v)) {
                if (!discovered[u]) {
                    discovered[u] = true;
                    deque.addLast(u);
                }
            }
        }
        return discovered;
    }
}
