package codes;

public class TrappingRainWater {

    public static void main(String[] args) {
        System.out.println(
                trap(new int[]{4, 2, 0, 3, 2, 5})
        );
    }

    public static int trap(int[] height) {
        int[] aMax = new int[height.length], bMax = new int[height.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            max = Math.max(max, height[i]);
            aMax[i] = max;
        }
        max = Integer.MIN_VALUE;
        for (int i = height.length - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            bMax[i] = max;
        }
        int total = 0;
        for (int i = 1; i < height.length - 1; i++) {
            max = Math.min(aMax[i - 1], bMax[i + 1]);
            if (max > height[i])
                total += max - height[i];
        }
        return total;
    }

}
