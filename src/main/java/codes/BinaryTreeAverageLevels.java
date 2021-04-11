package codes;

import java.util.ArrayList;
import java.util.List;

// Leetcode 637
public class BinaryTreeAverageLevels {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> total = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        preOrderTraversal(root, total, count, 0);
        for (int i = 0; i < total.size(); i++) {
            total.set(i, total.get(i) / count.get(i));
        }
        return total;
    }

    private void preOrderTraversal(TreeNode root, List<Double> total, List<Integer> count, int level) {
        if (root == null) return;
        if (total.size() == level) total.add(0.0);
        if (count.size() == level) count.add(0);
        total.set(level, total.get(level) + root.val);
        count.set(level, count.get(level) + 1);
        preOrderTraversal(root.left, total, count, level + 1);
        preOrderTraversal(root.right, total, count, level + 1);
    }

}
