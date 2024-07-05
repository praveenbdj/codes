package codes;

import java.util.ArrayList;
import java.util.List;

//leetcode 2420
//https://leetcode.com/problems/find-all-good-indices
public class FindAllGoodIndices {

    public static void main(String[] args) {
        FindAllGoodIndices sol = new FindAllGoodIndices();
        System.out.println(
                sol.goodIndices(new int[]{2, 1, 1, 1, 3, 4, 1}, 2)
        );
        System.out.println(
                sol.goodIndices(new int[]{2, 1, 1, 2}, 2)
        );
        System.out.println(
                sol.goodIndices(new int[]{440043, 276285, 336957}, 1)
        );
    }

    public List<Integer> goodIndices(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        if (k == 1) {
            for (int i = 1; i < nums.length - 1; i++) {
                ans.add(i);
            }
            return ans;
        }
        int left = 1, right = k + 1, j = 1;

        while (right < nums.length - 1) {

            if (nums[left] > nums[left - 1] ||
                    nums[right] > nums[right + 1]
            ) {
                j = 1;
            } else {
                j++;
            }
            if (j >= k) {
                ans.add(left + 1);
            }
            left++;
            right++;
        }

        return ans;
    }

}
