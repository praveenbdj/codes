package codes;

import helper.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/problems/generate-parentheses/
public class GenerateParentheses {

    private static final char OPEN = '(';
    private static final char CLOSE = ')';

    public List<String> generateParenthesis(int n) {
        Stack<Character> stack = new Stack<>();
        return generateParenthesis(n, n, stack);
    }

    private List<String> generateParenthesis(int open, int close, Stack<Character> stack) {
        List<String> list = new ArrayList<>();
        if (open == 0 && close == 0) {
            StringBuilder s = new StringBuilder();
            do {
                s.append(stack.pop());
            }
            while (!stack.isEmpty());
            list.add(s.reverse().toString());
            for (int i = 0; i < s.length(); i++) {
                stack.push(s.charAt(i));
            }
            return list;
        }
        if (open > 0) {
            stack.push(OPEN);
            list.addAll(
                    generateParenthesis(open - 1, close, stack)
            );
            stack.pop();
        }
        if (close > open) {
            stack.push(CLOSE);
            list.addAll(
                    generateParenthesis(open, close - 1, stack)
            );
            stack.pop();
        }
        return list;
    }

    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        System.out.println(
                Helper.serialize(
                        generateParentheses.generateParenthesis(3)
                )
        );
    }

}
