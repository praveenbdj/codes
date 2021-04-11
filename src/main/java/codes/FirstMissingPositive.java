package codes;

public class FirstMissingPositive {

    public static void main(String[] args) {
        System.out.println(
                firstMissingPositive(new int[]{1, 2, 0})
        );
    }

    public static int firstMissingPositive(int[] nums) {
        if (nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int abs = Math.abs(nums[i]);
            if (abs != 0 && abs < nums.length)
                nums[abs] = -1 * Math.abs(nums[abs]);
            else if (abs == nums.length)
                nums[0] = -1 * Math.abs(nums[0]);
        }
        //System.out.println(JsonHelper.serialize(nums));
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= 0) {
                return i;
            }
        }
        return nums[0] >= 0 ? nums.length : nums.length + 1;
    }

    private static boolean doesExist(int[] a, int x) {
        return x < a.length;
    }

}
