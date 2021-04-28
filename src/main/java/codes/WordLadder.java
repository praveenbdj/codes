package codes;

import java.util.*;

//https://leetcode.com/problems/word-ladder/
public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) return 0;
        Set<String> valid = new HashSet<>(wordList);
        return bfs(beginWord, endWord, valid);
    }


    private int bfs(String start, String end, Set<String> valid) {
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<>();
        Set<String> visitedSet = visited.keySet();
        queue.add(start);
        visited.put(start, 1);
        while (!queue.isEmpty()) {
            String s = queue.poll();
            int level = visited.get(s) + 1;
            for (String next : perms(s, valid, visitedSet)) {
                if (next.equals(end)) return level;
                visited.put(next, level);
                queue.add(next);
            }
        }
        return 0;
    }


    private List<String> perms(String s, Set<String> valid, Set<String> visited) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String n = sub(s, i, c);
                if (valid.contains(n) && !visited.contains(n)) {
                    list.add(n);
                }
            }
        }
        return list;
    }

    private String sub(String s, int i, char c) {
        char[] chars = s.toCharArray();
        chars[i] = c;
        return String.valueOf(chars);
    }

}
