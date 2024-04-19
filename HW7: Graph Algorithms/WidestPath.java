import java.util.*;

/**
 * Returns a widest path between two vertices in an undirected graph. A widest path between two
 * vertices maximizes the weight of the minimum-weight edge in the path.
 * <p/>
 * There are multiple ways to solve this problem. The following algorithm may be helpful:
 * - Kruskal's algorithm using Union Find
 * You are NOT allowed to use Prim's
 * Feel free to use any previous algorithms that you have already implemented.
 */
public final class WidestPath {
    private WidestPath() {}

    /**
     * Computes a widest path from {@param src} to {@param tgt} for an undirected graph.
     * If there are multiple widest paths, this method may return any one of them.
     * Input {@param g} guaranteed to be undirected.
     * Input {@param src} and {@param tgt} are guaranteed to be valid and in-bounds.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param g   the graph
     * @param src the vertex from which to start the search
     * @param tgt the vertex to find via {@code src}
     * @return an ordered list of vertices on a widest path from {@code src} to {@code tgt}, or an
     * empty list if there is no such path. The first element is {@code src} and the last
     * element is {@code tgt}. If {@code src == tgt}, a list containing just that element is
     * returned.
     * @implSpec This method should run in worst-case O((n + m) log n) time.
     */
    public static List<Integer> getWidestPath(Graph g, int src, int tgt) {
        LinkedList<Integer> widest = new LinkedList<>();

        if (src == tgt) {
            widest.add(src);
            return widest;
        }

        Graph graph = kruskal(g);

        int[] parent = bfs(graph,src);

        if (parent[tgt] == -2) {
            return widest;
        }

        int curr = tgt;
        while (curr != src) {
            widest.addFirst(curr);
            curr = parent[curr];
        }

        widest.addFirst(src);
        return widest;
    }
    static Graph kruskal(Graph g) {
        Graph minimumSpanningTree = new Graph(g.getSize());

        LinkedList<Edge> edges = g.getEdges();
        edges.sort((a,b) -> Integer.compare(b.weight, a.weight));

        UnionFind unionFind = new UnionFind(g);
        for (int i = 0; i < g.getSize(); i++) {
            unionFind.makeSet(i);
        }

        for (Edge e : edges) {
            if (unionFind.find(e.start) != unionFind.find(e.end)) {
                minimumSpanningTree.addEdge(e.start, e.end, e.weight);
                minimumSpanningTree.addEdge(e.end, e.start, e.weight);
                unionFind.union(e.start, e.end);
            }
        }
        return minimumSpanningTree;
    }

    static int[] bfs(Graph graph, int src) {
        ResizingDequeImpl<Integer> deque = new ResizingDequeImpl<>();

        int size = graph.getSize();

        int[] parent = new int[size];
        Arrays.fill(parent, -2);
        parent[src] = -1;

        boolean[] discovered = new boolean[size];

        deque.addLast(src);
        discovered[src] = true;

        while (deque.size() > 0) {
            int v = deque.pollFirst();
            for (int u : graph.outNeighbors(v)) {
                if (!discovered[u]) {
                    discovered[u] = true;
                    deque.addLast(u);
                    parent[u] = v;
                }
            }
        }
        return parent;
    }
}
