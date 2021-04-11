package codes;

import java.util.*;

//leetcode 210
public class CourseSchedule2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //solution from leetcode 207 to find if a solution exists or not.(to detect a cycle)
        //using an additional array to store the dfs traversal, and then returning its reverse order(topological sorting).
        Map<Integer, List<Integer>> adjMap = new HashMap<>(numCourses);
        for (int[] prerequisite : prerequisites) {
            int u = prerequisite[1];
            int v = prerequisite[0];
            adjMap.computeIfAbsent(u, integer -> new LinkedList<>());
            adjMap.get(u).add(v);
        }
        boolean[] visited = new boolean[numCourses];
        Set<Integer> dp = new HashSet<>();
        List<Integer> solution = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            if (!dp.contains(i) &&
                    !dfs(i, adjMap, visited, dp, solution)) return new int[0];
        }
        Collections.reverse(solution);
        return solution.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean dfs(int index, Map<Integer, List<Integer>> adjMap, boolean[] visited, Set<Integer> dp, List<Integer> solution) {
        if (dp.contains(index)) return true;
        if (visited[index]) return false;
        visited[index] = true;
        if (adjMap.containsKey(index))
            for (Integer vertex : adjMap.get(index)) {
                if (!dfs(vertex, adjMap, visited, dp, solution)) return false;
            }
        visited[index] = false;
        dp.add(index);
        solution.add(index);
        return true;
    }

}
