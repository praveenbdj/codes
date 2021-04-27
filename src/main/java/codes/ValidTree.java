package codes;

import java.util.*;

//Given an undirected graph, check if the graph is minimally-connected.(aka Tree)
public class ValidTree {

    private static Map<Integer, List<Integer>> map;

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
        map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
            addEdge(v, u);
        }
        isValidTree();
    }

    private static void isValidTree() {
        Set<Integer> visited = new HashSet<>();
        Iterator<Integer> iterator = map.keySet().iterator();
        Integer next = iterator.next();
        if (!dfs(next, null, visited)) {
            System.out.println("NO");
            return;
        }
        while (iterator.hasNext()) {
            if (!visited.contains(iterator.next())) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static boolean dfs(Integer u, Integer parent, Set<Integer> visited) {
        visited.add(u);
        for (Integer v : map.get(u)) {
            if (visited.contains(v)) {
                if (!Objects.equals(v, parent)) {
                    return false;
                }
            } else if (!dfs(v, u, visited)) {
                return false;
            }
        }
        return true;
    }

    private static void addEdge(int u, int v) {
        map.putIfAbsent(u, new ArrayList<>());
        map.get(u).add(v);
    }

}
