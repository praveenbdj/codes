package codes;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestInStream {

    PriorityQueue<Integer> queue;
    int k;

    public KthLargestInStream(int k, int[] nums) {
        this.k = k;
        List<Integer> slice = new ArrayList<>(k);
        for (int i = 0; i < k && i < nums.length; i++) {
            slice.add(nums[i]);
        }
        queue = new PriorityQueue<>(slice);
        for (int i = k; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (queue.size() < k) {
            queue.add(val);
        } else if (val > queue.peek()) {
            queue.poll();
            queue.add(val);
        }
        return queue.peek();
    }
}
