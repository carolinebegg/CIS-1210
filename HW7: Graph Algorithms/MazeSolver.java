import java.util.LinkedList;
import java.util.List;

final public class MazeSolver {
    private MazeSolver() {}

    /**
     * Returns a list of coordinates on the shortest path from {@code src} to {@code tgt}
     * by executing a breadth-first search on the graph represented by a 2D-array of size m x n.
     * Please note, you MUST use your ResizingDeque implementation as the BFS queue for this method.
     * <p>
     * Input {@code maze} guaranteed to be a non-empty and valid matrix.
     * Input {@code src} and {@code tgt} are guaranteed to be valid, in-bounds, and not blocked.
     * <p>
     * Do NOT modify this method header.
     *
     * @param maze the input maze, which is a 2D-array of size m x n
     * @param src The starting Coordinate of the path on the matrix
     * @param tgt The target Coordinate of the path on the matrix
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     * @implSpec This method should run in worst-case O(m x n) time.
     */

    public static List<Coordinate> getShortestPath(int[][] maze, Coordinate src, Coordinate tgt) {
        LinkedList<Coordinate> shortestPath = new LinkedList<>();
        int m = maze.length;
        int n = maze[0].length;

        if (m == 1 && n == 1) {
            shortestPath.add(new Coordinate(0,0));
            return shortestPath;
        }

        Graph graph = construct(maze);
        int[] parent = bfs(graph, src, maze);
        Coordinate curr = tgt;

        if (parent[getIndex(tgt, n)] == -2) {
            return shortestPath;
        }

        while (parent[getIndex(curr, n)] != -1) {
            shortestPath.addFirst(curr);
            curr = getCoordinate(parent[getIndex(curr, n)], n);
        }
        shortestPath.addFirst(src);
        return shortestPath;
    }

    static Coordinate getCoordinate(int index, int n) {
        int y = index / n;
        int x = index - n * y;
        return new Coordinate(x,y);
    }
    static int getIndex(Coordinate coordinate, int n) {
        return n * coordinate.getY() + coordinate.getX();
    }
    static boolean[] discover(int[][] maze) {
        boolean[] discovered = new boolean[maze.length * maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 1) {
                    Coordinate c = new Coordinate(j,i);
                    discovered[getIndex(c, maze[0].length)] = true;
                }
            }
        }
        return discovered;
    }
    static int[] bfs(Graph graph, Coordinate src, int[][] maze) {
        ResizingDequeImpl<Coordinate> deque = new ResizingDequeImpl<>();
        int m = maze.length;
        int n = maze[0].length;
        int size = graph.getSize();

        int[] parent = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -2;
        }
        parent[getIndex(src, n)] = -1;

        boolean[] discovered = discover(maze);

        deque.addLast(src);
        discovered[getIndex(src, n)] = true;

        while (deque.size() > 0) {
            Coordinate v = deque.pollFirst();
            for (Coordinate u : adjacent(v, m, n)) {
                if (!discovered[getIndex(u, n)]) {
                    discovered[getIndex(u, n)] = true;
                    deque.addLast(u);
                    parent[getIndex(u, n)] = getIndex(v, n);
                }
            }
        }
        return parent;
    }
    static Graph construct(int[][] maze) {
        Graph graph = new Graph(maze.length * maze[0].length);
        int index = 0;
        Coordinate curr;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                curr = new Coordinate(j,i);
                for (Coordinate c : adjacent(curr, maze.length, maze[0].length)) {
                    graph.addEdge(index,getIndex(c, maze[0].length), 0);
                }
                index++;
            }
        }
        return graph;
    }

    static Coordinate[] adjacent(Coordinate u, int m, int n) {
        Coordinate[] adj;
        int x = u.getX();
        int y = u.getY();
        if (x < 0 || x >= n || y >= m || y < 0) {
            adj = new Coordinate[0];
            return adj;
        }
        if (x == 0) {
            if (y == 0) {
                Coordinate right = new Coordinate(x + 1, y);
                Coordinate down = new Coordinate(x, y + 1);
                adj = new Coordinate[2];
                adj[0] = right;
                adj[1] = down;
                return adj;
            }
            if (y == m - 1) {
                Coordinate up = new Coordinate(x, y - 1);
                Coordinate right = new Coordinate(x + 1, y);
                adj = new Coordinate[2];
                adj[0] = up;
                adj[1] = right;
                return adj;
            }
            Coordinate up = new Coordinate(x, y - 1);
            Coordinate right = new Coordinate(x + 1, y);
            Coordinate down = new Coordinate(x, y + 1);
            adj = new Coordinate[3];
            adj[0] = up;
            adj[1] = right;
            adj[2] = down;
            return adj;
        }
        if (x == n - 1) {
            if (y == 0) {
                Coordinate left = new Coordinate(x - 1, y);
                Coordinate down = new Coordinate(x, y + 1);
                adj = new Coordinate[2];
                adj[0] = left;
                adj[1] = down;
                return adj;
            }
            if (y == m - 1) {
                Coordinate up = new Coordinate(x, y - 1);
                Coordinate left = new Coordinate(x - 1, y);
                adj = new Coordinate[2];
                adj[0] = up;
                adj[1] = left;
                return adj;
            }
            Coordinate up = new Coordinate(x, y - 1);
            Coordinate left = new Coordinate(x - 1, y);
            Coordinate down = new Coordinate(x, y + 1);
            adj = new Coordinate[3];
            adj[0] = up;
            adj[1] = left;
            adj[2] = down;
            return adj;
        }

        if (y == m - 1) {
            Coordinate up = new Coordinate(x, y - 1);
            Coordinate left = new Coordinate(x - 1, y);
            Coordinate right = new Coordinate(x + 1, y);
            adj = new Coordinate[3];
            adj[0] = up;
            adj[1] = left;
            adj[2] = right;
            return adj;
        }
        if (y == 0) {
            Coordinate left = new Coordinate(x - 1, y);
            Coordinate right = new Coordinate(x + 1, y);
            Coordinate down = new Coordinate(x, y + 1);
            adj = new Coordinate[3];
            adj[0] = left;
            adj[1] = right;
            adj[2] = down;
            return adj;
        }

        Coordinate up = new Coordinate(x, y - 1);
        Coordinate left = new Coordinate(x - 1, y);
        Coordinate right = new Coordinate(x + 1, y);
        Coordinate down = new Coordinate(x, y + 1);
        adj = new Coordinate[4];
        adj[0] = up;
        adj[1] = left;
        adj[2] = right;
        adj[3] = down;
        return adj;
    }
}