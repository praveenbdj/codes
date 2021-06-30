package codes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MinimumPlatforms {

    public static class Node {
        int val;
        boolean arr;

        public Node(int val, boolean arr) {
            this.val = val;
            this.arr = arr;
        }
    }

    public static int minPlatforms(int[] arr, int[] dep) {
        List<Node> nodeList = new ArrayList<>();
        for (int i : arr) {
            Node n = new Node(i, true);
            nodeList.add(n);
        }
        for (int i : dep) {
            Node n = new Node(i, false);
            nodeList.add(n);
        }
        nodeList.sort(Comparator.comparingInt(node -> node.val));
        for (Node node : nodeList) {
            System.out.println(node.val + " : " + (node.arr ? "arrival" : "depart"));
        }
        int max = 0;
        int counter = 0;

        for (Node node : nodeList) {
            if (node.arr) {
                counter++;
                max = Math.max(max, counter);
            } else {
                counter--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{900, 900, 900, 900, 900, 900};
        int[] dep = new int[]{910, 910, 910, 910, 910, 910};

        System.out.println(
                minPlatforms(arr, dep)
        );
    }

}
