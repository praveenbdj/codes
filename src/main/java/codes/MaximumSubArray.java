package codes;

//leetcode 53
//https://leetcode.com/problems/maximum-subarray/
public class MaximumSubArray {

    public static int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int num : nums) {
            currentSum = Math.max(currentSum + num, num);
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(
                maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})
        );
        System.out.println(
                maxSubArray(new int[]{-2, 1, -3, -40, -1, 2, 1, -5, 4})
        );
        System.out.println(
                maxSubArray(new int[]{1})
        );
        System.out.println(
                maxSubArray(new int[]{5, 4, -1, 7, 8})
        );
    }

}
