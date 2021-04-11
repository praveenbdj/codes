package codes;

import java.util.ArrayList;
import java.util.List;

//leetcode 1319
public class NetworkConnectedOperations {

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1)
            return -1;
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] connection : connections) {
            addEdge(graph, connection[0], connection[1]);
            addEdge(graph, connection[1], connection[0]);
        }
        boolean[] visited = new boolean[n];
        int c = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                c++;
                if (graph.get(i).size() != 0) dfs(i, graph, visited);
            }
        }
        return c - 1;
    }

    private void addEdge(List<List<Integer>> graph, int a, int b) {
        graph.get(a).add(b);
    }

    private void dfs(int i, List<List<Integer>> graph, boolean[] visited) {
        if (visited[i]) return;
        visited[i] = true;
        for (Integer b : graph.get(i)) {
            dfs(b, graph, visited);
        }
    }

}
