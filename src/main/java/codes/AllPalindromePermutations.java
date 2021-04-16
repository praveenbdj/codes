package codes;

import helper.JsonHelper;

import java.util.*;

public class AllPalindromePermutations {

    public static List<String> palPerms(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            int co = map.getOrDefault(c, 0);
            map.put(c, co + 1);
        }
        Character odd = null;
        int size = 0;
        for (char c : map.keySet()) {
            int co = map.get(c);
            if (co % 2 == 1) {
                if (odd != null) {
                    return new ArrayList<>();
                }
                odd = c;
            }
            map.put(c, co / 2);
            size += co / 2;
        }
        return permute(map, "", odd == null ? "" : String.valueOf(odd), size);

    }

    private static List<String> permute(Map<Character, Integer> map, String pre, String odd, int length) {
        if (pre.length() == length) {
            String rev = revers(pre);
            return Collections.singletonList(pre + odd + rev);
        }
        List<String> perms = new ArrayList<>();
        for (Character c : map.keySet()) {
            Integer co = map.get(c);
            if (co == 0) continue;
            map.put(c, co - 1);
            perms.addAll(
                    permute(map, pre + c, odd, length)
            );
            map.put(c, co);
        }
        return perms;
    }

    private static String revers(String s) {
        StringBuilder r = new StringBuilder();
        for (int j = s.length() - 1; j >= 0; j--) {
            r.append(s.charAt(j));
        }
        return r.toString();
    }

    public static void main(String[] args) {
        System.out.println(
                JsonHelper.serialize(
                        palPerms("abcba")
                )
        );
    }

}
