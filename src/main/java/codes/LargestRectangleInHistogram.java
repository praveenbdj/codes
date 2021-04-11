package codes;

import java.util.Stack;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        System.out.println(
                largestRectangleArea(new int[]{
                        1, 2, 3, 4, 3, 2, 3, 1
                })
        );
    }

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

}
