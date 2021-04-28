package codes;

import java.util.*;

//https://leetcode.com/problems/word-ladder/
public class WordLadderPreProcessing {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return bfs(beginWord, endWord, getAdjacencyMap(wordList));
    }

    private Map<String, List<String>> getAdjacencyMap(List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String next = word.substring(0, i) + "*" + word.substring(i + 1);
                map.putIfAbsent(next, new ArrayList<>());
                map.get(next).add(word);
            }
        }
        return map;
    }

    private int bfs(String start, String end, Map<String, List<String>> map) {
        int L = start.length();
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<>();
        Set<String> inVisited = new HashSet<>();
        queue.add(start);
        visited.put(start, 1);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            int level = visited.get(word) + 1;
            for (int i = 0; i < L; i++) {
                String intermediate = word.substring(0, i) + "*" + word.substring(i + 1);
                if (inVisited.contains(intermediate)) {
                    continue;
                }
                for (String next : map.getOrDefault(intermediate, new ArrayList<>())) {
                    if (next.equals(end)) return level;
                    if (!visited.containsKey(next)) {
                        visited.put(next, level);
                        queue.add(next);
                    }
                }
                inVisited.add(intermediate);
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        System.out.println(
                new WordLadderPreProcessing().ladderLength("cat",
                        "fin", Arrays.asList("ion", "rev", "che", "ind", "lie", "wis", "oct", "ham", "jag", "ray", "nun", "ref", "wig", "jul", "ken", "mit", "eel", "paw", "per", "ola", "pat", "old", "maj", "ell", "irk", "ivy", "beg", "fan", "rap", "sun", "yak", "sat", "fit", "tom", "fin", "bug", "can", "hes", "col", "pep", "tug", "ump", "arc", "fee", "lee", "ohs", "eli", "nay", "raw", "lot", "mat", "egg", "cat", "pol", "fat", "joe", "pis", "dot", "jaw", "hat", "roe", "ada", "mac"))
        );
    }
}
