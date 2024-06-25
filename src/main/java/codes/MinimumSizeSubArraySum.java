package codes;

//leetcode 209
//https://leetcode.com/problems/minimum-size-subarray-sum
public class MinimumSizeSubArraySum {

    public static int minSubArrayLen(int target, int[] nums) {
        int l = 0, r = 0, sum = 0, minL = Integer.MAX_VALUE;

        while (r < nums.length) {
            sum += nums[r];

            while (sum >= target) {
                minL = Math.min(minL, r - l + 1);
                sum -= nums[l++];
            }
            r++;
        }

        return minL == Integer.MAX_VALUE ? 0 : minL;

    }

    public static void main(String[] args) {
        System.out.println(
                minSubArrayLen(7, new int[]{8})
        );
    }
}
