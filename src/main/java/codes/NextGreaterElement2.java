package codes;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement2 {

    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        calc(nums, stack, ans);
        calc(nums, stack, ans);
        return ans;
    }

    private void calc(int[] nums, Stack<Integer> stack, int[] ans) {
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                int j = stack.pop();
                ans[j] = nums[i];
            }
            stack.push(i);
        }
    }

}
