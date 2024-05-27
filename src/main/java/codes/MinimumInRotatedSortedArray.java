package codes;

//leetcode 153
//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
public class MinimumInRotatedSortedArray {
    public static int findMin(int[] nums) {
        int pivot = SearchInRotatedSortedArray.findPivot(nums);
        return nums[pivot];
    }

    public static void main(String[] args) {
        System.out.println(
                findMin(
                        new int[]{3, 4, 5, 1, 2}
                )
        );
        System.out.println(
                findMin(
                        new int[]{4, 5, 6, 7, 0, 1, 2}
                )
        );
    }
}
