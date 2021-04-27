package codes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

//Given an undirected graph, check if the graph is minimally-connected.(aka Tree)
//assuming nodes are in range[1,n], both inclusive
public class ValidTree2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        if (m != n - 1) {
            System.out.println("NO");
            return;
        }
        if (n == 1 && m == 0) {
            System.out.println("YES");
            return;
        }
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(graph, u, v);
            addEdge(graph, v, u);
        }
        isValidTree(graph);
    }

    private static void isValidTree(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        visited[0] = true;
        if (!dfs(graph, 1, null, visited)) {
            System.out.println("NO");
            return;
        }
        for (boolean b : visited) {
            if (!b) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static boolean dfs(List<List<Integer>> graph, Integer u, Integer parent, boolean[] visited) {
        visited[u] = true;
        for (Integer v : graph.get(u)) {
            if (visited[v]) {
                if (!Objects.equals(v, parent)) {
                    return false;
                }
            } else if (!dfs(graph, v, u, visited)) {
                return false;
            }
        }
        return true;
    }

    private static void addEdge(List<List<Integer>> graph, int a, int b) {
        graph.get(a).add(b);
    }

}
