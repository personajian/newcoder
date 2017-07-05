package cn.edu.seu.leetcode;

import java.util.Stack;

/**
 * @author personajian
 * 非递归方式实现反转二叉树
 */
public class Solution226_ {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            stack.pop();
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return root;
    }
}
