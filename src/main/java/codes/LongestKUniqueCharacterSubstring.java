package codes;

import java.util.HashMap;
import java.util.Map;

public class LongestKUniqueCharacterSubstring {

    public int longestkSubstr(String s, int k) {
        int i = 0, j = 0, max = -1, cl = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (j < s.length()) {
            int c = map.getOrDefault(s.charAt(j), 0);
            if (c != 0 || map.size() < k) {
                map.put(s.charAt(j++), c + 1);
                cl++;
            } else {
                if (map.size() == k) {
                    max = Math.max(max, cl);
                }
                int c2 = map.get(s.charAt(i));
                if (c2 == 1) {
                    map.remove(s.charAt(i));
                } else {
                    map.put(s.charAt(i), --c2);
                }
                cl--;
                i++;
            }
        }
        if (map.size() == k) {
            max = Math.max(max, cl);
        }
        return max;
    }

    public static void main(String[] args) {
        LongestKUniqueCharacterSubstring sol = new LongestKUniqueCharacterSubstring();
        System.out.println(sol.longestkSubstr("aabacbebebe", 3));
    }
}
