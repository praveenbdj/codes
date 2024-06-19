package codes;


import helper.Helper;

import java.util.LinkedList;
import java.util.List;

//asked in whatfix round2
public class PhoneBook {

    static class Node {
        boolean isWord;
        Node[] nodes;

        public Node() {
            isWord = false;
            nodes = new Node[26];
        }
    }

    Node[] roots;

    PhoneBook() {
        roots = new Node[26];
    }

    public void addWord(String word) {
        if (word.length() == 0) return;
        Node traversal = getSet(roots, word.charAt(0) - 'a');
        for (int i = 1; i < word.length(); i++) {
            traversal = getSet(traversal.nodes, word.charAt(i) - 'a');
        }
        traversal.isWord = true;
    }

    public List<String> search(String word) {
        List<String> ans = new LinkedList<>();
        if (word.length() != 0) {
            Node traversal = roots[word.charAt(0) - 'a'];
            for (int i = 1; i < word.length() && traversal != null; i++) {
                traversal = traversal.nodes[word.charAt(i) - 'a'];
            }
            if (traversal != null) {
                searchAndAdd(word, ans, traversal);
            }
        }
        return ans;
    }

    private void searchAndAdd(String word, List<String> ans, Node node) {
        if (node == null) return;
        if (node.isWord) {
            ans.add(word);
        }
        for (char i = 0; i < 26; i++) {
            StringBuilder builder = new StringBuilder();
            StringBuilder newWord = builder.append(word).append((char) ('a' + i));
            searchAndAdd(newWord.toString(), ans, node.nodes[i]);
        }
    }

    private Node getSet(Node[] nodes, int c) {
        if (nodes[c] == null) nodes[c] = new Node();
        return nodes[c];
    }

    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();
        book.addWord("mohan");
        book.addWord("mohsin");
        book.addWord("praveen");
        System.out.println(
                Helper.serialize(
                        book.search("m").toArray()
                )
        );
        System.out.println(
                Helper.serialize(
                        book.search("mohan").toArray()
                )
        );
        System.out.println(
                Helper.serialize(
                        book.search("pravee").toArray()
                )
        );
    }

}
