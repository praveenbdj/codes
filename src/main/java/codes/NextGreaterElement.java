package codes;


import helper.Helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//leetcode 496
public class NextGreaterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                int in = stack.pop();
                map.put(nums2[in], nums2[i]);
            }
            stack.push(i);
        }
        int[] ans = new int[nums1.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = map.getOrDefault(nums1[i], -1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(
                Helper.serialize(
                        new NextGreaterElement().nextGreaterElement(
                                new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}
                        )
                )
        );
    }
}
