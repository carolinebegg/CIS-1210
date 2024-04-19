
public class UnionFind {

    int[] parent;
    int[] rank;

    UnionFind(Graph g) {
        parent = new int[g.getSize()];
        rank = new int[g.getSize()];
    }
    void makeSet(int x) {
        parent[x] = x;
        rank[x] = 0;
    }
    void union(int x, int y) {
        int rX = find(x);
        int rY = find(y);

        if (rX == rY) {
            return;
        }

        if (rank[rX] > rank[rY]) {
            parent[rY] = rX;
        } else {
            parent[rX] = rY;
            if (rank[rX] == rank[rY]) {
                rank[rY] = rank[rY] + 1;
            }
        }
    }
    int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
}
