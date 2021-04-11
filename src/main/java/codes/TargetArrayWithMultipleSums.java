package codes;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TargetArrayWithMultipleSums {

    public static void main(String[] args) {
        System.out.println(
                isPossible(
                        new int[]{1, 1000000000}
                )
        );
    }

    public static boolean isPossible(int[] target) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        int sum = 0;
        for (int i : target) {
            sum += i;
            maxHeap.add(i);
        }
        while (maxHeap.peek() > 1) {
            Integer max = maxHeap.remove();
            int tail = 2 * max - sum;
            if (tail <= 0) return false;
            sum = max;
            maxHeap.add(tail);
        }

        return true;
    }

}
