package codes;

import java.util.*;

//leetcode 399
public class EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> adjMap = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            addEdge(adjMap, equation.get(0), equation.get(1), values[i]);
            addEdge(adjMap, equation.get(1), equation.get(0), 1 / values[i]);
        }

        double[] sol = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            sol[i] = bfs(adjMap, query.get(0), query.get(1));
        }
        return sol;
    }

    private void addEdge(Map<String, Map<String, Double>> adjMap, String u, String v, Double w) {
        adjMap.computeIfAbsent(u, s -> new HashMap<>());
        adjMap.get(u).put(v, w);
    }

    private double bfs(Map<String, Map<String, Double>> adjMap, String u, String v) {
        if (!adjMap.containsKey(u))
            return -1.0;
        LinkedList<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();
        queue.add(u);

        while (queue.size() != 0) {
            String poll = queue.poll();
            visited.add(poll);
//            adjMap.get()

        }
        return 0.0;
    }

}
