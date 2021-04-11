package codes;

import java.util.*;

public class ZopSmart {

    public class Node {
        Node left, right, parent;
        int value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        public Node(int value) {
            this.value = value;
        }

        public Node(Node left, Node right, Node parent, int value) {
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.value = value;
        }
    }

    public List<List<Node>> findBurningSequence(Node root, Node start) {
        Queue<Node> queue = new ArrayDeque<>();//next to be burnet
        Map<Node, Integer> map = new HashMap<>();//what is in output.
        queue.add(start);
        map.put(start, 0);
        while (!queue.isEmpty()) {
            Node burned = queue.poll();
            Integer currentLevel = map.get(burned);
            checkAdd(burned.left, map, queue, currentLevel + 1);
            checkAdd(burned.right, map, queue, currentLevel + 1);
            checkAdd(burned.parent, map, queue, currentLevel + 1);
        }
        return calculateOutput(map);

    }

    private List<List<Node>> calculateOutput(Map<Node, Integer> map) {
        List<List<Node>> output = new ArrayList<>();
        int max = 0;
        for (Node node : map.keySet()) {
            Integer level = map.get(node);
            max = Math.max(level, max);
        }
        for (int i = 0; i <= max; i++) {
            output.add(new ArrayList<>());
        }
        for (Node node : map.keySet()) {
            Integer level = map.get(node);
            output.get(level).add(node);
        }
        return output;
    }

    private void checkAdd(Node node, Map<Node, Integer> map, Queue<Node> queue, Integer level) {
        if (!map.containsKey(node.value)) {
            queue.add(node);
            map.put(node, level);
        }
    }

    /*public static void main(String[] args) {
        ZopSmart smart = new ZopSmart();
        Node root = new Node(1, );

        smart.findBurningSequence();
    }*/

}
