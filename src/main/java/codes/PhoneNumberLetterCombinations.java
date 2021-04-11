package codes;

import java.util.*;
import java.util.stream.Collectors;

// Leetcode 17.
public class PhoneNumberLetterCombinations {
    public List<String> letterCombinations(String digits) {
        Map<Character, List<String>> map = new HashMap<>();
        map.put('2', new ArrayList<>(Arrays.asList("a", "b", "c")));
        map.put('3', new ArrayList<>(Arrays.asList("d", "e", "f")));
        map.put('4', new ArrayList<>(Arrays.asList("g", "h", "i")));
        map.put('5', new ArrayList<>(Arrays.asList("j", "k", "l")));
        map.put('6', new ArrayList<>(Arrays.asList("m", "n", "o")));
        map.put('7', new ArrayList<>(Arrays.asList("p", "q", "r", "s")));
        map.put('8', new ArrayList<>(Arrays.asList("t", "u", "v")));
        map.put('9', new ArrayList<>(Arrays.asList("w", "x", "y", "z")));
        if (digits.length() == 0) return new ArrayList<>();
        return oo(map, digits, 0, "");
    }

    private List<String> oo(Map<Character, List<String>> map, String digits, int i, String pre) {
        if (i == digits.length()) return new ArrayList<>(Collections.singletonList(pre));
        char c = digits.charAt(i);
        return map.get(c).stream().map(x -> oo(map, digits, i + 1, pre + x)).flatMap(List::stream).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        PhoneNumberLetterCombinations combinations = new PhoneNumberLetterCombinations();
        long l = System.currentTimeMillis();
        List<String> strings = combinations.letterCombinations("235467465469875");
        System.out.println(
                "Size : " + strings.size()
                        + "\nTime taken : " + (System.currentTimeMillis() - l)
        );
    }
}
