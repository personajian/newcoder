package cn.edu.seu.swordoffer;

/**
 * @Author personajian
 * @Date 2017/7/25 13:21
 */

public class T39_TreeDepth {
    public int TreeDepth(TreeNode root) {
        TreeNode p=root;
        if(p==null) return 0;
        else
            return Math.max(TreeDepth(p.left),TreeDepth(p.right))+1;
    }

    private class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}