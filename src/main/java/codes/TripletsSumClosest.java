package codes;

import java.util.Arrays;

//https://leetcode.com/problems/3sum-closest/
public class TripletsSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        int min = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int l = i + 1;
            int h = nums.length - 1;
            while (l < h) {
                if (l > i + 1 && nums[l] == nums[l - 1]) {
                    l++;
                    continue;
                }
                int sum = nums[i] + nums[l] + nums[h];
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    l++;
                } else {
                    h--;
                }
                if (Math.abs(sum - target) < Math.abs(min - target)){
                    min = sum;
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(
                new TripletsSumClosest().threeSumClosest(new int[]{-3,-2,-5,3,-4}, -1)
        );
    }

}
