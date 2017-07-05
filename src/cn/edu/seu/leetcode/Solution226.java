package cn.edu.seu.leetcode;


/**
 * @author personajian
 * 递归实现反转二叉树
 */
public class Solution226 {
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		invertTree(root.left);
		invertTree(root.right);
		return root;
	}
}
