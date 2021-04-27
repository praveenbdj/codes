package codes;

import java.util.Stack;

//https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/
public class MinimumRemoveValidParenthesis {

    private final static char OPEN = '(';
    private final static char CLOSE = ')';

    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == OPEN) {
                stack.push(i);
            } else if (s.charAt(i) == CLOSE) {
                if (!stack.isEmpty() && s.charAt(stack.peek()) == OPEN) {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }
        int i = s.length();
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            int j = stack.pop();
            while (--i > j) {
                builder.append(s.charAt(i));
            }
        }
        while (--i >= 0) {
            builder.append(s.charAt(i));
        }
        return builder.reverse().toString();
    }
}
