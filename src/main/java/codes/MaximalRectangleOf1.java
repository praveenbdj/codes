package codes;

import java.util.Stack;

public class MaximalRectangleOf1 {

    public static class El {
        int height;
        int pos;

        public El(int height, int pos) {
            this.height = height;
            this.pos = pos;
        }
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<El> stack = new Stack<>();
        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            El pop = null;
            while (!stack.isEmpty() && stack.peek().height > heights[i]) {
                pop = stack.pop();
                area = Math.max(area, pop.height * (i - pop.pos));
            }
            stack.push(new El(heights[i], pop == null ? i : pop.pos));
        }
        while (!stack.isEmpty()) {
            El pop = stack.pop();
            area = Math.max(area, pop.height * (heights.length - pop.pos));
        }
        return area;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int area = 0;
        int[] heights = new int[matrix[0].length];
        for (char[] chars : matrix) {
            for (int i = 0; i < chars.length; i++) {
                heights[i] = chars[i] == '0' ? 0 : heights[i] + 1;
            }
            area = Math.max(
                    area,
                    largestRectangleArea(heights)
            );
        }
        return area;
    }

}
