package codes;

import helper.Helper;

//leetcode 80
//https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
public class RemoveDuplicatesFromSortedArray2 {

    public static int removeDuplicates(int[] nums) {
        int minValue = Integer.MIN_VALUE;
        int dup = 1;
        int k = 1;
        int currentValue = nums[0];
        //mark extra items as null or min value
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == currentValue && ++dup > 2) {
                nums[i] = minValue;
            } else if (nums[i] != nums[i - 1]) {
                currentValue = nums[i];
                dup = 1;
                k++;
            } else {
                k++;
            }
        }

        //take two pointer approach to shift the elements
        int i = 0, j = 0;
        while (j < nums.length) {
            if (nums[j] == minValue) {
                j++;
            } else {
                nums[i++] = nums[j++];
            }
        }
        return k;
    }

    //more efficient, in single pass
    public static int removeDuplicates2(int[] nums) {
        int i = 0, j = 0, k = 0, dup = 0;
        int currentValue = nums[i];
        while (j < nums.length) {
            if (nums[j] == currentValue && ++dup > 2) {
                j++;
            } else {
                if (nums[j] != currentValue) {
                    currentValue = nums[j];
                    dup = 1;
                }
                nums[i++] = nums[j++];
                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = removeDuplicates2(nums);
        System.out.println("K = " + k);
        System.out.println(Helper.serialize(nums));
        nums = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        k = removeDuplicates2(nums);
        System.out.println("K = " + k);
        System.out.println(Helper.serialize(nums));

    }


}
