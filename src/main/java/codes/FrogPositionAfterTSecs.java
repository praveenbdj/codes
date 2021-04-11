package codes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//Leetcode 1377.
public class FrogPositionAfterTSecs {

    private static boolean visited[];

    private static class V {
        List<Integer> vertices = new ArrayList<>();

        public List<Integer> getVertices() {
            return vertices;
        }

        public void addVertex(int v) {
            vertices.add(v);
        }

        public List<Integer> getUnvisitedVertices() {
            return vertices.stream().filter(x -> !visited[x]).collect(Collectors.toList());
        }
    }

    public double frogPosition(int n, int[][] edges, int t, int target) {
        V[] vs = new V[n + 1];
        for (int i = 1; i < vs.length; i++) {
            vs[i] = new V();
        }
        for (int[] edge : edges) {
            vs[edge[0]].addVertex(edge[1]);
            vs[edge[1]].addVertex(edge[0]);
            ;
        }
        visited = new boolean[n + 1];
        return probability(vs, 1, 1, target, t);
    }

    private double probability(V[] vs, int root, double p, int target, int time) {
        if (time == 0) {
            if (root == target) {
                return p;
            }
            return 0;
        }
        visited[root] = true;
        double ans = 0;
        List<Integer> vertices = vs[root].getUnvisitedVertices();
        int size = vertices.size();
        if (size != 0) {
            double ratio = p / size;
            for (Integer vertex : vertices) {
                ans += probability(vs, vertex, ratio, target, time - 1);
            }
        } else if (root == target) return p;
        visited[root] = false;
        return ans;
    }

    public static void main(String[] args) {
        FrogPositionAfterTSecs prog = new FrogPositionAfterTSecs();
        System.out.println(
                prog.frogPosition(8, new int[][]{{2, 1}, {3, 2}, {4, 1}, {5, 1}, {6, 4}, {7, 1}, {8, 7}}, 7, 7)
        );
    }

}
