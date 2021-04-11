package codes;

import java.util.Arrays;

public class RedundantConnection {
    int[] parent, size;

    public int[] findRedundantConnection(int[][] edges) {
        parent = new int[edges.length + 1];
        size = new int[edges.length + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        for (int[] edge : edges) {
            if (checkOrSet(edge[0], edge[1])) {
                return edge;
            }
        }
        return null;
    }

    private boolean checkOrSet(int p, int q) {
        if (root(p) == root(q)) return true;
        unite(p, q);
        return false;
    }

    private int root(int i) {
        while (i != parent[i]) i = parent[i];
        return i;
    }

    private void unite(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (size[i] >= size[j]) {
            parent[j] = i;
            size[i] += size[j];
        } else {
            parent[i] = j;
            size[j] += size[i];
        }
    }

    public static void main(String[] args) {
        RedundantConnection connection = new RedundantConnection();
        System.out.println(
                Arrays.toString(connection.findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}}))
        );
    }

}
