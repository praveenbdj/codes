package codes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://leetcode.com/problems/word-search-ii/
public class WordSearchII {

    public List<String> findWords(char[][] board, String[] words) {
        roots = new Node[26];
        for (String word : words) {
            addWord(word);
        }
        Set<String> ans = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                checkNullAndSet(ans, searchInBoard(board, visited, roots, i, j));
            }
        }
        return new ArrayList<>(ans);
    }

    private void checkNullAndSet(Set<String> ans, Set<String> set) {
        if (set != null) ans.addAll(set);
    }

    private Set<String> searchInBoard(char[][] board, boolean[][] visited, Node[] nodes, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return null;
        if (visited[i][j]) return null;
        Node node = nodes[board[i][j] - 'a'];
        if (node == null) return null;
        visited[i][j] = true;
        Set<String> ans = new HashSet<>();
        if (node.word != null) {
            ans.add(node.word);
        }
        checkNullAndSet(ans, searchInBoard(board, visited, node.nodes, i + 1, j));
        checkNullAndSet(ans, searchInBoard(board, visited, node.nodes, i - 1, j));
        checkNullAndSet(ans, searchInBoard(board, visited, node.nodes, i, j + 1));
        checkNullAndSet(ans, searchInBoard(board, visited, node.nodes, i, j - 1));
        visited[i][j] = false;
        return ans;
    }

    Node[] roots;

    static class Node {
        String word;
        Node[] nodes;

        public Node() {
            word = null;
            nodes = new Node[26];
        }
    }

    private void addWord(String word) {
        if (word.length() == 0) return;
        Node traversal = getSet(roots, word.charAt(0) - 'a');
        for (int i = 1; i < word.length(); i++) {
            traversal = getSet(traversal.nodes, word.charAt(i) - 'a');
        }
        traversal.word = word;
    }

    private Node getSet(Node[] nodes, int c) {
        if (nodes[c] == null) nodes[c] = new Node();
        return nodes[c];
    }

}
