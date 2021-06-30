package codes;

import java.util.Comparator;
import java.util.PriorityQueue;

public class StackUsingPriorityQueue {

    private Integer PRIORITY = Integer.MAX_VALUE;

    private PriorityQueue<Node> queue;

    public StackUsingPriorityQueue() {
        queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.priority));
    }

    public static class Node {
        int val;
        int priority;

        public Node(int val, int priority) {
            this.val = val;
            this.priority = priority;
        }
    }

    public void push(int val) {
        Node node = new Node(val, getNextPriority());
        queue.add(node);
    }

    public int pop() throws Exception {
        if (queue.isEmpty()) {
            throw new Exception("Stack is empty");
        }
        Node poll = queue.poll();
        return poll.val;
    }

    public int peek() throws Exception {
        if (queue.isEmpty()) {
            throw new Exception("Stack is empty");
        }
        return queue.peek().val;
    }

    private int getNextPriority() {
        return PRIORITY--;
    }

    public static void main(String[] args) throws Exception {
        StackUsingPriorityQueue stack = new StackUsingPriorityQueue();
        stack.push(10);
        stack.push(11);
        System.out.println(stack.peek());
        stack.push(9);
        System.out.println(stack.peek());
        stack.push(1);
        System.out.println(stack.peek());
        stack.push(10);
        System.out.println(stack.peek());
        System.out.println("-------------");
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }
    }
}
