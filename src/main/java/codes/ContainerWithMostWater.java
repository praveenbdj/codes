package codes;

//leetcode 11
//https://leetcode.com/problems/container-with-most-water/description/
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(
                maxArea(height)
        );
    }

    public static int maxArea(int[] height) {
        int maxArea = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            int currentArea = Math.min(height[l], height[r]) * (r - l);
            maxArea = Math.max(currentArea, maxArea);
            if (height[l] < height[r]) {
                l++;
            } else if (height[r] < height[l]) {
                r--;
            } else {
                l++;
                r--;
            }
        }

        return maxArea;
    }
}
