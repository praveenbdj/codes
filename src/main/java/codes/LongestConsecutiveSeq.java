package codes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//https://leetcode.com/problems/longest-consecutive-sequence/
public class LongestConsecutiveSeq {

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> minMax = new HashMap<>();
        Map<Integer, Integer> maxMin = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        for (int num : nums) {
            if (visited.contains(num)) continue;
            checkAndAdjust(num, minMax, maxMin);
            visited.add(num);
        }
        int sol = 0;
        for (Integer key : minMax.keySet()) {
            sol = Math.max(sol, minMax.get(key) - key + 1);
        }
        return sol;
    }

    private void checkAndAdjust(int num, Map<Integer, Integer> minMax, Map<Integer, Integer> maxMin) {
        if (maxMin.containsKey(num - 1) && minMax.containsKey(num + 1)) {
            //merge
            int min = maxMin.get(num - 1);
            int max = minMax.get(num + 1);
            minMax.remove(num + 1);
            maxMin.remove(num - 1);
            maxMin.remove(max);
            minMax.remove(min);
            maxMin.put(max, min);
            minMax.put(min, max);

        } else if (maxMin.containsKey(num - 1)) {
            int min = maxMin.get(num - 1);
            minMax.put(min, num);
            maxMin.remove(num - 1);
            maxMin.put(num, min);

        } else if (minMax.containsKey(num + 1)) {
            int max = minMax.get(num + 1);
            maxMin.put(max, num);
            minMax.remove(num + 1);
            minMax.put(num, max);
        } else {
            minMax.put(num, num);
            maxMin.put(num, num);
        }
    }


    public static void main(String[] args) {
        System.out.println(
                new LongestConsecutiveSeq().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2})
        );
        System.out.println(
                new LongestConsecutiveSeq().longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1})
        );
        System.out.println(
                new LongestConsecutiveSeq().longestConsecutive(new int[]{})
        );
        System.out.println(
                new LongestConsecutiveSeq().longestConsecutive(new int[]{0})
        );
    }

}
