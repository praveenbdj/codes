package codes;

import java.util.Arrays;

//leetcode 34
//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class FirstAndLastPositionInSortedArray {
    public static int[] searchRange(int[] nums, int target) {
        return new int[]
                {
                        findFirst(nums, target, 0, nums.length - 1),
                        findLast(nums, target, 0, nums.length - 1)
                };
    }

    private static int findFirst(int[] nums, int target, int beg, int end) {
        if (end < beg) {
            return -1;
        }
        if (end == beg) {
            if (nums[beg] == target) {
                return beg;
            }
            return -1;
        }
        int med = (beg + end) / 2;
        if (nums[med] < target) {
            return findFirst(nums, target, med + 1, end);
        }
        int ans = findFirst(nums, target, beg, med - 1);
        if (nums[med] == target && ans == -1) {
            ans = med;
        }
        return ans;
    }

    private static int findLast(int[] nums, int target, int beg, int end) {
        if (end < beg) {
            return -1;
        }
        if (end == beg) {
            if (nums[beg] == target) {
                return beg;
            }
            return -1;
        }
        int med = (beg + end) / 2;
        if (nums[med] > target) {
            return findLast(nums, target, beg, med - 1);
        }
        int ans = findLast(nums, target, med + 1, end);
        if (nums[med] == target && ans == -1) {
            ans = med;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(searchRange(
                        new int[]{5, 7, 7, 8, 8, 10},
                        8
                ))
        );
        System.out.println(
                Arrays.toString(searchRange(
                        new int[]{5, 7, 7, 8, 8, 10},
                        6
                ))
        );
        System.out.println(
                Arrays.toString(searchRange(
                        new int[]{5, 7, 7, 8, 8, 10},
                        10
                ))
        );
        System.out.println(
                Arrays.toString(searchRange(
                        new int[]{},
                        0
                ))
        );
    }
}
