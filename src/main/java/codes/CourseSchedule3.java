package codes;

import java.util.*;

//leetcode 210
public class CourseSchedule3 {

    static class Key {
        int index;
        int time;

        public Key(int index, int time) {
            this.index = index;
            this.time = time;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return index == key.index && time == key.time;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, time);
        }
    }

    public int scheduleCourseUnoptimized(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(ints -> ints[1]));
        Map<Key, Integer> dp = new HashMap<>();
        return solve(0, 0, courses, dp);
    }

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(ints -> ints[1]));
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int t = 0;
        for (int[] course : courses) {
            if (t + course[0] <= course[1]) {
                t += course[0];
                queue.add(course[0]);
            } else if (!queue.isEmpty()) {
                int r = queue.peek() - course[0];
                if (r > 0 && t - r < course[1]) {
                    queue.poll();
                    t -= r;
                    queue.add(course[0]);
                }
            }
        }
        return queue.size();
    }

    public int solve(int i, int time, int[][] courses, Map<Key, Integer> dp) {
        if (i >= courses.length) return 0;
        Key key = new Key(i, time);
        if (!dp.containsKey(key)) {
            int ans = solve(i + 1, time, courses, dp);
            int t = courses[i][0];
            int d = courses[i][1];
            if (t + time <= d) {
                ans = Math.max(ans,
                        1 + solve(i + 1, time + t, courses, dp)
                );
            }
            dp.put(key, ans);
        }
        return dp.get(key);
    }

    public static void main(String[] args) {
        CourseSchedule3 schedule = new CourseSchedule3();
        System.out.println(
                schedule.scheduleCourse(new int[][]{{2, 5}, {2, 19}, {1, 8}, {1, 3}})
        );
    }

}
