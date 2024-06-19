package codes;

import helper.Helper;

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
        if (!adjMap.containsKey(u) || !adjMap.containsKey(v))
            return -1.0;
        Queue<String> queue = new LinkedList<>();
        Map<String, Double> visitMap = new HashMap<>();
        queue.add(u);
        visitMap.put(u, 1.0);
        while (!queue.isEmpty()) {
            String poll = queue.poll();
            Double weight = visitMap.get(poll);
            if (poll.equals(v)) {
                return weight;
            }
            for (Map.Entry<String, Double> entry : adjMap.get(poll).entrySet()) {
                if (!visitMap.containsKey(entry.getKey())) {
                    queue.add(entry.getKey());
                    visitMap.put(entry.getKey(), entry.getValue() * weight);
                }
            }
        }

        return -1.0;
    }

    public static void main(String[] args) {
        List<List<String>> equations = new LinkedList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = new LinkedList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));
        System.out.println(
                Helper.serialize(
                        new EvaluateDivision().calcEquation(
                                equations, values, queries
                        )
                )
        );
    }

}
