package codes;

import helper.Helper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/find-all-anagrams-in-a-string/
public class AllAnagramsInString {
    public List<Integer> findAnagrams(String s, String p) {
        int l = p.length();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            int k = map.getOrDefault(c, 0);
            map.put(c, k - 1);
        }
        int i = 0, j = 0;
        List<Integer> ans = new LinkedList<Integer>();
        while (j < s.length()) {
            addOrReset(map, s, j, true);
            if (j - i + 1 > l) {
                addOrReset(map, s, i, false);
                i++;
            }
            if (j - i + 1 == l && map.size() == 0) {
                ans.add(i);
            }
            j++;
        }
        return ans;
    }

    private void addOrReset(Map<Character, Integer> map, String s, int j, boolean add) {
        int k = map.getOrDefault(s.charAt(j), 0);
        k += add ? +1 : -1;
        map.put(s.charAt(j), k);
        if (k == 0)
            map.remove(s.charAt(j));
    }

    public static void main(String[] args) {
        AllAnagramsInString sol = new AllAnagramsInString();
        System.out.println(
                Helper.serialize(
                        sol.findAnagrams("abab", "ab")
                )
        );
    }
}
