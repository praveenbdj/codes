package codes;

//leetcode 55
//https://leetcode.com/problems/jump-game/
public class JumpGame {
    public static boolean canJump(int[] nums) {
        int jump = 1;
        int i = 0;
        while (i < nums.length - 1) {
            jump--;
            if (nums[i] > jump) {
                jump = nums[i];
            }
            if (jump == 0) {
                return false;
            }
            i++;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(
                canJump(
                        new int[]{2, 3, 1, 1, 4}
                )
        );

        System.out.println(
                canJump(
                        new int[]{2, 2, 0, 0, 4}
                )
        );

        System.out.println(
                canJump(
                        new int[]{1, 1, 1, 1, 0}
                )
        );

        System.out.println(
                canJump(
                        new int[]{3, 2, 1, 0, 4}
                )
        );
    }
}
