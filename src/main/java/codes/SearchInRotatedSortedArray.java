package codes;

//leetcode 33
//https://leetcode.com/problems/search-in-rotated-sorted-array/
public class SearchInRotatedSortedArray {
    public static int search(int[] nums, int target) {
        int pivot = findPivot(nums);
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int med = (start + end) / 2;
            int item = getItem(nums, med, pivot);
            if (start == end && item != target) {
                return -1;
            }
            if (item > target) {
                end = med - 1;
            } else if (item < target) {
                start = med + 1;
            } else return (med + pivot) % nums.length;
        }

        return -1;
    }

    private static int getItem(int[] nums, int index, int pivot) {
        int actualIndex = (index + pivot) % nums.length;
        return nums[actualIndex];
    }

    //helps to find the pivot at which the array was rotated
    public static int findPivot(int[] nums) {
        int start = 0, end = nums.length - 1;
        int ans = 0;
        while (start <= end) {
            int med = (start + end) / 2;
            if (nums[med] < nums[start]) {
                end = med - 1;
                ans = med;
            } else if (nums[med] > nums[end]) {
                start = med + 1;
            } else return nums[start] < nums[ans] ? start : ans;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(
                search(
                        new int[]{4, 5, 6, 7, 0, 1, 2}, 6
                )
        );
        System.out.println(
                search(
                        new int[]{4, 5, 6, 7, 0, 1, 2}, 0
                )
        );
        System.out.println(
                search(
                        new int[]{4, 5, 6, 7, 0, 1, 2}, 3
                )
        );
        System.out.println(
                findPivot(
                        new int[]{1, 2, 3, 4, 5, 6, 7, 8}
                )
        );
        System.out.println(
                findPivot(
                        new int[]{5, 6, 7, 8, 9, 20, 24, 1, 2, 3, 4, 5}
                )
        );

        System.out.println(
                findPivot(
                        new int[]{5, 1, 3}
                )
        );
        System.out.println(
                search(
                        new int[]{5, 1, 3}, 5
                )
        );

    }
}
