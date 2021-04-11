package codes;


import java.util.Arrays;

//leetcode 416
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1 || nums.length < 2) return false;
        sum /= 2;
        Boolean[][] dp = new Boolean[nums.length + 1][sum + 1];
        return knapsack(dp, nums, 0, sum);
    }

    private boolean knapsack(Boolean[][] dp, int[] nums, int i, int sum) {
        if (i >= nums.length) return false;
        if (sum - nums[i] == 0) return true;
        if (dp[i][sum] == null) {
            dp[i][sum] = knapsack(dp, nums, i + 1, sum);
            if (!dp[i][sum] && sum - nums[i] > 0) {
                dp[i][sum] = knapsack(dp, nums, i + 1, sum - nums[i]);
            }
        }
        return dp[i][sum];
    }

}
