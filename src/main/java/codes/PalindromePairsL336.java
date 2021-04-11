package codes;


import helper.JsonHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PalindromePairsL336 {

    private static class Trie {
        Trie[] next;
        Integer end;

        public Trie() {
            next = new Trie[26];
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> all = new LinkedList<>();
        Trie[] root = new Trie[26];
        for (int i = 0, wordsLength = words.length; i < wordsLength; i++) {
            addToTrie(root, words[i], 0, i);
        }
        for (int i = 0; i < words.length; i++) {
            all.addAll(
                    searchTrie(root, words[i], i)
            );
        }
        return all;
    }

    private void addToTrie(Trie[] root, String word, int i, int index) {
        if (i > word.length() - 1) return;
        char c = word.charAt(i);
        if (root[c - 'a'] == null) {
            root[c - 'a'] = new Trie();
        }
        root[c - 'a'].end = i == word.length() - 1 ? index : null;
        addToTrie(root[c - 'a'].next, word, ++i, index);
    }

    private List<List<Integer>> searchTrie(Trie[] root, String word, int index) {
        List<List<Integer>> list = new LinkedList<>();
        searchTrie(root, new StringBuilder(), word, word.length() - 1, index, list);
        return list;
    }

    private void searchTrie(Trie[] root, StringBuilder pre, String word, int i, int index, List<List<Integer>> lists) {
        if (i < 0) return;
        char c = word.charAt(i);
        if (root[c - 'a'] != null) {
            pre.append(c);
            if (root[c - 'a'].end != null && i == 0 && index != root[c - 'a'].end && isPalindrome(word, pre.toString())) {
                List<Integer> list = new ArrayList<>();
                list.add(index);
                list.add(root[c - 'a'].end);
                lists.add(list);
            }
            searchTrie(root[c - 'a'].next, pre, word, i > 0 ? i - 1 : 0, index, lists);
        }
    }

    private boolean isPalindrome(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        int i = 0, m = (l1 + l2) / 2, j = l2 - 1;
        while (i < m) {
            if (i >= l1) {
                if (word2.charAt(i - l1) != word2.charAt(j)) {
                    return false;
                }
            } else if (j < 0) {
                if (word1.charAt(i) != word1.charAt(l1 + j)) {
                    return false;
                }
            } else if (word1.charAt(i) != word2.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePairsL336 pp = new PalindromePairsL336();
        List<List<Integer>> pairs = pp.palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"});
        pairs.forEach(x -> System.out.println(JsonHelper.serialize(x)));
    }

}
