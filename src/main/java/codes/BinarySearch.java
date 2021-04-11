package codes;

public class BinarySearch {

    public int search(int[] nums, int target) {
        int l = 0, h = nums.length - 1, m;
        while (l <= h) {
            m = (l + h) / 2;
            if (nums[m] == target) return m;
            if (nums[m] > target) h = m - 1;
            else l = m + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        System.out.println(
                bs.search(
                        new int[]{},
                        5
                )
        );
    }

}
