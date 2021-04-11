package codes;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//leetcode 215
public class KthLargestInArray {

    public int findKthLargest(int[] nums, int k) {
        List<Integer> slice = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            slice.add(nums[i]);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(slice);
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > queue.peek()) {
                queue.poll();
                queue.add(nums[i]);
            }
        }
        return queue.peek();
    }

}
