package codes;

import java.util.*;

//leetcode 207
public class CourseSchedule {

    //the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>(numCourses);
        for (int[] prerequisite : prerequisites) {
            int u = prerequisite[1];
            int v = prerequisite[0];
            adjMap.computeIfAbsent(u, integer -> new LinkedList<>());
            adjMap.get(u).add(v);
        }
        boolean[] visited = new boolean[numCourses];
        Set<Integer> dp = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            if (!dp.contains(i) &&
                    !dfs(i, adjMap, visited, dp)) return false;
        }
        return true;
    }

    private boolean dfs(int index, Map<Integer, List<Integer>> adjMap, boolean[] visited, Set<Integer> dp) {
        if (dp.contains(index)) return true;
        if (visited[index]) return false;
        visited[index] = true;
        if (adjMap.containsKey(index))
            for (Integer vertex : adjMap.get(index)) {
                if (!dfs(vertex, adjMap, visited, dp)) return false;
            }
        visited[index] = false;
        dp.add(index);
        return true;
    }

    public static void main(String[] args) {
        CourseSchedule schedule = new CourseSchedule();
        System.out.println(
                schedule.canFinish(20, new int[][]{{5, 5}})
        );
    }

}
